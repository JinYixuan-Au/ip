public class Deadline extends Task {

    protected String byDate;

    public Deadline(String description, String byDate) {
        super(description);
        this.byDate=byDate;
    }

    public String getTypeIcon(){
        return "[D]";
    }

    public String toString(){
        return this.getTypeIcon() + this.getStatusIcon() + description + " (by: " + byDate + ")";
    }
}
