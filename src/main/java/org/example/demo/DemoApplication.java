package org.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class DemoApplication {
    /*
    Cette application spring boot detecte tout les @Component qu'il y a dans le même dossier qu'elle, ainsi que dans tout les sous-dossiers présent
    */
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
