package duke;

import java.io.IOException;

/**
 * This is the main class of the application.
 * This application can store things to do, events and deadlines.
 * It can add and delete tasks, list tasks, search task by keywords, and mark tasks as done.
 */
public class Duke {
    private static Storage storage;
    private static TaskList tasks;
    private static Ui ui;
    private static final String FILE_PATH="duke.txt";
    public static int thingsCounted = 0;

    /**
     *
     * @param filePath the path you are going to store the data
     * @throws IOException throw the input or output exception
     */
    public Duke(String filePath) throws IOException {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList();
    }

    public void run() throws DukeException {
        thingsCounted = storage.readFromFile(tasks);
        ui.start();
        ui.count(thingsCounted);
        ui.doTasks(tasks);
        ui.end();
        storage.writeToFile(tasks);
    }

    /**
     *
     * @param args
     * @throws DukeException throw the exception
     * @throws IOException throw the inout or output exception
     */
    public static void main(String[] args) throws DukeException, IOException {
        new Duke(FILE_PATH).run();
    }
}