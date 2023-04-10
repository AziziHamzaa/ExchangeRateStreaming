# ExchangeRateStreaming

In stream processing, a window is a finite-sized buffer that collects a subset of data items from an unbounded stream. The sliding window is a type of window that is used to process a continuous stream of data in a fixed-size buffer.

A sliding window has two parameters: window size and slide size. The window size specifies the number of data items that the buffer can hold, while the slide size determines the number of data items that are added to the buffer after each operation.

In our use case, we are processing an exchange rate data stream, and we want to compute the moving average of the exchange rate over a certain period. We can use a sliding window with a fixed window size and slide size to compute the moving average.

For example, if we use a window size of 5 and slide size of 1, the sliding window will hold 5 exchange rates at a time and slide over the stream, adding a new exchange rate to the buffer after each operation.

After the buffer is full, the sliding window applies a computation function (in our case, the mean function) to the data in the buffer and emits the result. Then, it moves the window by the slide size and repeats the process with the next set of data items.

Using a sliding window allows us to compute aggregates over a continuous stream of data, without having to store the entire stream in memory. We can also adjust the window size and slide size to balance the accuracy and responsiveness of the computation.
