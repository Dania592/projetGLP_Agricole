package process.action.exception.structure;

import process.action.task.Task;

public class TaskCompleteException extends Exception {
    public TaskCompleteException(Task<?> task){
        super(task + " is complete!");
    }
}
