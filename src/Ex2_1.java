import com.sun.source.util.TaskEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;


public class Ex2_1 {
    /**
     * the function makes n text files, every row contains at least 10 characters.
     * it's return a string array with the n files names.
     *
     * @param n     number of text files
     * @param seed  seed for the random number generator
     * @param bound bound for the random number generator
     * @return files name array ,
     */
    public static String[] createTextFiles(int n, int seed, int bound) {
        String[] files_name = new String[n];
        for (int i = 0; i < n; i++) {
            int files_id = i + 1;
            files_name[i] = "file_" + files_id + ".txt";
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(files_name[i])); // create a new file with the name file_i.txt
                Random rand = new Random(seed); // create a random-number : number of file's rows
                int rand_row = rand.nextInt(bound); //random number between 0 to bound
                for (int rows_writer = 0; rows_writer < rand_row; rows_writer++) {
                    writer.write("hello world\n"); //  write to the file "hello world" (at least 10 characters)
                }
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            seed++;

        }
        return files_name;
    }

    /**
     * this function gets a string array with the files names and return the sum of the file's rows
     *
     * @param fileNames string array with the files names
     * @return sum of the rows of the files
     */
    public static int getNumOfLines(String[] fileNames) {
        int sum_lines = 0;
        for (int i = 0; i < fileNames.length; i++) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(fileNames[i]));
                while (reader.readLine() != null) {
                    sum_lines++;
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return sum_lines;
    }

    /**
     * this function gets a string array with the files names and return the sum of the file's rows using threads
     *
     * @param fileNames string array with the files names
     * @return sum of the rows of the files
     */
    public int getNumOfLinesThreads(String[] fileNames) {
        Mythread[] threads = new Mythread[fileNames.length];//create array of threads
        for (int i = 0; i < fileNames.length; i++) {
            Mythread current_thread = new Mythread(fileNames[i]);
            current_thread.start();
            threads[i] = current_thread;
        }
        for (int i = 0; i < threads.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                System.err.println("Thread " + i + " was interrupted");
                e.printStackTrace();

            }
        }
        return Mythread.fun3sum.get();
    }

    /**
     * this function gets a string array with the files names and return the sum of the file's rows using threadpool
     *
     * @return sum of the rows of the files
     */
    public int getNumOfLinesThreadPool(String[] fileNames) throws ExecutionException, InterruptedException {
        int sum_lines=0;
        ExecutorService MyThreadPool = Executors.newFixedThreadPool(fileNames.length); // create thread pool
        for (int i=0; i<fileNames.length; i++) {
            Callable<Integer> callable = new Task(fileNames[i]); // create callable object
            Future<Integer> future = MyThreadPool.submit(callable); // submit the task to the thread pool
            sum_lines+=future.get(); // get the result from the future object
        }
        MyThreadPool.shutdown();
        return sum_lines;
    }

// interface callable - with return (implements)
// interface runable - withoout return  (class thread implements runable)

    public class Mythread extends Thread {
        public String fileName;
        static AtomicInteger fun3sum = new AtomicInteger(0);

        public Mythread(String fileName) {
            super(); // call to Thread constructor
            this.fileName = fileName;
        }

        @Override
        public void run() { // override run method and count the num of the file's lines.
            try {
                BufferedReader reader = new BufferedReader(new FileReader(fileName));
                while (reader.readLine() != null) {
                    fun3sum.incrementAndGet();
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // threadpool- treadcollection in order to run few threads in the same time
    class Task implements Callable {
        String fileThread;
        public Task(String fileName) {
            this.fileThread = fileName;
        }

        @Override
        public Object call() throws Exception {
            int sum_lines = 0;
            try {
                BufferedReader reader = new BufferedReader(new FileReader(fileThread));
                while (reader.readLine() != null) {
                    sum_lines++;
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return sum_lines;
        }
    }
}