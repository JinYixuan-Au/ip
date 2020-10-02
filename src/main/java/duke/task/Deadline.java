package duke.task;

import duke.Parser;

public class Deadline extends Task {

    protected String byDate;
    protected Parser parser;

    public Deadline(String description, String byDate) {
        super(description);
        byDate = parser.getDateFormat(byDate);
        this.byDate=byDate;
    }

    public String getTypeIcon(){
        return "[D]";
    }

    public String toString(){
        return this.getTypeIcon() + this.getStatusIcon() + description + " (by: " + byDate + ")";
    }

    public String printIntoFile(){
        return "D|" + isDone + "|" + description + "|" + this.byDate;
    }
}
