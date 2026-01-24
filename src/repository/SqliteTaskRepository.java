package repository;

import domain.Task;
import domain.TaskStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqliteTaskRepository implements TaskRepository{
    private final Connection connection;

    public SqliteTaskRepository() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:taskserver.db");
            initSchema();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void initSchema() throws SQLException {
        String sql = """
            CREATE TABLE IF NOT EXISTS tasks (
                id INTEGER PRIMARY KEY,
                title TEXT NOT NULL,
                status TEXT NOT NULL,
                created_by TEXT NOT NULL,
                assigned_to TEXT
            );
        """;
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        }
    }


        @Override
        public Task save(Task task) {
            String sql = """
            INSERT INTO tasks (id, title, status, created_by, assigned_to)
            VALUES (?, ?, ?, ?, ?)
        """;

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setInt(1, task.getId());
                ps.setString(2, task.getTitle());
                ps.setString(3, task.getStatus().name());
                ps.setString(4, task.getCreatedBy());
                ps.setString(5, task.getAssignedTo());
                ps.executeUpdate();
                return task;
            } catch (SQLException e) {
                System.out.println(e.toString());
                throw new RuntimeException(e);
            }
        }

    @Override
    public List<Task> findAll() {
        return List.of();
    }

    @Override
        public List<Task> findVisibleTasksFor(String username) {
            String sql = """
            SELECT * FROM tasks
            WHERE created_by = ? OR assigned_to = ?
        """;

            List<Task> result = new ArrayList<>();

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, username);
                ps.setString(2, username);

                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    result.add(mapRow(rs));
                }
            } catch (SQLException e) {
                System.out.println("findvisible tasks for");
                throw new RuntimeException(e);
            }

            return result;
        }

        private Task mapRow(ResultSet rs) throws SQLException {
            Task task = new Task(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("created_by")
            );
            task.setStatus(TaskStatus.valueOf(rs.getString("status")));
            return task;
        }
}
