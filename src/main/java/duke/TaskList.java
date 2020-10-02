package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;
import static java.util.stream.Collectors.toList;

public class TaskList {
    public static ArrayList<Task> tasksList;

    //The Constructor of TaskList
    public TaskList(){
        this.tasksList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasksList){
        this.tasksList = tasksList;
    }

    /**
     * Add a new task to the taskList
     * @param task the task to be added
     */
    public void add(Task task){
        tasksList.add(task);
    }

    /**
     * Remove the task fro the taskList
     * @param index the task to be removed
     * @return the remove task
     */
    public Task remove(int index){
        Task removeTask = tasksList.remove(index);
        return removeTask;
    }

    /**
     * Get the task with a particular index
     * @param index the index of the task
     * @return the task with the particular index
     */
    public Task get(int index){
        Task getTask=tasksList.get(index);
        return getTask;
    }

    public ArrayList<Task> getTasksList(){
        return tasksList;
    }

    //Print the list of tasks
    public static void listTasks() {
        int count = 1;
        for(Task task : tasksList){
            System.out.print(count + ". ");
            System.out.println(task);
            count++;
        }
    }

    /**
     * Find a task with given keywords
     * @param command keywords of the task to be found
     */
    public static void findTasks(String command) {
        String findText = command.substring(5);
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

    /**
     * Add a task to do
     * @param command description of the task to do
     * @throws DukeException throw the DukeException
     */
    public static void addTodoTask(String command) throws DukeException{
        String todoDescription;
        todoDescription = command.substring(4);
        Task task = new Todo(todoDescription);
        tasksList.add(task);
        Ui.printTask(task);
    }

    /**
     * Add a deadline task
     * @param command description of the deadline task
     * @throws DukeException throw the DukeException
     */
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

    /**
     * Add an event
     * @param command description of the event
     * @throws DukeException
     */
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

    /**
     * Mark the task as done
     * @param command the index of the task done
     * @param countTasks avoid string out of bound
     * @throws DukeException
     */
    public static void markTaskAsDone(String command, int countTasks) throws DukeException{
        int index = Integer.parseInt(command.substring(4));
        if(index > countTasks){
            throw new DukeException();
        }
        tasksList.get(index-1).taskDone();
        System.out.println("Nice! I've marked this task as done:\n" + tasksList.get(index-1));
    }

    /**
     * Delete a task in the taskList
     * @param command index of the task
     * @param tasksCounted avoid sting out of bound
     * @throws DukeException
     */
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