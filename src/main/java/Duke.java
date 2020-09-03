import java.util.Scanner;
public class Duke {
    static String horizontalLine = "--------------------------------------------------";
    static String[] thingsToDo = new String[100];
    static int thingsCounted = 0;

    public static void main(String[] args) {
        System.out.println(horizontalLine + "\nHello! I'm Duke\n" + "What can I do for you?\n" + horizontalLine);
        storeTextAndList();
    }

    public static void storeTextAndList() {
        String text;
        Scanner in = new Scanner(System.in);
        text = in.nextLine();
        while (!text.equals("bye")) {
            if (text.equals("list")) {
                System.out.println(horizontalLine);
                int count = 0;
                for (count = 0; count < thingsCounted; count++) {
                    System.out.print(String.format("%d. ", count + 1));
                    System.out.println(thingsToDo[count]);
                }
                System.out.println(horizontalLine);
            } else {
                System.out.println(horizontalLine);
                System.out.println("added: " + text);
                System.out.println(horizontalLine);
                thingsToDo[thingsCounted] = text;
                thingsCounted++;
            }
            text = in.nextLine();
        }
        System.out.println(horizontalLine);
        System.out.println("Bye. Hope to see you again soon");
        System.out.println(horizontalLine);
    }
}

