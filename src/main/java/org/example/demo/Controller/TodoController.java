package org.example.demo.Controller;

import org.example.demo.Service.TodoService;
import org.example.demo.entity.Todo;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

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
    public Optional<Todo> getTodoById(@PathVariable Integer id) throws SQLException, ClassNotFoundException {
        return service.getTodoById(id);
    }

    @GetMapping("/search")
    public List<Todo> searchTodoByStatus(@RequestParam String status) throws SQLException, ClassNotFoundException {
        return service.searchByStatus(status);
    }

    @PostMapping("/todo")
    public void addTodo(@RequestBody Todo todo) throws SQLException, ClassNotFoundException {
        service.createTodo(todo);
    }

    @PostMapping("/update/{id}")
    public void updateTodo(@RequestBody Todo todo, @PathVariable Integer id) throws SQLException, ClassNotFoundException {
        service.updateTodo(id, todo);
    }
}
