package com.example.mapper;

import com.example.game.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Mapper
@Component
public interface UserMapper
{
    void insertUser(User user);
    User getUser(String username);

    void deleteUser(String username);
}
