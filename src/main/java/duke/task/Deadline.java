package duke.task;

import duke.Parser;

/**
 * Deadline class is inherited from Task class
 */
public class Deadline extends Task {

    protected String byDate;
    protected Parser parser;

    /**
     * Constructor of Deadline class
     * @param description the description of the deadline class
     * @param byDate the date of deadline
     */
    public Deadline(String description, String byDate) {
        super(description);
        byDate = parser.getDateFormat(byDate);
        this.byDate=byDate;
    }

    public String getTypeIcon(){
        return "[D]";
    }

    /**
     * Change into a standard format
     * @return the standard format of the deadline
     */
    public String toString(){
        return this.getTypeIcon() + this.getStatusIcon() + description + " (by: " + byDate + ")";
    }

    public String printIntoFile(){
        return "D|" + isDone + "|" + description + "|" + this.byDate;
    }
}
