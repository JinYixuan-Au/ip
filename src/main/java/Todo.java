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
}