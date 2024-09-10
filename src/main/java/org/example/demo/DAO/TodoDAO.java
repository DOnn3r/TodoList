package org.example.demo.DAO;

import org.example.demo.entity.Todo;

import java.util.List;

public interface TodoDAO {
    void create(Todo toAdd);
    List<Todo> findAll();
    Todo readOne(int id);
    Todo update(int id, Todo toUpdate);
    void delete(int id);
}
