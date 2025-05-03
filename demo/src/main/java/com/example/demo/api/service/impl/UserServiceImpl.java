package com.example.demo.api.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.demo.api.entity.User;
import com.example.demo.api.entity.UserLoveMusicDTO;
import com.example.demo.api.mapper.UserMapper;
import com.example.demo.api.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Transactional
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public User AddUser(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", user.getUserName());
        User existingUser = userMapper.selectOne(queryWrapper);

        // 如果已存在，返回已存在的音乐
        if (existingUser != null) {
            // 可以根据需求返回现有的音乐，或者给出提示信息
            return null;  // 或者抛出异常提示音乐已存在
        } else {
            // 如果不存在相同的音乐，执行插入操作
            userMapper.insert(user);
            return user;  // 返回插入的音乐对象
        }
    }

    public List<User> getAllUser() {
        return userMapper.selectList(null);
    }

    public User DeleteUser(User user) {
        // 创建查询条件
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", user.getUserName());
        // 查询数据库，确保该音乐存在
        User existingUser = userMapper.selectOne(queryWrapper);
        if (existingUser == null) {
            // 如果音乐不存在，可以返回 null 或抛出异常提示
            return new User("user not exist");
        }
        // 执行删除操作
        int rows = userMapper.delete(queryWrapper);
        if (rows > 0) {
            // 删除成功，返回被删除的音乐信息
            return existingUser;
        } else {
            return new User("Database delete error");
        }
    }

    public User getUserByName(String name) {
        // 使用 MyBatis-Plus 提供的 QueryWrapper 简化查询
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("User_name", name);
        return userMapper.selectOne(queryWrapper);
    }


    public User EditUser(User user) {
        // 1. 检查用户是否存在
        User existing = userMapper.selectByUsername(user.getUserName());
        if (existing == null) {
            System.out.println("用户不存在");
            return new User("用户不存在");
        }

        // 2. 构建 UpdateWrapper，只更新必要字段
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.eq("user_name", user.getUserName());

        // 3. 只有当前端传来了非空密码，才去 set 密码
        if (StringUtils.isNotBlank(user.getUserPassword())) {
            wrapper.set("user_password", user.getUserPassword());
        }

        // 4. 无论如何都要更新 vip 字段
        wrapper.set("vip", user.getVip());

        // 5. 执行更新，注意第一个参数必须是 null
        int rows = userMapper.update(null, wrapper);
        if (rows > 0) {
            // 把变更同步到返回对象中
            existing.setVip(user.getVip());
            if (StringUtils.isNotBlank(user.getUserPassword())) {
                existing.setUserPassword(user.getUserPassword());
            }
            return existing;
        } else {
            return new User("更新失败");
        }
    }



    public List<UserLoveMusicDTO> getMusicByUser(User user) {
        if (user == null || user.getUserName() == null) {
            throw new IllegalArgumentException("User or username cannot be null");
        }

        // 调用Mapper查询用户喜欢的音乐数据
        return userMapper.getMusicByUserName(user.getUserName());
    }

    public User PostUser(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("User_name", user.getUserName());
        queryWrapper.eq("User_password", user.getUserPassword());
        return userMapper.selectOne(queryWrapper);
    }
}
