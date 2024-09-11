package org.example.demo.Service;

import org.example.demo.DAO.TodoDAO;
import org.example.demo.DAO.TodoDAOImpL;
import org.example.demo.entity.Status;
import org.example.demo.entity.Todo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {
    private TodoDAO dao;

    public TodoService(TodoDAOImpL dao){
        this.dao = dao;
    }

    public void createTodo(Todo todo){
        dao.create(todo);
    }
    public List<Todo> getAllTodos(){
        return dao.findAll();
    }

    public Todo getTodoById(int id){
        return dao.readOne(id);
    }

    public void updateTodo(int id,Todo todo){
        dao.update(id, todo) ;
    }

    public void deleteTodo(int id){
        dao.delete(id);
    }

    public List<Todo> searchInProgress(){
        String status1 = "IN_PROGRESS";
        return dao.searchByStatus(status1);
    }

    public List<Todo> searchDone(){
        String status1 = "DONE";
        return dao.searchByStatus(status1);
    }
}