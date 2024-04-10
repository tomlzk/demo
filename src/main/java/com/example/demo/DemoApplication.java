package com.example.demo;

import com.example.game.LoginFrame;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.swing.*;

@MapperScan("com.example.mapper")
@ComponentScan("com.example")
@SpringBootApplication
public class DemoApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(DemoApplication.class, args);
        SwingUtilities.invokeLater(() -> {
            new LoginFrame();
        });
    }

}
