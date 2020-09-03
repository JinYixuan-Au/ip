public class Event extends Task{
    protected String atDate;

    public Event(String description, String atDate) {
        super(description);
        this.atDate=atDate;
    }

    public String getTypeIcon(){
        return "[D]";
    }

    public String toString(){
        return this.getTypeIcon() + this.getStatusIcon() + description + " (at: " + atDate + ")";
    }
}
