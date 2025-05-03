package com.example.demo.api.service;

import com.example.demo.api.entity.User_love;
import java.util.List;

public interface UserLoveService {
    User_love AddUserLove(User_love user_love);

    List<User_love> getAllUserLove();

    User_love DeleteUserLove(User_love user_love);

//    User_love EditUserLove(User_love user_love);
}
