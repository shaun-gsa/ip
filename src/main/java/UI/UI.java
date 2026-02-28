package UI;

import Task.Task;
import Task.TaskList;

public class UI {

    public void printLine() {
        System.out.println("____________________________________________________________");
    }

    public void welcomeMessage() {
        printLine();
        System.out.println("Hello! I'm Shaun");
        System.out.println("What can I do for you?");
        printLine();
    }

    public void goodbyeMessage() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    public void errorMessage(String message) {
        printLine();
        System.out.println(message);
        printLine();
    }

    public void printList(TaskList taskLists) {
        printLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskLists.size(); i++) {
            System.out.println((i + 1) + ". " + taskLists.getTask(i));
        }
        printLine();
    }

    public void showAdded(Task task, int totalTasks) {
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(" " + task);
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
        printLine();
    }

}
