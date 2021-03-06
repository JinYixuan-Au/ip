package duke;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class Parser {
    /**
     *
     * @param command user's input
     * @return the parsed command line
     * @throws DukeException throw invalid or incomplete exceptions.
     */
    public static String getCommand(String command) throws DukeException {
        try{
            if (command.equals("list")) {
                return "list";
            } else if (command.equals("bye")) {
                return "bye";
            } else if ( command.equals("todo") || command.equals("deadline")
                    || command.equals("event") || command.equals("done")
                    || command.equals("delete")) {
                throw new DukeException();
            } else if (command.contains("todo")) {
                return "todo";
            } else if (command.contains("event")) {
                return "event";
            } else if (command.contains("deadline")) {
                return "deadline";
            } else if (command.contains("delete")) {
                return "delete";
            } else if (command.contains("done")) {
                return "done";
            } else if (command.contains("find")) {
                return "find";
            } else {
                throw new DukeException();
            }
        } catch (DukeException e) {
            Ui.dealWithException(command);
            return "fail";
        }
    }

    /**
     *
     * @param datetime date input
     * @return the standard format of date
     */
    public static String getDateFormat(String datetime) {
        LocalDate date;
        String dateForm;
        try {
            date = LocalDate.parse(datetime);
            dateForm = date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } catch (Exception e) {
            dateForm = datetime;
        }
        return dateForm;
    }
}
