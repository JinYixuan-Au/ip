package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

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
