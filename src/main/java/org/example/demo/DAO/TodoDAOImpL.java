package org.example.demo.DAO;

import org.example.demo.entity.Priority;
import org.example.demo.entity.Status;
import org.example.demo.entity.Todo;
import org.springframework.stereotype.Repository;

import javax.print.attribute.standard.DateTimeAtCompleted;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TodoDAOImpL implements TodoDAO{
    private DatabaseConnection db;

    public TodoDAOImpL(DatabaseConnection db) {
        this.db = db;
    }

    @Override
    public void create(Todo toAdd) {

    }

    @Override
    public List<Todo> findAll() {
        List<Todo> allTodos = new ArrayList<Todo>();
        try(Statement stm = db.getConnection().createStatement()){
            String sql = "select * from todo";

            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
                Todo toAdd = new Todo(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString(("description")),
                        rs.getObject("creationdate", LocalDateTime.class),
                        rs.getObject("deadline", LocalDateTime.class),
                        rs.getObject("executiondate", LocalDateTime.class),
                        Priority.valueOf(rs.getString("priority")),
                        Status.valueOf(rs.getString("status"))
                );
                allTodos.add(toAdd);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return allTodos;
    }

    @Override
    public Todo readOne(int id) {
        return null;
    }

    @Override
    public Todo update(int id, Todo toUpdate) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}
