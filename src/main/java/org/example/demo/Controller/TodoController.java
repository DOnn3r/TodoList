package org.example.demo.Controller;

import org.example.demo.Service.TodoService;
import org.example.demo.entity.Todo;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
public class TodoController {
    private TodoService service;

    //injection de dependance: il faut fournir une instance de service pour créer le controller
    public TodoController(TodoService service) {
        this.service = service;
    }
    //IoC (Inversion of control): on laisse Spring gérer nos objets (il fera new pour nous)
    @GetMapping("/todos")
    public List<Todo> getAllTodos() throws SQLException, ClassNotFoundException {
        return service.getAllTodos();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTodo(int id) throws SQLException, ClassNotFoundException {
        service.deleteTodo(id);
    }

    @GetMapping("/todos/{id}")
    public Todo getTodoById(int id) throws SQLException, ClassNotFoundException {
        return service.getTodoById(id);
    }

    @GetMapping("/search?status=IN_PROGRESS")
    public List<Todo> searchTodoInProgress() throws SQLException, ClassNotFoundException {
        return service.searchInProgress();
    }

    @GetMapping("/search?status=DONE")
    public List<Todo> searchTodoDone() throws SQLException, ClassNotFoundException {
        return service.searchDone();
    }

    @PostMapping("/todo")
    public void addTodo(Todo todo) throws SQLException, ClassNotFoundException {
        service.createTodo(todo);
    }

}
