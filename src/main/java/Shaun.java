import java.util.Scanner;
import java.util.ArrayList;

public class Shaun {

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    public static class Task {
        protected String description;
        protected boolean isDone;

        Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        void markDone() {
            isDone = true;
        }

        void unmarkDone() {
            isDone = false;
        }

        String getStatus() {
            return isDone ? "[X]" : "[ ]";
        }

        @Override
        public String toString() {
            return getStatus() + " " + description;
        }
    }

    public static void main(String[] args) {
        printLine();
        System.out.println("Hello! I'm Shaun");
        System.out.println("What can I do for you?");
        printLine();

        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> taskLists = new ArrayList<>();

        while (true) {
            String userInput = scanner.nextLine();

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

            if (userInput.equalsIgnoreCase("bye")) {
                break;
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

            taskLists.add(new Task(userInput));
            printLine();
            System.out.println("added: " + userInput);
            printLine();

        }

        System.out.println("Bye. Hope to see you again soon!");
        scanner.close();
    }

    private static void unmarkTaskNotDone(String userInput, ArrayList<Task> taskLists) {
        int index = Integer.parseInt(userInput.substring(7)) - 1;

        if (index >= 0 && index < taskLists.size()) {
            taskLists.get(index).unmarkDone();

            printLine();
            System.out.println("Ok, I've marked this task as not done yet: ");
            System.out.println(" " + taskLists.get(index).getStatus() + " " + taskLists.get(index).description);
            printLine();
        }
    }

    private static void markTaskAsDone(String userInput, ArrayList<Task> taskLists) {
        int index = Integer.parseInt(userInput.substring(5)) - 1;

        if (index >= 0 && index < taskLists.size()) {
            taskLists.get(index).markDone();

            printLine();
            System.out.println("Nice! I've marked this task as done: ");
            System.out.println(" " + taskLists.get(index).getStatus() + " " + taskLists.get(index).description);
            printLine();
        }
    }

    private static void printList(ArrayList<Task> taskLists) {
        printLine();
        System.out.println("Here are the tasks in yuor list:");
        for (int i = 0; i < taskLists.size(); i++) {
            System.out.println((i + 1) + ". " + taskLists.get(i));
        }
        printLine();
    }

    public static class Deadline extends Task {
        protected String by;

        public Deadline(String description, String by) {
            super(description);
            this.by = by;
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + " (by: " + by + ")";
        }
    }

    public static class Event extends Task {
        protected String from;
        protected String to;

        public Event(String description, String from, String to) {
            super(description);
            this.from = from;
            this.to = to;
        }

        @Override
        public String toString() {
            return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
        }
    }

    public static class Todo extends Task {
        public Todo(String description) {
            super(description);
        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
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
}