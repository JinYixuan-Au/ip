package duke;

import duke.task.Task;

import java.util.Scanner;

public class Ui {
    public Parser parser;
    public static int tasksCounted = 0;

    public void count(int thingsCounted){
        this.tasksCounted =thingsCounted;
    }

    public void doTasks(TaskList tasks) throws DukeException{
        String input;
        Scanner in = new Scanner(System.in);
        while(in.hasNextLine()){
            input = in.nextLine();
            Ui.printLine();
            String command = parser.getCommand(input);
            switch (command) {
                case "list":
                    tasks.listTasks();
                    break;
                case "done":
                    tasks.markTaskAsDone(command, tasksCounted);
                    break;
                case "todo":
                    tasksCounted++;
                    tasks.addTodoTask(command);
                    break;
                case "deadline":
                    tasksCounted++;
                    tasks.addDeadlineTask(command);
                    break;
                case "event":
                    tasksCounted++;
                    tasks.addEventTask(command);
                    break;
                case "delete":
                    tasks.delete(command, tasksCounted);
                    break;
                case "find":
                    tasks.findTasks(command);
                    break;
                case "bye":
                    return;
            }
            Ui.printLine();
        }
    }

    public static void printTask(Task task){
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + tasksCounted + " tasks in the list.");
    }

    public static void dealWithException(String inputLine){
        if(inputLine.equals("todo")){
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
        }else if(inputLine.equals("deadline")){
            System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
        }else if(inputLine.equals("event")) {
            System.out.println("☹ OOPS!!! The description of a event cannot be empty.");
        }else if(inputLine.contains("done")) {
            System.out.println("☹ OOPS!!! The done index is out of bound.");
        }else if(inputLine.contains("delete")) {
            System.out.println("☹ OOPS!!! The delete index is out of bound.");
        }else{
            System.out.println(("☹ OOPS!!! I'm sorry, but I don't know what that means :-("));
        }
    }

    public static void end(){
        System.out.println("Bye. Hope to see you again soon");
        printLine();
    }

    public static void printLine(){
        System.out.println("____________________________________________________________");
    }

    public static void start(){
        String startGreet = "____________________________________________________________\n" +
                " Hello! I'm Duke\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";

        System.out.println(startGreet);
    }
}