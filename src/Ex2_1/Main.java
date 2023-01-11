package Ex2_1;

import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String[] str = Ex2_1.createTextFiles(10, 1,100);
        Ex2_1 o = new Ex2_1();
        for (String name:str) {
            System.out.println(name);
        }
        System.out.println("Normal: "+Ex2_1.getNumOfLines(str));
        System.out.println("Threads: "+o.getNumOfLinesThreads(str));
        System.out.println("Threadpool: "+o.getNumOfLinesThreadPool(str));
    }
}