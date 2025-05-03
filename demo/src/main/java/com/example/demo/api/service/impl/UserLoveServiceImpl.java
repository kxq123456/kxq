package com.example.demo.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.api.entity.User_love;
import com.example.demo.api.mapper.UserLoveMapper;
import com.example.demo.api.service.UserLoveService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Transactional
@Service
public class UserLoveServiceImpl implements UserLoveService {
    @Resource
    private UserLoveMapper userLoveMapper;

    @Override
    public User_love AddUserLove(User_love user_love) {
        QueryWrapper<User_love> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("User_name", user_love.getUserName());
        queryWrapper.eq("music_name", user_love.getMusicName());
        queryWrapper.eq("music_author",user_love.getMusicAuthor());
        User_love existingUserLove = userLoveMapper.selectOne(queryWrapper);
        System.out.println(user_love);
        // 如果已存在，返回已存在的音乐
        if (existingUserLove != null) {
            // 可以根据需求返回现有的音乐，或者给出提示信息
            return new User_love();  // 或者抛出异常提示音乐已存在
        } else {
            // 如果不存在相同的音乐，执行插入操作
            userLoveMapper.insert(user_love);
            return user_love;  // 返回插入的音乐对象
        }
    }

    public List<User_love> getAllUserLove() {
        return userLoveMapper.selectList(null);
    }

    public User_love DeleteUserLove(User_love user_love) {
        // 创建查询条件
        QueryWrapper<User_love> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("User_name", user_love.getUserName());
        queryWrapper.eq("music_name", user_love.getMusicName());
        // 查询数据库，确保该音乐存在
        User_love existingUserLove = userLoveMapper.selectOne(queryWrapper);
        if (existingUserLove == null) {
            // 如果音乐不存在，可以返回 null 或抛出异常提示
            return new User_love("music not exist");
        }
        // 执行删除操作
        int rows = userLoveMapper.delete(queryWrapper);
        if (rows > 0) {
            // 删除成功，返回被删除的音乐信息
            return existingUserLove;
        } else {
            return new User_love("Database delete error");
        }
    }

//    public User EditUser(User user) {
//        // 检查目标记录是否存在
//        User existingUser = userMapper.selectById(user.getUserId());
//        if (existingUser == null) {
//            return new User("User does not exist");
//        }
//
//        // 更新记录
//        int rowsAffected = userMapper.updateById(user);
//        if (rowsAffected > 0) {
//            // 更新成功，返回更新后的音乐对象
//            return userMapper.selectById(user.getUserId());
//        } else {
//            return new User("Failed to edit user");
//        }
//    }
}
