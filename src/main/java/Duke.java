import java.util.Arrays;
import java.util.Scanner;
public class Duke {
    static String horizontalLine = "--------------------------------------------------";
    static String[] thingsToDo = new String[100];
    static int thingsCounted = 0;
    static Task [] tasks=new Task[100];

    public static void main(String[] args) {
        System.out.println(horizontalLine + "\nHello! I'm Duke\n" + "What can I do for you?\n" + horizontalLine);
        storeTextAndList();
    }

    public static void storeTextAndList() {
        String text;
        Scanner in = new Scanner(System.in);
        text = in.nextLine();
        while (!text.equals("bye")) {
            System.out.println(horizontalLine);
            if (text.equals("list")) {
                int count = 0;
                for (count = 0; count < thingsCounted; count++) {
                    System.out.print(String.format("%d. ",count+1));
                    System.out.println(tasks[count].getStatusIcon() + thingsToDo[count]);
                }
                System.out.println(horizontalLine);
            }
            else if(text.contains("done")){
                int index = Integer.parseInt(text.substring(5));
                doneTask(index);
            }
            else {
                System.out.println("added: " + text);
                thingsToDo[thingsCounted] = text;
                Task task = new Task(text);
                tasks[thingsCounted] = task;
                thingsCounted++;
            }
            System.out.println(horizontalLine);
            text = in.nextLine();
        }
        System.out.println(horizontalLine);
        System.out.println("Bye. Hope to see you again soon");
        System.out.println(horizontalLine);
    }

    public static void doneTask(int index){
        tasks[index-1].taskDone();
        System.out.println("Nice! I've marked this task as done:\n" +tasks[index-1].getStatusIcon()+thingsToDo[index-1]);
    }
}

