package com.example.demo.api.controller;

import com.example.demo.api.dto.Result;
import com.example.demo.api.entity.User;
import com.example.demo.api.entity.UserLoveMusicDTO;
import com.example.demo.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@CrossOrigin
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public Result addUser(@RequestBody User user) {
        try {
            User addUser = userService.AddUser(user);
            if(addUser == null){
                return new Result(0,"添加的用户已存在",null);
            }
            return new Result(0,"ok",addUser);
        }
        catch (Exception e){
            return new Result(500,"添加用户失败: " + e.getMessage(),null);
        }
    }

    @GetMapping("/getAllUser")
    public Result getMusic() {
        try {
            // 调用 Service 方法查询所有音乐记录
            List<User> UserList = userService.getAllUser();
            // 计算总数
            int total = UserList.size();

            // 构造返回数据
            Map<String, Object> responseData = new HashMap<>();
            responseData.put("userList", UserList);
            responseData.put("total", total);

            // 返回查询结果
            if (total == 0) {
                return new Result(0, "未查询到所有的用户");
            }
            return new Result(0, "ok", responseData);
        } catch (Exception e) {
            // 捕获异常并返回错误信息
            return new Result(500,"获取所有用户失败: " + e.getMessage(),null);
        }
    }

    @GetMapping("/getUserByName")
    public Result getUserByName(@RequestParam("name") String name) {
        try {
            User user = userService.getUserByName(name);
            if (user == null) {
                return new Result(0, "未找到该用户", null);
            }
            return new Result(0, "ok", user);
        } catch (Exception e) {
            return new Result(500,"查询该用户失败: " + e.getMessage(),null);
        }
    }

    @DeleteMapping("/deleteUser")
    public Result deleteUser(@RequestBody User user) {
        try{
            User DeleteUser = userService.DeleteUser(user);
            if(Objects.equals(DeleteUser.getUserName(), "author not exist")) {
                return new Result(0,"删除的用户不存在",null);
            }
            if(Objects.equals(DeleteUser.getUserName(), "Database delete error")) {
                return new Result(0,"数据库用户删除歌手失败",null);
            }
            return new Result(DeleteUser);
        }
        catch (Exception e) {
            return new Result(500,"删除用户失败: " + e.getMessage(),null);
        }
    }

    @PutMapping("/editUser")
    public Result editUser(@RequestBody User user) {
        try {
            User EditUser = userService.EditUser(user);
            if(Objects.equals(EditUser.getUserName(), "User does not exist")) {
                return new Result(0,"编辑的用户不存在",null);
            }
            if(Objects.equals(EditUser.getUserName(), "Failed to edit user")) {
                return new Result(500,"数据库编辑用户失败",null);
            }
            return new Result(0, "ok", EditUser);
        }
        catch (Exception e) {
            return new Result(e.getMessage());
        }
    }

    @GetMapping("/getMusicByUser")//查询用户喜爱的音乐
    public Result getMusicByUser(User user){
        try {
            List<UserLoveMusicDTO> LoveMusicList = userService.getMusicByUser(user);
            int total = LoveMusicList.size();
            // 构造返回数据
            Map<String, Object> responseData = new HashMap<>();
            responseData.put("musicList", LoveMusicList);
            responseData.put("total", total);
            if (total == 0) {
                return new Result(0, "未查询到该用户有喜爱的音乐");
            }
            return new Result(0, "ok", responseData);
        }catch (Exception e){
            // 捕获异常并返回错误信息
            return new Result(500,"数据库获取所有用户失败: " + e.getMessage(),null);
        }

    }

    @PostMapping("/postUser")//登录
    public Result postUser(@RequestBody User user) {
        try {
            User postuser = userService.PostUser(user);
            if (postuser == null) {
                return new Result(0, "账号密码错误", null);
            }
            postuser.setUserPassword(null); // 假设你的 User 类有 setUserPassword 方法
            return new Result(0, "ok", postuser);
        } catch (Exception e) {
            return new Result(500,"数据库查询该用户失败: " + e.getMessage(),null);
        }
    }
}
