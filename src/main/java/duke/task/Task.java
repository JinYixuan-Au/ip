package duke.task;

/**
 * The parent class of Deadline, Event and Todo class
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor of the task class
     * Create a new task object from command
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription(){
        return description;
    }
    public String getStatusIcon() {
        return (isDone ? "[\u2713]" : "[\u2718]"); //return tick or X symbols
    }

    public void taskDone(){
        this.isDone=true;
    }

    public String toString(){
        return this.getStatusIcon() + description;
    }

    public String printIntoFile(){
        return "Task|" + isDone + "|" + description + "|";
    }
}
