package org.example.demo.Controller;

import org.example.demo.Service.TodoService;
import org.example.demo.entity.Todo;
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
        return getAllTodos();
    }

    @GetMapping("/error")
    public String error() throws SQLException, ClassNotFoundException {
        return error();
    }
    /*
    @GetMapping("/todos/{id}")
    public Todo getTodoById(int id) throws SQLException, ClassNotFoundException {

    }

    @GetMapping("/search?status=IN_PROGRESS")
    public List<Todo> searchTodoByStatus(String status) throws SQLException, ClassNotFoundException {

    }
    @GetMapping("/search?status=DONE")
    public List<Todo> searchTodoByStatus(String status) throws SQLException, ClassNotFoundException {

    }
    @PostMapping("/todo")
    public void addTodo(Todo todo) throws SQLException, ClassNotFoundException {

    }
*/
}
