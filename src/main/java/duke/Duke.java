package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.Scanner;
import java.util.ArrayList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Duke {
    static String horizontalLine = "--------------------------------------------------";
    static int thingsCounted = 0;
    //static Task[] tasks = new Task[100];
    public static ArrayList<Task> taskArray = new ArrayList<>();
    public static final String FILE_PATH = "duke.txt";

    public static void writeToFile(String FILE_PATH){
        try {
            File f = new File(FILE_PATH);
            FileWriter fw = new FileWriter(FILE_PATH);
            for(Task task: taskArray){
                fw.write(task.printIntoFile() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error writing file");
        }
    }

    public static void readFromFile(String FILE_PATH){
        try {
            File f = new File(FILE_PATH);
            f.createNewFile();
            Scanner sc = new Scanner(f);
            Task task;
            while(sc.hasNext()){
                String[] taskInFile = sc.nextLine().split("\\|");
                if(taskInFile[0].equals("T")){
                    task = new Todo(taskInFile[2]);
                }else if(taskInFile[0].equals("D")){
                    task = new Deadline(taskInFile[2],taskInFile[3]);
                }else{
                    task = new Event(taskInFile[2], taskInFile[3]);
                }
                thingsCounted++;
                if(taskInFile[1].equals("true")){
                    task.taskDone();
                }
                taskArray.add(task);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error reading file");
        }catch(IOException e){
            e.printStackTrace();
        }
    }

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
        if (text.equals("todo")) {
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
        } else if (text.equals("deadline")) {
            System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
        } else if (text.equals("event")) {
            System.out.println("☹ OOPS!!! The description of a event cannot be empty.");
        } else if(text.contains("done")){
            System.out.println("☹ OOPS!!! The index is out of bound");
        } else if(text.contains("delete")){
            System.out.println("☹ OOPS!!! The index is out of bound");
        } else {
            System.out.println(("☹ OOPS!!! I'm sorry, but I don't know what that means :-("));
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
        readFromFile(FILE_PATH);
        System.out.println(horizontalLine + "\nHello! I'm Duke\n" + "What can I do for you?\n" + horizontalLine);
        storeTextAndList();
        writeToFile(FILE_PATH);
    }
}