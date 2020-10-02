package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.Scanner;

public class Duke {
    static String horizontalLine = "--------------------------------------------------";
    static int thingsCounted = 0;
    static Task[] tasks = new Task[100];

    public static void storeTextAndList() throws DukeException {
        String text;
        Scanner in = new Scanner(System.in);
        text = in.nextLine();
        while (!text.equals("bye")) {
            System.out.println(horizontalLine);
            try {
                if (text.equals("list")) {
                    listTask();
                } else if (text.contains("done")) {
                    int index = Integer.parseInt(text.substring(5));
                    doneTask(index);
                } else {
                    if (text.contains("todo")) {
                        addTodoTask(text);
                    } else if (text.contains("deadline")) {
                        addDeadlineTask(text);
                    } else if (text.contains("event")) {
                        addEventTask(text);
                    } else {
                        throw new DukeException();
                    }
                }
            }catch (DukeException e) {
                dealWithException(text);
            }
                System.out.println(horizontalLine);
                text = in.nextLine();
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
        tasks[thingsCounted] = task;
        thingsCounted++;
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
        tasks[thingsCounted] = task;
        thingsCounted++;
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
        tasks[thingsCounted] = task;
        thingsCounted++;
        printTask(task);
    }

    public static void printTask(Task task){
        System.out.println("Got it. I've added this task: ");
        System.out.println(task);
        System.out.println("Now you have " + thingsCounted + " tasks in the list.");
    }


    public static void doneTask(int index){
        tasks[index - 1].taskDone();
        System.out.println("Nice! I've marked this task as done:\n" + tasks[index - 1]);
    }

    public static void listTask(){
        System.out.println(horizontalLine);
        System.out.println("Here are the tasks in your list:");
        for (int count = 0; count < thingsCounted; count++) {
            System.out.println((count+1)+"."+tasks[count]);
        }
        System.out.println(horizontalLine);
    }

    public static void main(String[] args) throws DukeException {
        System.out.println(horizontalLine + "\nHello! I'm Duke.Duke\n" + "What can I do for you?\n" + horizontalLine);
        storeTextAndList();
    }

}