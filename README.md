

 
# Exchange Rate Streaming Job with Sliding Windows
## Carried Out By : 
* Roua Acheche
* Hamza AZIZI

This project implements a Flink streaming job that processes exchange rate data using a sliding window operation. The job reads data from an AlphaVantageAPI source and applies a sliding window operation with a fixed window size of 10 seconds and a slide interval of 5 seconds. Within each window, the exchange rate data is multiplied by 2 and then aggregated using the reduce operation, which sums up the values in the window. The aggregated data is then printed to the console.

## Requirements
To run this project, you will need:

- Java 8 or higher
- Apache Flink streaming API
- AlphaVantageAPI source
## Setup
- Open the project in your preferred IDE or text editor.
- Modify the ExchangeRate.scala file to specify the correct input source and window parameters if needed.
- Build the project using your preferred build tool or using the command line.
- Run the ExchangeRate object to start the streaming job.
## Usage
Once the streaming job is running, it will read data from the AlphaVantageAPI source and apply a sliding window operation with a fixed window size of 10 seconds and a slide interval of 5 seconds. Within each window, the exchange rate data is multiplied by 2 and then aggregated using the reduce operation, which sums up the values in the window. The aggregated data is then printed to the console.

To stop the streaming job, you can terminate the job using the CTRL+C command or by stopping the Flink cluster.

## Contributing
If you find a bug or would like to suggest an improvement, feel free to open an issue or submit a pull request.

## License
This project is licensed under the MIT License.
## Sliding window

In stream processing, a window is a finite-sized buffer that collects a subset of data items from an unbounded stream. The sliding window is a type of window that is used to process a continuous stream of data in a fixed-size buffer.

A sliding window has two parameters: window size and slide size. The window size specifies the number of data items that the buffer can hold, while the slide size determines the number of data items that are added to the buffer after each operation.

Using a sliding window allows us to compute aggregates over a continuous stream of data, without having to store the entire stream in memory. We can also adjust the window size and slide size to balance the accuracy and responsiveness of the computation.


In the provided code, a sliding window of fixed length 10 seconds and slide interval 5 seconds is defined using the timeWindowAll method of Flink's streaming API. This means that every 5 seconds, a new window of 10 seconds worth of data will be created, and the window will slide forward by 5 seconds to include the new data.

For example, if we have data arriving at times t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, ... where ti denotes the time of arrival of the ith data point, the sliding window operation will produce the following windows:

* Window 1: [t1, t2, t3, t4, t5, t6, t7, t8, t9, t10]
* Window 2: [t6, t7, t8, t9, t10, t11, t12, t13, t14, t15]
* Window 3: [t11, t12, t13, t14, t15, t16, t17, t18, t19, t20]
... and so on

Within each window, the exchange rate data will be multiplied by 2 using the map operation and then aggregated using the reduce operation. Finally, the aggregated data will be printed to the console using the print operation.

Overall, the logic for the sliding window operation is to define a fixed-size window and a slide interval, and then to process the data within each window using appropriate operations such as map and reduce.
 ## Exchange Rates Plotting ( Bitcoin, Ethereum and Ripple)
 This project includes a plotting component for visualizing Cryptocurrencies exchange rates using Python, which can be found in the notebook titled "RapidAPI_Crypto.ipynb".
 ![Alt Text](plotting_Exchange_Rates.png)
