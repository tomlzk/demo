package com.example.game;

import com.example.mapper.UserMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//@MapperScan("com.example.mapper")
@Service
public class UserService
{
    @Autowired
    public UserMapper userMapper;

    public User getUser(String username)
    {
        return userMapper.getUser(username);
    }

    public void insertUser(User user)
    {
        userMapper.insertUser(user);
    }

    public void deleteUser(String username)
    {
        userMapper.deleteUser(username);
    }
}
