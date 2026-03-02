package UI;

import Shaun.exception.InvalidCommandException;
import Shaun.exception.ShaunException;
import Task.TaskList;
import Task.Task;
import Task.Todo;
import Task.Event;
import Task.Deadline;

/** Parses user input, then executes the commands accordingly. */
public class Parser {

    /**
     * Processes a user command and delegates execution.
     *
     * @param userInput Command entered by user on CLI.
     * @param tasks Current task list from storage file.
     * @param ui The user interface for displaying output.
     * @throws ShaunException For invalid commands.
     * */
    public static void handleCommand(String userInput, TaskList tasks, UI ui) throws ShaunException {

        if (userInput.startsWith("mark ")) {
            markTaskAsDone(userInput, tasks, ui);
            return;
        }

        if (userInput.startsWith("unmark ")) {
            unmarkTaskNotDone(userInput, tasks, ui);
            return;
        }

        if (userInput.startsWith("todo ")) {
            addToDo(userInput, tasks, ui);
            return;
        }

        if (userInput.startsWith("deadline ")) {
            addDeadline(userInput, tasks, ui);
            return;
        }

        if (userInput.startsWith("event ")) {
            addEvent(userInput, tasks, ui);
            return;
        }

        if (userInput.startsWith("delete ")) {
            deleteTask(userInput, tasks, ui);
            return;
        }

        if (userInput.startsWith("find ")) {
            findTasks(userInput, tasks, ui);
            return;
        }

        throw new InvalidCommandException("Invalid command; Please enter a valid command: ");

    }

    /**
     * Checks the specific task in the task list.
     *
     * @param userInput The full user command.
     * @param taskLists The task list.
     * @param ui The user interface.
     * */
    public static void markTaskAsDone(String userInput, TaskList taskLists, UI ui) throws ShaunException {
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

        Task task = taskLists.getTask(index);
        task.markDone();

        ui.printLine();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(" " + taskLists.getTask(index).getStatus() + " " + taskLists.getTask(index).description);
        ui.printLine();
    }

    /**
     * Unchecks the specific task in the task list.
     *
     * @param userInput The full user command.
     * @param taskLists The task list.
     * @param ui The user interface.
     * */
    public static void unmarkTaskNotDone(String userInput, TaskList taskLists, UI ui) throws ShaunException {
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
        Task task = taskLists.getTask(index);
        task.unmarkDone();
        ui.printLine();
        System.out.println("Ok, I've marked this task as not done yet: ");
        System.out.println(" " + task + " " + taskLists.getTask(index).description);
        ui.printLine();
    }

    /**
     * Adds a ToDo task to the task list.
     *
     * @param userInput The full user command.
     * @param taskLists The task list.
     * @param ui The user interface.
     * */
    public static void addToDo(String userInput, TaskList taskLists, UI ui) {
        String description = userInput.substring(5).trim();

        Task t = new Todo(description);
        taskLists.addTask(t);
        ui.showAdded(t, taskLists.size());
    }

    /**
     * Adds a Deadline task to the task list.
     *
     * @param userInput The full user command.
     * @param taskLists The task list.
     * @param ui The user interface.
     * */
    public static void addDeadline(String userInput, TaskList taskLists, UI ui) throws ShaunException {
        String remainder = userInput.substring(9).trim();

        String[] parts = remainder.split("\\s*/by\\s*", 2);

        String description = parts[0].trim();
        String by = parts[1].trim();

        Task t = new Deadline(description, by);
        taskLists.addTask(t);
        ui.showAdded(t, taskLists.size());
    }

    /**
     * Adds a Event task to the task list.
     *
     * @param userInput The full user command.
     * @param taskLists The task list.
     * @param ui The user interface.
     * */
    public static void addEvent(String userInput, TaskList taskLists, UI ui) {
        String remainder = userInput.substring(6).trim();

        String[] fromSplit = remainder.split("\\s*/from\\s*", 2);

        String description = fromSplit[0].trim();
        String[] toSplit = fromSplit[1].split("\\s*/to\\s*", 2);

        String from = toSplit[0].trim();
        String to = toSplit[1].trim();

        Task t = new Event(description, from, to);
        taskLists.addTask(t);
        ui.showAdded(t, taskLists.size());
    }

    /**
     * Deletes a specific task from the task list.
     *
     * @param userInput The full user command.
     * @param taskLists The task list.
     * @param ui The user interface.
     * */
    public static void deleteTask(String userInput, TaskList taskLists, UI ui) throws ShaunException {
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

        Task removedTask = taskLists.deleteTask(index);

        ui.printLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println(" " + removedTask);
        System.out.println("Now you have " + taskLists.size() + " tasks in the list.");
        ui.printLine();
    }

    /**
     * Finds all the tasks with a specific keyword from the task list.
     *
     * @param userInput The full user command.
     * @param taskLists The task list.
     * @param ui The user interface.
     * */
    public static void findTasks(String userInput, TaskList taskLists, UI ui) {
        String keyword = userInput.substring(5).trim();
        TaskList matches = taskLists.find(keyword);

        ui.printFindResults(matches);
    }
}
