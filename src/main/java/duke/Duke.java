package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    static String horizontalLine = "--------------------------------------------------";
    static int thingsCounted = 0;
    //static Task[] tasks = new Task[100];
    public static ArrayList<Task> taskArray = new ArrayList<>();

    public static void storeTextAndList() throws DukeException {
        String command;
        Scanner in = new Scanner(System.in);
        command = in.nextLine();
        while (!command.equals("bye")) {
            System.out.println(horizontalLine);
            try {
                if (command.equals("list")) {
                    listTask();
                } else if (command.contains("done")) {
                    int index = Integer.parseInt(command.substring(5));
                    if(index > thingsCounted){
                        throw new DukeException();
                    }
                    markTaskAsDone(index);
                } else if (command.contains("todo")) {
                    addTodoTask(command);
                } else if (command.contains("deadline")) {
                    addDeadlineTask(command);
                } else if (command.contains("event")) {
                    addEventTask(command);
                } else if (command.contains("delete")){
                    int index = Integer.parseInt(command.substring(7));
                    if(index > thingsCounted){
                        throw new DukeException();
                    }
                    deleteItem(index);
                } else {
                    throw new DukeException();
                }
            }catch (DukeException e) {
                dealWithException(command);
            }
                System.out.println(horizontalLine);
                command = in.nextLine();
            }
            System.out.println(horizontalLine);
            System.out.println("Bye. Hope to see you again soon");
            System.out.println(horizontalLine);
    }

    public static void dealWithException(String text){
        switch (text) {
            case "todo":
                System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                break;
            case "deadline":
                System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                break;
            case "event":
                System.out.println("☹ OOPS!!! The description of a event cannot be empty.");
                break;
            default:
                System.out.println(("☹ OOPS!!! I'm sorry, but I don't know what that means :-("));
                break;
        }
    }

    public static void addTodoTask(String text) throws DukeException {
        String todoDescription;
        if(text.equals("todo")){
            throw new DukeException();
        }
        todoDescription = text.substring(5);
        Task task = new Todo(todoDescription);
        //tasks[thingsCounted] = task;
        thingsCounted++;
        taskArray.add(task);
        printTask(task);
    }

    public static void addDeadlineTask (String text) throws DukeException {
        String deadlineDescription;
        String deadlineByDate;
        int getIndex;
        if(text.equals("deadline")){
            throw new DukeException();
        }
        getIndex = text.indexOf("/");
        deadlineDescription = text.substring(9, getIndex - 1);
        deadlineByDate = text.substring(getIndex + 4);
        Task task = new Deadline(deadlineDescription, deadlineByDate);
        //tasks[thingsCounted] = task;
        thingsCounted++;
        taskArray.add(task);
        printTask(task);
    }

    public static void addEventTask(String text) throws DukeException {
        String eventDescription;
        String eventAtDate;
        int getIndex;
        if(text.equals("event")){
            throw new DukeException();
        }
        getIndex = text.indexOf("/");
        eventDescription = text.substring(6, getIndex - 1);
        eventAtDate = text.substring(getIndex + 4);
        Task task = new Event(eventDescription, eventAtDate);
        //tasks[thingsCounted] = task;
        thingsCounted++;
        taskArray.add(task);
        printTask(task);
    }

    public static void printTask(Task task){
        System.out.println("Got it. I've added this task: ");
        System.out.println(task);
        System.out.println("Now you have " + thingsCounted + " tasks in the list.");
    }


    public static void markTaskAsDone(int index){
        taskArray.get(index-1).taskDone();
        System.out.println("Nice! I've marked this task as done:\n" +taskArray.get(index-1));
    }

    private static void deleteItem(int index) {
        Task task = taskArray.remove(index - 1);
        System.out.println("Noted. I've removed this task: ");
        System.out.println(task);
        thingsCounted--;
        System.out.println("Now you have " + thingsCounted + " tasks in the list.");
    }

    public static void listTask(){
        System.out.println(horizontalLine);
        System.out.println("Here are the tasks in your list:");
        int count = 1;
        for(Task task : taskArray){
            System.out.print(count + ". ");
            System.out.println(task);
            count++;
        }
        System.out.println(horizontalLine);
    }

    public static void main(String[] args) throws DukeException {
        System.out.println(horizontalLine + "\nHello! I'm Duke\n" + "What can I do for you?\n" + horizontalLine);
        storeTextAndList();
    }
}