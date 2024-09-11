package org.example.demo.DAO;

import org.example.demo.entity.Priority;
import org.example.demo.entity.Status;
import org.example.demo.entity.Todo;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TodoDAOImpL implements TodoDAO{
    private DatabaseConnection db;

    public TodoDAOImpL(DatabaseConnection db) {
        this.db = db;
    }

    @Override
    public void create(Todo toAdd) {
        String query = "INSERT INTO todo (id, title, description, creationdate, deadline, executiondate, priority, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stm = db.getConnection().prepareStatement(query)) {
            stm.setInt(1, toAdd.getId());
            stm.setString(2, toAdd.getTitle());
            stm.setString(3, toAdd.getDescription());
            stm.setTimestamp(4, Timestamp.valueOf(toAdd.getCreationDate()));
            stm.setTimestamp(5, Timestamp.valueOf(toAdd.getDeadline()));
            stm.setTimestamp(6, Timestamp.valueOf(toAdd.getExecutionDate()));
            stm.setString(7, toAdd.getPriority().name());
            stm.setString(8, toAdd.getStatus().name());

            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Todo> findAll() {
        List<Todo> allTodos = new ArrayList<>();
        String query = "select * from todo";

        try(Statement stm = db.getConnection().createStatement();
            ResultSet rs = stm.executeQuery(query))
        {
            while(rs.next()){
                allTodos.add(new Todo(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString(("description")),
                        rs.getObject("creationdate", LocalDateTime.class),
                        rs.getObject("deadline", LocalDateTime.class),
                        rs.getObject("executiondate", LocalDateTime.class),
                        Priority.valueOf(rs.getString("priority")),
                        Status.valueOf(rs.getString("status"))
                ));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return allTodos;
    }

    @Override
    public Optional<Todo> readOne(int id) {
        String query = "select * from todo where id = ?";
        Todo todo = null;
        try (PreparedStatement stm = db.getConnection().prepareStatement(query)) {
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                todo = new Todo(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getObject("creationdate", LocalDateTime.class),
                        rs.getObject("deadline", LocalDateTime.class),
                        rs.getObject("executiondate", LocalDateTime.class),
                        Priority.valueOf(rs.getString("priority")),
                        Status.valueOf(rs.getString("status"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(todo);
    }

    @Override
    public Todo update(int id, Todo toUpdate) {
        String query = "UPDATE todo SET title = ?, description = ?, creationdate = ?, deadline = ?, executiondate = ?, priority = ?, status = ? WHERE id = ?";

        try (PreparedStatement pstmt = db.getConnection().prepareStatement(query)) {
            pstmt.setString(1, toUpdate.getTitle());
            pstmt.setString(2, toUpdate.getDescription());
            pstmt.setObject(3, toUpdate.getCreationDate());
            pstmt.setObject(4, toUpdate.getDeadline());
            pstmt.setObject(5, toUpdate.getExecutionDate());
            pstmt.setString(6, toUpdate.getPriority().name());
            pstmt.setString(7, toUpdate.getStatus().name());
            pstmt.setInt(8, id);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                return toUpdate;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM todo WHERE id = " + id;
        try (PreparedStatement stm = db.getConnection().prepareStatement(query)){
            stm.setInt(1, id);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Todo> searchByStatus(String status) {
        List<Todo> allTodos = new ArrayList<>();
        String query = "select * from todo where status = ?";
        try (PreparedStatement stm = db.getConnection().prepareStatement(query)) {
            stm.setString(1, status);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Todo toAdd = new Todo(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getObject("creationdate", LocalDateTime.class),
                        rs.getObject("deadline", LocalDateTime.class),
                        rs.getObject("executiondate", LocalDateTime.class),
                        Priority.valueOf(rs.getString("priority")),
                        Status.valueOf(rs.getString("status"))
                );
                allTodos.add(toAdd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allTodos;
    }

}
