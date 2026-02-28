package UI;

import Shaun.exception.ShaunException;
import Task.TaskList;

import java.io.File;
import java.util.Scanner;

public class Shaun {

    private static final UI ui = new UI();
    private static final String FILE_PATH =
            "." + File.separator + "data" + File.separator + "shaun.txt";

    public static void main(String[] args) {

        ui.welcomeMessage();

        Scanner scanner = new Scanner(System.in);
        Storage storage = new Storage(FILE_PATH);
        TaskList taskLists = storage.loadTasks();

        while (true) {
            try {
                String userInput = scanner.nextLine();

                if (userInput.trim().isEmpty()) {
                    throw new ShaunException("Please enter a command.");
                }

                if (userInput.equalsIgnoreCase("bye")) {
                    break;
                }

                if (userInput.equalsIgnoreCase("list")) {
                    ui.printList(taskLists);
                    continue;
                }

                Parser.handleCommand(userInput, taskLists, ui);
                storage.saveTasks(taskLists);

            } catch (ShaunException e) {
                ui.errorMessage(e.getMessage());
            }
        }

        ui.goodbyeMessage();
        scanner.close();
    }

}