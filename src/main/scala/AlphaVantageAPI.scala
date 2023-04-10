import org.apache.flink.streaming.api.functions.source.SourceFunction
import org.json4s._
import org.json4s.native.JsonMethods._

import java.io.{BufferedReader, InputStreamReader}
import java.net.{HttpURLConnection, URL}
import scala.util.{Failure, Success, Try}

class AlphaVantageAPI extends SourceFunction[Double] {
  override def run(ctx: SourceFunction.SourceContext[Double]): Unit = {
    val apiKey = "a87319b68cmsh175e9f86cb40e90p190f6cjsn8572d0df81a4"
    val fromCurrency = "BTC"
    val toCurrency = "USD"
    val function = "CURRENCY_EXCHANGE_RATE"
    val url = s"https://alpha-vantage.p.rapidapi.com/query?from_currency=$fromCurrency&function=$function&to_currency=$toCurrency"

    while (true) {
      Try {
        val connection = new URL(url).openConnection.asInstanceOf[HttpURLConnection]
        connection.setRequestMethod("GET")
        connection.setRequestProperty("X-RapidAPI-Key", apiKey)
        connection.setRequestProperty("X-RapidAPI-Host", "alpha-vantage.p.rapidapi.com")

        val responseCode = connection.getResponseCode
        if (responseCode == HttpURLConnection.HTTP_OK) {
          val in = new BufferedReader(new InputStreamReader(connection.getInputStream))
          val response = new StringBuilder
          var inputLine: String = null
          while ({ inputLine = in.readLine; inputLine != null }) {
            response.append(inputLine)
          }
          in.close()

          implicit val formats: DefaultFormats.type = DefaultFormats
          val json = parse(response.toString)
          val rate = (json \\ "5. Exchange Rate").extract[String].toDouble
          println(s"Exchange rate: $rate")
        } else if (responseCode == 429) {
          // If we exceed the API limit, wait for a minute and try again
          Thread.sleep(60000)
        }else {
          throw new RuntimeException(s"HTTP GET request failed with error code: $responseCode")
        }
      } match {
        case Success(_) =>
        case Failure(ex) =>
          ex.printStackTrace()
      }

      Thread.sleep(13000) // Stream the data every 13 seconds
    }
  }

  override def cancel(): Unit = {}
}