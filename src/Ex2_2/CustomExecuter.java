package Ex2_2;

import java.util.concurrent.*;

public class CustomExecuter extends ThreadPoolExecutor {

    int[] priority = new int[10];

    public CustomExecuter() {
        super(Runtime.getRuntime().availableProcessors() / 2, Runtime.getRuntime().availableProcessors() - 1,
                300, TimeUnit.MILLISECONDS, new PriorityBlockingQueue<>());
    }

    public <T> Future<T> submit(Task<T> task){
        if(task == null){
            throw new NullPointerException();
        }
        this.priority[task.getPriority()-1]++;
        TaskConverter<T> temp = new TaskConverter(task);
        execute(temp);
        return temp;
    }

    public <T> Future<T> submit(Callable<T> my_task){
        return submit(Task.createTask(my_task));
    }

    public <T> Future<T> submit(Callable<T> my_task, TaskType task_type){
        return submit(Task.createTask(my_task, task_type));
    }

    public void gracefullyTerminate() {
        try
        {
            super.shutdown();
            super.awaitTermination(10,TimeUnit.MILLISECONDS);
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public int getCurrentMax() {
        for(int i = 0; i < 10; i++){
            if(this.priority[i] != 0){
                return i + 1;
            }
        }
        return 0;
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        int max = getCurrentMax();
        if (10 >= max && max > 0) {
            priority[max-1]--;
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }
}