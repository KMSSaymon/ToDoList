package todoapp;

public class Task {
    private String name;
    private String description;
    private String dueDate;
    private String priority;
    private boolean isCompleted;

    // Constructor
    public Task(String name, String description, String dueDate, String priority) {
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.isCompleted = false; // By default, task is not completed.
    }

    // Getters and Setters
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getDueDate()
    {
        return dueDate;
    }
    public void setDueDate(String dueDate)
    {
        this.dueDate = dueDate;
    }

    public String getPriority()
    {
        return priority;
    }
    public void setPriority(String priority)
    {
        this.priority = priority;
    }

    public boolean isCompleted()
    {
        return isCompleted;
    }
    public void setCompleted(boolean completed)
    {
        isCompleted = completed;
    }

    @Override
    public String toString()
    {
        return "Task: " + name +
                "\nDescription: " + description +
                "\nDue Date: " + dueDate +
                "\nPriority: " + priority +
                "\nCompleted: " + (isCompleted ? "Yes" : "No");
    }
}
