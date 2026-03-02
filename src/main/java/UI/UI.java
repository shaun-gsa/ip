package UI;

import Task.Task;
import Task.TaskList;

/**
 * Handles all UI interactions.
 * Helps display messages to the user.
 * */
public class UI {

    /** Output horizontal line divider. */
    public void printLine() {
        System.out.println("____________________________________________________________");
    }

    /** Greets the user when starting the program. */
    public void welcomeMessage() {
        printLine();
        System.out.println("Hello! I'm Shaun");
        System.out.println("What can I do for you?");
        printLine();
    }

    /** Ends the program with a message when user exits the application. */
    public void goodbyeMessage() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    /**
     * Informs the user about wrong input format.
     *
     * @param message Message displayed based on the wrong formatting of user input.
     * */
    public void errorMessage(String message) {
        printLine();
        System.out.println(message);
        printLine();
    }

    /**
     * Displays a numbered-list of tasks from the current task list.
     *
     * @param taskLists The task list.
     * */
    public void printList(TaskList taskLists) {
        printLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskLists.size(); i++) {
            System.out.println((i + 1) + ". " + taskLists.getTask(i));
        }
        printLine();
    }

    /**
     * Displays the description of the input task and new total tasks of the task list.
     *
     * @param task Task to be added to task list.
     * @param totalTasks Number of tasks in the appended task list.
     * */
    public void showAdded(Task task, int totalTasks) {
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(" " + task);
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
        printLine();
    }

    /**
     * Displays the task(s) that has the keyword inputted by the user.
     *
     * @param matches List of tasks that has the keyword inputted by user.
     * */
    public void printFindResults(TaskList matches) {
        printLine();

        if (matches.size() == 0) {
            System.out.println("No matching tasks found");
            printLine();
            return;
        }

        System.out.println("Here are the matching tasks in your list: ");

        for (int i = 0; i < matches.size(); i++) {
            System.out.println((i + 1) + ". " + matches.getTask(i));
        }

        printLine();
    }
}
