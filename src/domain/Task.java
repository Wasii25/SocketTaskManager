package domain;

public class Task {
    private int id;
    private String title;
    private TaskStatus status;
    private String createdBy ;
    private String assignedTo;
    private TaskStatus taskStatus;

    public Task(int id, String title, String createdBy) {
        this.id = id;
        this.title = title;
        this.status = TaskStatus.OPEN;
        this.createdBy = createdBy;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getAssignedTo() {
        return assignedTo;
    }


    public int getId() {
        return id;
    }
    public TaskStatus getStatus() {
        return status;
    }
    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public String toString() {
        return id + ". " + title + " [" + status + "]";
    }
}
