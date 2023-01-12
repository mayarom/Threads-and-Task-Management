

# Assignment number 2 - Threads
## Object Oriented Programming Ariel University

The assignment had two parts:
1. Part A- Use of threads, thread pool: Examining the running time of a simple function in relation to threads and thread pool
2. Part B- 

## Part one

### Metods
In the first part we had to build 4 methods:

#### createTextFiles(int n, int seed, int bound):
This method creates n text files and returns an array of the file names. The parameter n controls the number of files created. The parameter seed is used as a seed for the random number generator, and bound is used as the upper bound for the random number of rows in each file. Each file is named "file_i.txt", where i is the index of the file in the array.
The method first checks that the input parameters are valid (n > 0 and bound > 0), if not it throws an IllegalArgumentException.
It then creates an array of file names and a buffered writer for each file, with the file name "file_i.txt".
A random number generator is created with the seed parameter, and it generates a random number between 0 and bound, which represents the number of rows for the current file.
It then writes the string "hello world" (at least 10 characters) for the number of rows determined by the random number, for each file.
Finally it closes the buffered writer.

#### getNumOfLines(String[] fileNames):
This method takes a string array of file names and returns the sum of the total number of lines in all the files.
It creates a variable to store the total lines count and using a for loop, it opens a buffered reader for each file in the array, it reads each line in the file and increment the count variable, after reading all the file lines, it closes the buffered reader and returns the total lines count.

#### getNumOfLinesThreads(String[] fileNames):
This method does the same as the previous method, but uses threads to read the files.
It creates an array of threads, with one thread for each file in the fileNames array.
Each thread is created with the Mythread class, which reads the corresponding file and increments a shared AtomicInteger variable that stores the total number of lines read.
Finally it starts all the threads, waits for them to finish, and then it returns the total number of lines.

#### getNumOfLinesThreadPool(String[] fileNames):
This method does the same as the previous two methods, but uses a thread pool to read the files.
It creates an executor service thread pool with the number of threads equals to the number of files, using the Executors.newFixedThreadPool() method.
Then it submits a Callable task for each file, and collects the returned value of each task, which is the number of lines in the corresponding file.
Finally, it shutdowns the thread pool and returns the sum of all the returned values as the total number of lines in all the files.

### Threads and Thread pool
In Java, threads are mapped to system-level threads, which are the operating system's resources. If we create threads uncontrollably, we may run out of these resources quickly.

The operating system does the context switching between threads as well — in order to emulate parallelism. A simplistic view is that the more threads we spawn, the less time each thread spends doing actual work.

The Thread Pool pattern helps to save resources in a multithreaded application and to contain the parallelism in certain predefined limits.

When we use a thread pool, we write our concurrent code in the form of parallel tasks and submit them for execution to an instance of a thread pool. This instance controls several re-used threads for executing these tasks.

### runnable and callable
Runnable or Callable interfaces Interfaces are used by Thread, Mythread and ThreadPool (ExecutorService) to read the files and count the number of lines.

Runnable is an interface in Java that defines a single method, run(), which contains the code that will be executed by a thread. In order to run code in a separate thread, you need to create a class that implements Runnable and overrides the run() method with the code you want to run in the separate thread.

Callable is similar to Runnable, but it allows the thread to return a result when it completes its execution. Callable is a functional interface, it defines a single method, call(), that contains the code that will be executed by a thread and returns a value.

Thread class is a predefined class in Java that allows creating and controlling a thread of execution, it can be used by extending the class or providing a Runnable object or using the Thread constructor. Mythread is an example of extending the Thread class.

ExecutorService is an interface that provides methods for executing tasks using a pool of threads, the tasks are either Runnable or Callable, ExecutorService provides more functionality than thread creation and management, it also provides ways of managing the thread pool and handling the thread exceptions.

### Uml
![image](https://user-images.githubusercontent.com/95377680/211935055-5037b316-bfe1-4195-bb7a-8d8fc44c1847.png)

### Tests

It appears that the test cases are checking the performance of the program using three different methods of executing the tasks (normal, using threads, and using thread pool) and comparing their average execution times. The test is creating text files, and it is calling three different methods to count the number of lines in these text files and comparing the time it takes for each method. There is also test cases for creating text files and validating if the files are created successfully, and a test case for checking if the total number of lines returned is correct.

#### Time measurement results and conclusions

In the test results, it was shown that the function implemented with threads had the best performance among the three methods, followed by the function implemented with threadpool and lastly, the function implemented in a traditional, single-threaded way.

There are several reasons why this might be the case:
First, in the traditional, single-threaded method, the program only uses one core of the CPU, which can cause a bottleneck in performance as the program's operations can only be processed one at a time.
On the other hand, the thread-based method and threadpool-based method utilizes multiple threads, which can run concurrently and can therefore perform multiple operations at the same time, resulting in better performance.

Threadpool-based method has a limitation that it can only queue a certain number of thread, and each thread has a cost of instantiation, the thread-based method is more effective if the number of requests is not constant.

Additionally, thread-based method can have a lower overhead because it doesn’t require the threadpool manager to add and remove threads, this means that it can create and destroy threads on the fly as needed, which can lead to better performance.

In conclusion, using threads and threadpools can greatly improve the performance of your program, especially when dealing with concurrent tasks. However, the best method to use depends on the specific requirements and nature of the task at hand.







## Part two
