package Ex2_2;

import java.util.concurrent.Callable;

public class Task<T> implements Callable<T>, Comparable<Task<T>> {
    private int priority;
    private Callable<T> my_task;

    /**
     * this function creates a task with a callable and task type
     * @param my_task the task to create of a callable type
     * @param task_type the type of the task to create
     * @return the task that we created
     */
    public static Task createTask(Callable my_task, TaskType task_type) {
        return new Task(task_type,my_task);
    }

    /**
     * this function creates a task with a callable and a default task type
     * @param my_task the task to create of a callable type
     * @return the task that we created
     */
    public static Task createTask(Callable my_task) {
        return new Task(TaskType.OTHER,my_task);
    }

    /**
     * this function creates the task
     * @param task_type the type of the task
     * @param my_task the task to create
     */
    private Task(TaskType task_type, Callable<T> my_task){
        this.priority = task_type.getPriorityValue();
        this.my_task = my_task;
    }

    /**
     * this function do the call method of the callable which means execute the asynchronous task
     * @throws Exception if the callable throws an exception
     * @return the result of the callable
     */
    @Override
    public T call() throws Exception {
        return this.my_task.call();
    }

    /**
     * this function compares between two tasks
     * @param task the object to be compared.
     * @return the value 0 if the argument task is equal to this task; a value less than 0 if this task is
     *         less than the task argument; and a value greater than 0 if this task is greater than the task
     */
    @Override
    public int compareTo(Task<T> task) {

        if (this.priority < task.getPriority()) {
         return -1;
        } else if (this.priority > task.getPriority()) {
            return 1;
        }
        return 0;
    }

    /**
     * this function returns the priority of the task
     * @return the priority of the task
     */
    public int getPriority(){return priority;}

    /**
     * this function returns the task
     * @return the task
     */
    public Callable getMyTask(){return my_task;}

    /**
     * this function print the task's priority
     * @return the task's priority
     */
    @Override
    public String toString() {
        return "Task's priority : " + priority + "\n";
    }
}
