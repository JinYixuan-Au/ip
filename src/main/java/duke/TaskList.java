package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;
import static java.util.stream.Collectors.toList;

public class TaskList {
    public static ArrayList<Task> tasksList;

    public TaskList(){
        this.tasksList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasksList){
        this.tasksList = tasksList;
    }

    public void add(Task task){
        tasksList.add(task);
    }

    public Task remove(int index){
        Task removeTask = tasksList.remove(index);
        return removeTask;
    }

    public Task get(int index){
        Task getTask=tasksList.get(index);
        return getTask;
    }

    public ArrayList<Task> getTasksList(){
        return tasksList;
    }

    public static void listTasks() {
        int count = 1;
        for(Task task : tasksList){
            System.out.print(count + ". ");
            System.out.println(task);
            count++;
        }
    }

    public static void findTasks(String command) {
        String findText = command.substring(4);
        ArrayList<Task> findTasks = (ArrayList<Task>) tasksList.stream()
                .filter((t) -> t.getDescription().contains(findText))
                .collect(toList());
        System.out.println("Here are the matching tasks in your list:");
        int count = 1;
        for(Task task : findTasks){
            System.out.print(count + ". ");
            System.out.println(task);
            count++;
        }
        if(count==1){
            System.out.println("no matching result");
        }
    }

    public static void addTodoTask(String command) throws DukeException{
        String todoDescription;
        todoDescription = command.substring(4);
        Task task = new Todo(todoDescription);
        tasksList.add(task);
        Ui.printTask(task);
    }

    public static void addDeadlineTask(String command) throws DukeException{
        String deadlineDescription;
        String deadlineDate;
        int index;
        index = command.indexOf("/");
        deadlineDescription = command.substring(9,index-1);
        deadlineDate = command.substring(index+4);
        Task task = new Deadline(deadlineDescription, deadlineDate);
        tasksList.add(task);
        Ui.printTask(task);

    }

    public static void addEventTask(String command) throws DukeException{
        String eventDescription;
        String eventDate;
        int index;
        index = command.indexOf("/");
        eventDescription = command.substring(6,index-1);
        eventDate = command.substring(index+4);
        Task task = new Event(eventDescription, eventDate);
        tasksList.add(task);
        Ui.printTask(task);
    }

    public static void markTaskAsDone(String command, int countTasks) throws DukeException{
        int index = Integer.parseInt(command.substring(4));
        if(index > countTasks){
            throw new DukeException();
        }
        tasksList.get(index-1).taskDone();
        System.out.println("Nice! I've marked this task as done:\n" + tasksList.get(index-1));
    }

    public static void delete(String command, int tasksCounted) throws DukeException {
        int index = Integer.parseInt(command.substring(6));
        if(index > tasksCounted){
            throw new DukeException();
        }
        Task task = tasksList.remove(index - 1);
        tasksCounted--;
        System.out.println("Noted. I've removed this task: ");
        System.out.println(task);
        System.out.println("Now you have " + tasksCounted + " tasks in the list.");
    }
}