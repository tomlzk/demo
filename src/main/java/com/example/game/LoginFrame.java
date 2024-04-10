package com.example.game;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@MapperScan("com.example.mapper")
@ComponentScan("com.example")
//@SpringBootApplication
public class LoginFrame extends JFrame
{
    private JPanel panel = new JPanel();
    private JTextField username = new JTextField();
    private JTextField password = new JTextField();

    public UserService userService = new UserService();

    public LoginFrame()
    {
        //窗口初始化
        setTitle("登录");
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        //panel.setSize(450, 300);
        setContentPane(panel);
        panel.setLayout(null);

        //用户名标签
        JLabel usernameLabel = new JLabel("用户名:");
        usernameLabel.setBounds(20, 20, 80, 25);
        panel.add(usernameLabel);

        //密码标签
        JLabel passwordLabel = new JLabel("密码:");
        passwordLabel.setBounds(20, 60, 80, 25);
        panel.add(passwordLabel);

        //用户名输入文本框
        username.setBounds(100, 20, 200, 25);
        panel.add(username);

        //密码输入文本框
        password.setBounds(100, 60, 200, 25);
        panel.add(password);

        //登录按钮
        JButton loginButton = new JButton("登录");
        loginButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String name = username.getText();
                String pass = password.getText();
                User user = userService.getUser(name);
                if (user != null)
                {
                    if(user.getPassword().equals(pass))
                    {
                        new Game();
                        dispose();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "密码错误");
                        password.setText("");
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "用户不存在");
                }
            }
        });
        panel.add(loginButton);
        loginButton.setBounds(100, 100, 75, 25);

        //注册按钮
        JButton registerButton = new JButton("注册");
        registerButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String name = username.getText();
                String pass = password.getText();
                System.out.println("name: " + name + " pass: " + pass);
                if(userService.userMapper == null)
                {
                    System.out.println("userMapper is null");
                }
                User user = new User(name, pass);
                userService.insertUser(user);
                JOptionPane.showMessageDialog(null, "注册成功");
                username.setText("");
                password.setText("");
            }
        });
        panel.add(registerButton);
        registerButton.setBounds(200, 100, 75, 25);

    }

    public static void main(String[] args)
    {
        new LoginFrame();
    }

//    @Test
//    void test()
//    {
//        new LoginFrame();
//    }
}

