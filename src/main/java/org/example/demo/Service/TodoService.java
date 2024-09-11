package org.example.demo.Service;

import org.example.demo.DAO.TodoDAO;
import org.example.demo.DAO.TodoDAOImpL;
import org.example.demo.entity.Status;
import org.example.demo.entity.Todo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    private final TodoDAO dao;

    public TodoService(TodoDAOImpL dao){
        this.dao = dao;
    }

    public void createTodo(Todo todo){
        dao.create(todo);
    }

    public List<Todo> getAllTodos(){
        return dao.findAll();
    }

    public Optional<Todo> getTodoById(int id){
        return dao.readOne(id);
    }

    public void updateTodo(int id,Todo todo){
        dao.update(id, todo) ;
    }

    public void deleteTodo(int id){
        dao.delete(id);
    }

    public List<Todo> searchByStatus(String status) {
        return dao.searchByStatus(status);
    }

}