package org.example.demo.Service;

import org.example.demo.DAO.TodoDAOImpL;
import org.example.demo.entity.Todo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoService {
    private TodoDAOImpL dao;

    public TodoService(TodoDAOImpL dao){
        this.dao = dao;
    }

    public List<Todo> getAllTodos(){
        return List.of();
    }
}