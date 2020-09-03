import java.util.Scanner;
public class Duke {
    static String horizontalLine = "--------------------------------------------------";

    public static void main(String[] args) {
        System.out.println(horizontalLine + "\nHello! I'm Duke\n" + "What can I do for you?\n" + horizontalLine);
        echoCommand();
    }

    public static void echoCommand(){
        String command;
        Scanner in = new Scanner(System.in);
        command = in.nextLine();
        while(!command.equals("bye")){
            System.out.println(horizontalLine);
            System.out.println(command);
            System.out.println(horizontalLine);
            command = in.nextLine();
        }
        System.out.println(horizontalLine +"\nBye. Hope to see you again soon\n"+ horizontalLine);
    }
}
