import java.util.Scanner;
import java.util.ArrayList;

public class Shaun {

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    public static class Task {
        String description;
        boolean isDone;

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
    }

    public static void main(String[] args) {
        printLine();
        System.out.println("Hello! I'm Shaun");
        System.out.println("What can I do for you?");

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
        for (int i = 0; i < taskLists.size(); i++) {
            Task task = taskLists.get(i);
            System.out.println((i + 1) + ". " + task.getStatus() + " " + task.description);
        }
        printLine();
    }

}