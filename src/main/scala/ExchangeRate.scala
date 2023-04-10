import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.windowing.time.Time

object ExchangeRate {
  def main(args: Array[String]): Unit = {

    // Set up the streaming execution environment
    val env = StreamExecutionEnvironment.getExecutionEnvironment

    // Set up the AlphaVantageAPI source
    val xrpExchangeRate = env.addSource(new AlphaVantageAPI())

    // Define a fixed-size sliding window of 10 seconds with a slide interval of 5 seconds
    val windowSize = Time.seconds(10)
    val slideInterval = Time.seconds(5)

    // Apply a sliding window to the exchange rate stream
    val exchangeRateWindowed = xrpExchangeRate
      .map(rate => rate * 2)
      .timeWindowAll(windowSize, slideInterval)
      .reduce((rate1, rate2) => rate1 + rate2)

    // Print the aggregated windowed data to the console
    exchangeRateWindowed.print()

    // Execute the streaming job
    env.execute("BTC Exchange Rate Streaming with Sliding Windows")
  }
}
