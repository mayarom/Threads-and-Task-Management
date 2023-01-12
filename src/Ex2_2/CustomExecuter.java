package Ex2_2;

import java.util.concurrent.*;

public class CustomExecuter extends ThreadPoolExecutor {

    int[] priority = new int[10];

    /**
     * this function inherits from ThreadPoolExecutor the constructor
     *
     */

    public CustomExecuter() {
        super(Runtime.getRuntime().availableProcessors() / 2, Runtime.getRuntime().availableProcessors() - 1,
                300, TimeUnit.MILLISECONDS, new PriorityBlockingQueue<>());
    }

    /**
     * this function submits the task to the thread pool
     * @param task the task that we want to submit
     * @param <T> the type of the task
     * @return the task that we submitted
     */
    public <T> Future<T> submit(Task<T> task){
        if(task == null){
            throw new NullPointerException();
        }
        this.priority[task.getPriority()-1]++;
        TaskConverter<T> temp = new TaskConverter(task);
        execute(temp);
        return temp;
    }

    /**
     * this function submits the task to the thread pool through the first submit function
     * @param my_task the task to submit of a callable type
     * @return the task that we submitted
     */
    public <T> Future<T> submit(Callable<T> my_task){
        return submit(Task.createTask(my_task));
    }

    /**
     * this function submits the task to the thread pool through the first submit function
     * @param my_task the task to submit of a collable type
     * @param task_type the type of the task
     * @return the task that we submitted
     */
    public <T> Future<T> submit(Callable<T> my_task, TaskType task_type){
        return submit(Task.createTask(my_task, task_type));
    }

    /**
     * this function stops and terminate the CustomExecuter
     */
    public void gracefullyTerminate() {
        try
        {
            super.shutdown();
            super.awaitTermination(100,TimeUnit.MILLISECONDS);
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * this function returns the max priority of the tasks that were submitted in a O(1) complexity
     * @return the max priority of the tasks that were submitted
     */
    public int getCurrentMax() {
        for(int i = 0; i < 10; i++){
            if(this.priority[i] != 0){
                return i + 1;
            }
        }
        return 0;
    }

    /**
     * this function override the beforeExecute function from ThreadPoolExecutor
     * @param thread the thread that will run task
     * @param run the task that will be executed by thread
     */

    @Override
    protected void beforeExecute(Thread thread, Runnable run) {
        int max = getCurrentMax();
        if (10 >= max && max > 0) {
            priority[max-1]--;
        }
    }

    /**
     * this function override the tosString function from ThreadPoolExecutor
     * @return the string that represents the CustomExecuter
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
