package Ex2_2;

import java.util.concurrent.FutureTask;

public class TaskConverter<T> extends FutureTask<T> implements Comparable<TaskConverter<T>> {

    private Task<T> task;

    public TaskConverter(Task<T> task){
        super(task);
        this.task = task;
    }
    @Override
    public int compareTo(TaskConverter<T> task) {
        return this.task.compareTo(task.getTask());
    }

    public void setTask(Task<T> task) {
        this.task = task;
    }

    public Task<T> getTask() {
        return task;
    }
}
