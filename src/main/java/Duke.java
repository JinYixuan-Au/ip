import java.util.Arrays;
import java.util.Scanner;
public class Duke {
    static String horizontalLine = "--------------------------------------------------";
    static int thingsCounted = 0;
    static Task[] tasks = new Task[100];

    public static void storeTextAndList() {
        String text;
        Scanner in = new Scanner(System.in);
        text = in.nextLine();
        while (!text.equals("bye")) {
            System.out.println(horizontalLine);
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
                } else {
                    addEventTask(text);
                }
                System.out.println(horizontalLine);
                text = in.nextLine();
            }
            System.out.println(horizontalLine);
            System.out.println("Bye. Hope to see you again soon");
            System.out.println(horizontalLine);
        }
    }

    public static void addTodoTask(String text){
        String todoDescription;
        todoDescription = text.substring(5);
        Task task = new Todo(todoDescription);
        tasks[thingsCounted] = task;
        thingsCounted++;
        printTask(task);
    }

    public static void addDeadlineTask(String text){
        String deadlineDescription;
        String deadlineByDate;
        int getIndex;
        getIndex = text.indexOf("/");
        deadlineDescription = text.substring(9, getIndex - 1);
        deadlineByDate = text.substring(getIndex + 4);
        Task task = new Deadline(deadlineDescription, deadlineByDate);
        tasks[thingsCounted] = task;
        thingsCounted++;
        printTask(task);
    }

    public static void addEventTask(String text){
        String eventDescription;
        String eventAtDate;
        int getIndex;
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

    public static void main(String[] args) {
        System.out.println(horizontalLine + "\nHello! I'm Duke\n" + "What can I do for you?\n" + horizontalLine);
        storeTextAndList();
    }
}