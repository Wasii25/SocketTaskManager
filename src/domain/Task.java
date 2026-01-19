package domain;

public class Task {
    private int id;
    private String title;
    private TaskStatus status;
    private String createdBy = null;
    private String assignedTo = null;
    private TaskStatus taskStatus;

    public Task(int id, String title) {
        this.id = id;
        this.title = title;
        this.status = TaskStatus.OPEN;
    }

    public int getId() {
        return id;
    }
    public TaskStatus getStatus() {
        return status;
    }
    public void setStatus(String status) {
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
        return id + ". " + title + "[" + status + "]";
    }
}
