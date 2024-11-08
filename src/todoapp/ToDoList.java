package todoapp;

import java.io.*;
import java.util.*;

public class ToDoList {
    public List<Task> tasks;
    private final String fileName = "tasks.txt";

    public ToDoList() {
        tasks = new ArrayList<>();
        loadTasks();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void editTask(int index, String name, String description, String dueDate, String priority) {
        if (index >= 0 && index < tasks.size()) {
            Task task = tasks.get(index);
            task.setName(name);
            task.setDescription(description);
            task.setDueDate(dueDate);
            task.setPriority(priority);
        } else {
            System.out.println("Invalid task index!");
        }
    }

    public void deleteTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
        } else {
            System.out.println("Invalid task index!");
        }
    }

    public void displayTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i).toString());
            }
        }
    }

    public void sortTasksByPriority() {
        Stack<Task> stack = new Stack<>();

        for (Task task : tasks) {
            if (task.getPriority().equals("High")) {
                stack.push(task);
            }
        }
        for (Task task : tasks) {
            if (task.getPriority().equals("Medium")) {
                stack.push(task);
            }
        }
        for (Task task : tasks) {
            if (task.getPriority().equals("Low")) {
                stack.push(task);
            }
        }

        tasks.clear();
        while (!stack.isEmpty()) {
            tasks.add(stack.pop());
        }
    }

    public void sortTasksByDueDate() {
        Queue<Task> queue = new LinkedList<>();

        for (Task task : tasks) {
            if (queue.isEmpty()) {
                queue.add(task);
            } else {
                Queue<Task> tempQueue = new LinkedList<>();
                boolean added = false;

                while (!queue.isEmpty()) {
                    Task tempTask = queue.poll();
                    if (!added && task.getDueDate().compareTo(tempTask.getDueDate()) < 0) {
                        tempQueue.add(task);
                        added = true;
                    }
                    tempQueue.add(tempTask);
                }

                if (!added) {
                    tempQueue.add(task);
                }

                queue = tempQueue;
            }
        }

        tasks.clear();
        tasks.addAll(queue);
    }

    public void filterTasksByCompletion(boolean completed) {
        boolean found = false;
        for (Task task : tasks) {
            if (task.isCompleted() == completed) {
                System.out.println(task.toString());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No tasks found with the specified completion status.");
        }
    }

    public void saveTasks() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Task task : tasks) {
                writer.write(task.getName() + "," + task.getDescription() + "," + task.getDueDate() + "," +
                        task.getPriority() + "," + task.isCompleted() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    public void loadTasks() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] taskDetails = line.split(",");
                if (taskDetails.length == 5) {
                    Task task = new Task(taskDetails[0], taskDetails[1], taskDetails[2], taskDetails[3]);
                    task.setCompleted(Boolean.parseBoolean(taskDetails[4]));
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("No previous task file found, starting fresh.");
        }
    }
}
