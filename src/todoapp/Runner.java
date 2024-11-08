package todoapp;

import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ToDoList toDoList = new ToDoList();

        int choice;
        do {
            System.out.println("\n--- To-Do List Menu ---");
            System.out.println("1. Add Task");
            System.out.println("2. Edit Task");
            System.out.println("3. Delete Task");
            System.out.println("4. Mark Task as Completed");
            System.out.println("5. Display Tasks");
            System.out.println("6. Sort by Priority");
            System.out.println("7. Sort by Due Date");
            System.out.println("8. Filter Tasks by Completion");
            System.out.println("9. Save Tasks");
            System.out.println("10. Load Tasks");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addTask(scanner, toDoList);
                    break;
                case 2:
                    editTask(scanner, toDoList);
                    break;
                case 3:
                    deleteTask(scanner, toDoList);
                    break;
                case 4:
                    markTaskCompleted(scanner, toDoList);
                    break;
                case 5:
                    toDoList.displayTasks();
                    break;
                case 6:
                    toDoList.sortTasksByPriority();
                    break;
                case 7:
                    toDoList.sortTasksByDueDate();
                    break;
                case 8:
                    filterTasks(scanner, toDoList);
                    break;
                case 9:
                    toDoList.saveTasks();
                    System.out.println("Tasks saved.");
                    break;
                case 10:
                    toDoList.loadTasks();
                    System.out.println("Tasks loaded.");
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 0);

        scanner.close();
    }

    public static void addTask(Scanner scanner, ToDoList toDoList) {
        System.out.print("Enter task name: ");
        String name = scanner.nextLine();
        System.out.print("Enter task description: ");
        String description = scanner.nextLine();
        System.out.print("Enter task due date: ");
        String dueDate = scanner.nextLine();
        System.out.print("Enter task priority (Low/Medium/High): ");
        String priority = scanner.nextLine();

        Task task = new Task(name, description, dueDate, priority);
        toDoList.addTask(task);
    }

    public static void editTask(Scanner scanner, ToDoList toDoList) {
        System.out.print("Enter task index to edit: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();

        System.out.print("Enter new task name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new task description: ");
        String description = scanner.nextLine();
        System.out.print("Enter new task due date: ");
        String dueDate = scanner.nextLine();
        System.out.print("Enter new task priority: ");
        String priority = scanner.nextLine();

        toDoList.editTask(index, name, description, dueDate, priority);
    }

    public static void deleteTask(Scanner scanner, ToDoList toDoList) {
        System.out.print("Enter task index to delete: ");
        int index = scanner.nextInt() - 1;
        toDoList.deleteTask(index);
    }

    public static void markTaskCompleted(Scanner scanner, ToDoList toDoList) {
        System.out.print("Enter task index to mark as completed: ");
        int index = scanner.nextInt() - 1;
        toDoList.tasks.get(index).setCompleted(true);
        System.out.println("Task marked as completed.");
    }

    public static void filterTasks(Scanner scanner, ToDoList toDoList) {
        System.out.print("Show (1) completed or (2) uncompleted tasks? ");
        int option = scanner.nextInt();
        boolean completed = (option == 1);
        toDoList.filterTasksByCompletion(completed);
    }
}
