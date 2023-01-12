package Ex2_2;

import java.util.concurrent.FutureTask;

public class TaskConverter<T> extends FutureTask<T> implements Comparable<TaskConverter<T>> {

    private Task<T> task;

    /**
     * this function inherits from FutureTask the constructor and creates a task converter
     * @param task the task that we want to convert
     */
    public TaskConverter(Task<T> task){
        super(task);
        this.task = task;
    }

    /**
     * this function compares between two tasks
     * @param task the object to be compared.
     * @return the value 0 if the argument task is equal to this task; a value less than 0 if this task is
     *         less than the task argument; and a value greater than 0 if this task is greater than the task
     */
    @Override
    public int compareTo(TaskConverter<T> task) {
        return this.task.compareTo(task.getTask());
    }

    /**
     * this function set the task
     * @param task the task to set
     */
    public void setTask(Task<T> task) {
        this.task = task;
    }

    /**
     * this function returns the task
     * @return the task
     */
    public Task<T> getTask() {
        return task;
    }
}
