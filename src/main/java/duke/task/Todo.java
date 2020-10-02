package duke.task;

public class Todo extends Task{

    public Todo(String description) {
        super(description);
    }

    public String getTypeIcon(){
        return "[T]";
    }

    public String toString(){
        return this.getTypeIcon() + this.getStatusIcon() + description;
    }

    public String printIntoFile(){
        return "T|" + isDone + "|" + description;
    }
}
