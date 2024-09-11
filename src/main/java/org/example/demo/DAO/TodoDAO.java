package org.example.demo.DAO;

import org.example.demo.entity.Todo;

import java.util.List;
import java.util.Optional;

public interface TodoDAO {
    void create(Todo toAdd);
    List<Todo> findAll();
    Optional<Todo> readOne(int id);
    Todo update(int id, Todo toUpdate);
    void delete(int id);
    List<Todo> searchByStatus(String status);
}
