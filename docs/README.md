# Shaun User Guide

Shaun is a command-line task management chatbot that aims to track tasks for a user.

## Quick Start

1. Ensure you have Java 17 or above installed.
2. Download the latest 'Shaun.jar' file.
3. Copy the file to your desired folder.
4. Open a command prompt in that folder.
5. Run the command: java -jar Shaun.jar

### Adding a Todo

Adds a simple task without any date or time.

Format: todo DESCRIPTION

Example: todo read book

### Adding a Deadline

Adds a task with a deadline.

Format: deadline DESCRIPTION /by DATE

Example: deadline submit quiz /by 2026-03-06

### Adding an Event

Adds a task that occurs within a time period.

Format: event DESCRIPTION /from START /to END

Example: event project meeting /from 2pm /to 4pm

### Listing all tasks

Displays every task name and description in the task list.

Format: list

### Marking a Task as Done

Marks the specified task as completed.

Format: mark INDEX

Example: mark 2

### Unmarking a Task as not done

Unmarks the specified task as not completed.

Format: unmark INDEX

Example: unmark 2

### Deleting a Task

Removes a task from the list.

Format: delete INDEX

Example: delete 2

### Finding Tasks

Finds tasks that contain a given keyword.

Format: find KEYWORD

Example: find book

---

## Exiting

```markdown
### Exiting the Program

Closes the application.

Format: bye