package duke.task;

/**
 * The Todo class is inherited from the Task class
 */
public class Todo extends Task{

    /**
     * Constructor of the Todo class
     * @param description the description of the todo task
     */
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
