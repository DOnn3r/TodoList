package org.example.demo.DAO;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class DatabaseConnection {
    @Bean
    public Connection getConnection(){
        Connection connection = null;

        try{
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/todolist",
                    "postgres",
                    "Nomena7805."
            );
        }catch(SQLException e){
            e.printStackTrace();
        }
        return connection;
    }
}
