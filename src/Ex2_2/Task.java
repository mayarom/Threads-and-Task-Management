package Ex2_2;

import java.util.concurrent.Callable;

public class Task<T> implements Callable<T>, Comparable<Task<T>> {
    private int priority;
    private Callable<T> my_task;

    public static Task createTask(Callable my_task, TaskType task_type) {
        return new Task(task_type,my_task);
    }

    public static Task createTask(Callable my_task) {
        return new Task(TaskType.OTHER,my_task);
    }

    private Task(TaskType task_type, Callable<T> my_task){
        this.priority = task_type.getPriorityValue();
        this.my_task = my_task;
    }

    @Override
    public T call() throws Exception {
        return this.my_task.call();
    }

    @Override
    public int compareTo(Task<T> task) {
        return Integer.compare(this.priority, task.priority);
        //        if (this.priority < task.getPriority()) {
//            return -1;
//        } else if (this.priority > task.getPriority()) {
//            return 1;
//        }
//        return 0;
    }

    public int getPriority(){return priority;}

    public Callable getMyTask(){return my_task;}

    @Override
    public String toString() {
        return "Task's priority : " + priority + "\n";
    }
}
