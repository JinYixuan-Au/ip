package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public static ArrayList<Task> taskArrayList;
    private static File f;
    private static String filePath;
    public static int fileTasksCounted = 0;

    public static void writeToFile(TaskList tasks){
        try {
            FileWriter fw = new FileWriter(filePath);
            taskArrayList = tasks.getTasksList();
            for(Task task: taskArrayList) {
                fw.write(task.printIntoFile() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error writing file");
        }
    }

    public static int readFromFile(TaskList tasks){
        try {
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
                fileTasksCounted++;
                if(taskInFile[1].equals("true")){
                    task.taskDone();
                }
                tasks.add(task);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error reading file");
        }
        return fileTasksCounted;
    }

    public Storage(String filePath) throws IOException {
        f = new File(filePath);
        f.createNewFile();
        this.filePath=filePath;
    }
}