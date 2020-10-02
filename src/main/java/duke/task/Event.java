package duke.task;

import duke.Parser;

/**
 * The Event class is inherited from Task class
 */
public class Event extends Task{
    protected String atDate;
    protected Parser parser;

    /**
     * The constructor of Event class
     * @param description the description of the event class
     * @param atDate the date of the event
     */
    public Event(String description, String atDate) {
        super(description);
        atDate = parser.getDateFormat(atDate);
        this.atDate=atDate;
    }

    /**
     * "[E]" is the icon of event
     * @return the icon of event
     */
    public String getTypeIcon(){
        return "[E]";
    }

    public String toString() {
        return this.getTypeIcon() + this.getStatusIcon() + description + " (at: " + atDate + ")";
    }

    /**
     * The standard format to write into the file
     * @return the format
     */
    public String printIntoFile(){
        return "E|" + isDone + "|" + description + "|" + this.atDate;
    }
}
