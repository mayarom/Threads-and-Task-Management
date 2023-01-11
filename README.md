

# Assignment number 2 - Threads
## Object Oriented Programming Ariel University

The assignment had two parts:
1. Part A- Use of threads, thread pool: Examining the running time of a simple function in relation to threads and thread pool
2. Part B- 

### Part one

In the first part we had to build 4 actions:
1. Creates 10 text files (with a random number of lines) and an array of size 10 with the names of the files (txt.serial number).


#### Threads and Thread pool
In Java, threads are mapped to system-level threads, which are the operating system's resources. If we create threads uncontrollably, we may run out of these resources quickly.

The operating system does the context switching between threads as well — in order to emulate parallelism. A simplistic view is that the more threads we spawn, the less time each thread spends doing actual work.

The Thread Pool pattern helps to save resources in a multithreaded application and to contain the parallelism in certain predefined limits.

When we use a thread pool, we write our concurrent code in the form of parallel tasks and submit them for execution to an instance of a thread pool. This instance controls several re-used threads for executing these tasks.
