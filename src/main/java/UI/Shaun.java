package UI;

import Shaun.exception.InvalidCommandException;
import Shaun.exception.ShaunException;
import Task.Task;
import Task.Todo;
import Task.Event;
import Task.Deadline;

import java.util.Scanner;
import java.util.ArrayList;

public class Shaun {

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {
        printLine();
        System.out.println("Hello! I'm Shaun");
        System.out.println("What can I do for you?");
        printLine();

        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> taskLists = new ArrayList<>();

        while (true) {
            try {
                String userInput = scanner.nextLine();

                if (userInput.trim().isEmpty()) {
                    throw new ShaunException(
                            "Please enter a command."
                    );
                }

                if (userInput.equalsIgnoreCase("bye")) {
                    break;
                }

                if (userInput.equalsIgnoreCase("list")) {
                    printList(taskLists);
                    continue;
                }

                if (userInput.startsWith("mark ")) {
                    markTaskAsDone(userInput, taskLists);
                    continue;
                }

                if (userInput.startsWith("unmark ")) {
                    unmarkTaskNotDone(userInput, taskLists);
                    continue;
                }


                if (userInput.startsWith("todo ")) {
                    addToDo(userInput, taskLists);
                    continue;
                }

                if (userInput.startsWith("deadline ")) {
                    addDeadline(userInput, taskLists);
                    continue;
                }

                if (userInput.startsWith("event ")) {
                    addEvent(userInput, taskLists);
                    continue;
                }

                if (userInput.startsWith("delete ")) {
                    deleteTask(userInput, taskLists);
                    continue;
                }

                throw new InvalidCommandException(
                        "Command Invalid. Please enter a valid command."
                );

            } catch (ShaunException e) {
                printLine();
                System.out.println(e.getMessage());
                printLine();
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
        scanner.close();
    }

    private static void printList(ArrayList<Task> taskLists) {
        printLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskLists.size(); i++) {
            System.out.println((i + 1) + ". " + taskLists.get(i));
        }
        printLine();
    }

    private static void markTaskAsDone(String userInput, ArrayList<Task> taskLists) throws ShaunException {
        String[] parts = userInput.trim().split("\\s+");

        if (parts.length != 2) {
            throw new ShaunException("Command: mark <task number>");
        }

        int index;

        try {
            index = Integer.parseInt(parts[1]) - 1;
        } catch (NumberFormatException e) {
            throw new ShaunException("Task number invalid");
        }

        if (index < 0 || index >= taskLists.size()) {
            throw new ShaunException(("Task number out of range"));
        }

        Task task = taskLists.get(index);
        task.markDone();

        printLine();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(" " + task + " " + taskLists.get(index).description);
        printLine();
    }

    private static void unmarkTaskNotDone(String userInput, ArrayList<Task> taskLists) throws ShaunException {

        String[] parts = userInput.trim().split("\\s+");

        if (parts.length != 2) {
            throw new ShaunException("Command: unmark <task number>");
        }

        int index;


        try {
            index = Integer.parseInt(userInput.substring(7)) - 1;
        } catch (NumberFormatException e) {
            throw new ShaunException("Task number invalid");
        }

        if (index < 0 || index >= taskLists.size()) { throw new ShaunException("Task number out of range."); }
        Task task = taskLists.get(index);
        task.unmarkDone();

        printLine();
        System.out.println("Ok, I've marked this task as not done yet: ");
        System.out.println(" " + task + " " + taskLists.get(index).description);
        printLine();
    }

    private static void addToDo(String userInput, ArrayList<Task> taskLists) {
        String description = userInput.substring(5).trim();

        Task t = new Todo(description);
        taskLists.add(t);
        showAdded(t, taskLists.size());
    }

    private static void addDeadline(String userInput, ArrayList<Task> taskLists) {
        String remainder = userInput.substring(9).trim();

        String[] parts = remainder.split("\\s*/by\\s*", 2);

        String description = parts[0].trim();
        String by = parts[1].trim();

        Task t = new Deadline(description, by);
        taskLists.add(t);
        showAdded(t, taskLists.size());
    }

    private static void addEvent(String userInput, ArrayList<Task> taskLists) {
        String remainder = userInput.substring(6).trim();

        String[] fromSplit = remainder.split("\\s*/from\\s*", 2);

        String description = fromSplit[0].trim();
        String[] toSplit = fromSplit[1].split("\\s*/to\\s*", 2);

        String from = toSplit[0].trim();
        String to = toSplit[1].trim();

        Task t = new Event(description, from, to);
        taskLists.add(t);
        showAdded(t, taskLists.size());
    }

    private static void showAdded(Task task, int totalTasks) {
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(" " + task);
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
        printLine();
    }

    private static void deleteTask(String userInput, ArrayList<Task> taskLists) throws ShaunException {

        String[] parts = userInput.trim().split("\\s+");

        if (parts.length != 2) {
            throw new ShaunException("Command: delete <task number>");
        }

        int index;

        try {
            index = Integer.parseInt(parts[1]) - 1;
        } catch (NumberFormatException e) {
            throw new ShaunException("Invalid task number");
        }

        if (index < 0 || index >= taskLists.size()) {
            throw new ShaunException("Task number not in range");
        }

        Task removedTask = taskLists.remove(index);

        printLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println(" " + removedTask);
        System.out.println("Now you have " + taskLists.size() + " tasks in the list.");
        printLine();
    }
}