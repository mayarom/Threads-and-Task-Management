

# Assignment number 2 - Threads
## Object Oriented Programming Ariel University

The assignment had two parts:
1. Part A- Use of threads, thread pool: Examining the running time of a simple function in relation to threads and thread pool
2. Part B- 

### Part one

In the first part we had to build 4 methods:

* createTextFiles(int n, int seed, int bound):
This method creates n text files and returns an array of the file names. The parameter n controls the number of files created. The parameter seed is used as a seed for the random number generator, and bound is used as the upper bound for the random number of rows in each file. Each file is named "file_i.txt", where i is the index of the file in the array.
The method first checks that the input parameters are valid (n > 0 and bound > 0), if not it throws an IllegalArgumentException.
It then creates an array of file names and a buffered writer for each file, with the file name "file_i.txt".
A random number generator is created with the seed parameter, and it generates a random number between 0 and bound, which represents the number of rows for the current file.
It then writes the string "hello world" (at least 10 characters) for the number of rows determined by the random number, for each file.
Finally it closes the buffered writer.

* getNumOfLines(String[] fileNames):
This method takes a string array of file names and returns the sum of the total number of lines in all the files.
It creates a variable to store the total lines count and using a for loop, it opens a buffered reader for each file in the array, it reads each line in the file and increment the count variable, after reading all the file lines, it closes the buffered reader and returns the total lines count.

* getNumOfLinesThreads(String[] fileNames):
This method does the same as the previous method, but uses threads to read the files.
It creates an array of threads, with one thread for each file in the fileNames array.
Each thread is created with the Mythread class, which reads the corresponding file and increments a shared AtomicInteger variable that stores the total number of lines read.
Finally it starts all the threads, waits for them to finish, and then it returns the total number of lines.

* getNumOfLinesThreadPool(String[] fileNames):
This method does the same as the previous two methods, but uses a thread pool to read the files.
It creates an executor service thread pool with the number of threads equals to the number of files, using the Executors.newFixedThreadPool() method.
Then it submits a Callable task for each file, and collects the returned value of each task, which is the number of lines in the corresponding file.
Finally, it shutdowns the thread pool and returns the sum of all the returned values as the total number of lines in all the files.

#### Threads and Thread pool
In Java, threads are mapped to system-level threads, which are the operating system's resources. If we create threads uncontrollably, we may run out of these resources quickly.

The operating system does the context switching between threads as well — in order to emulate parallelism. A simplistic view is that the more threads we spawn, the less time each thread spends doing actual work.

The Thread Pool pattern helps to save resources in a multithreaded application and to contain the parallelism in certain predefined limits.

When we use a thread pool, we write our concurrent code in the form of parallel tasks and submit them for execution to an instance of a thread pool. This instance controls several re-used threads for executing these tasks.

#### runnable and callable

In this assignment, for the function getNumOfLinesThreads() we built a MyThread class which extends from Thread, where Thread implements the Runnable interface. For the getNumOfLinesThreadpool() function we built a Task class that implements the Callable interface

Both runnable and callable interfaces are designed for classes. Their instances are supposed to be executed by another thread.

However, there are also some differences between these interfaces. Let’s discuss the differences between them by explaining them separately.

#### Uml
![image](https://user-images.githubusercontent.com/95377680/211935055-5037b316-bfe1-4195-bb7a-8d8fc44c1847.png)

