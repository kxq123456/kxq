package com.example.demo.api.service;
import com.example.demo.api.entity.User;
import com.example.demo.api.entity.UserLoveMusicDTO;

import java.util.List;

public interface UserService {
    User AddUser(User user);

    List<User> getAllUser();

    User getUserByName(String name);

    User DeleteUser(User user);

    User EditUser(User user);

    List<UserLoveMusicDTO> getMusicByUser(User user);

    User PostUser(User user);
}
