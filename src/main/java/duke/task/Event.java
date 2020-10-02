package duke.task;

import duke.Parser;

public class Event extends Task{
    protected String atDate;
    protected Parser parser;

    public Event(String description, String atDate) {
        super(description);
        atDate = parser.getDateFormat(atDate);
        this.atDate=atDate;
    }

    public String getTypeIcon(){
        return "[E]";
    }

    public String toString() {
        return this.getTypeIcon() + this.getStatusIcon() + description + " (at: " + atDate + ")";
    }

    public String printIntoFile(){
        return "E|" + isDone + "|" + description + "|" + this.atDate;
    }
}
