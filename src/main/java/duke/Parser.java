package duke;

public class Parser {
    public static String getCommand(String command) throws DukeException{
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
            }
            else {
                throw new DukeException();
            }
        } catch (DukeException e) {
            Ui.dealWithException(command);
            return "fail";
        }
    }
}
