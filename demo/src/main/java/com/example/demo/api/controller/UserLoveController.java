package com.example.demo.api.controller;

import com.example.demo.api.dto.Result;
import com.example.demo.api.entity.Music;
import com.example.demo.api.entity.User_love;
import com.example.demo.api.service.MusicService;
import com.example.demo.api.service.UserLoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@CrossOrigin
@RestController
public class UserLoveController {

    @Autowired
    private UserLoveService userLoveService;
    private MusicService musicService;

    @PostMapping("/addUserLove")
    public Result addUserLove(@RequestBody User_love user_love) {
        try {
            User_love addUserLove = userLoveService.AddUserLove(user_love);
            if(Objects.equals(addUserLove.getUserName(), "添加的喜爱歌曲已存在")){
                return new Result(0,"添加的喜爱歌曲已存在",null);
            }
            return new Result(0,"ok",addUserLove);
        }
        catch (Exception e){
            return new Result("添加的喜爱歌曲失败: " + e.getMessage());
        }
    }

    @GetMapping("/getAllUserLove")
    public Result getAllUserLove() {
        try {
            // 调用 Service 方法查询所有音乐记录
            List<User_love> UserLoveList = userLoveService.getAllUserLove();
            // 计算总数
            int total = UserLoveList.size();

            // 构造返回数据
            Map<String, Object> responseData = new HashMap<>();
            responseData.put("userLoveList", UserLoveList);
            responseData.put("total", total);

            // 返回查询结果
            if (total == 0) {
                return new Result(0, "未查询到该用户有喜爱歌曲");
            }
            return new Result(0, "ok", responseData);
        } catch (Exception e) {
            // 捕获异常并返回错误信息
            return new Result("获取用户喜爱歌曲失败: " + e.getMessage());
        }
    }

    @DeleteMapping("/deleteUserLove")
    public Result deleteUserLove(@RequestBody User_love user_love) {
        try{
            User_love DeleteUser = userLoveService.DeleteUserLove(user_love);
            if(Objects.equals(DeleteUser.getUserName(), "")) {
                return new Result(0,"删除的喜爱歌曲不存在",null);
            }
            if(Objects.equals(DeleteUser.getUserName(), "Database delete error")) {
                return new Result(0,"数据库用户删除喜爱歌曲失败",null);
            }
            return new Result(0,"ok", DeleteUser);
        }
        catch (Exception e) {
            return new Result("删除喜欢歌曲失败: " + e.getMessage());
        }
    }

    @GetMapping("/getUserLoveByMusicName")
    public Result getUserLoveByName(@RequestParam("name") String name) {
        try{
            List<Music> musicList = musicService.getMusicByName(name);
            if(musicList == null || musicList.isEmpty()) {
                return new Result(0,"未查询到用户喜爱的音乐",null);
            }
            return new Result(0,"ok",musicList);
        }
        catch (Exception e) {
            return new Result(500,"查询用户喜爱失败",null);
        }
    }

//    @PutMapping("/editUserLove")
//    public Result editAuthor(@RequestBody User user) {
//        try {
//            User EditUser = userService.EditUser(user);
//            if(Objects.equals(EditUser.getUserName(), "Author does not exist")) {
//                return new Result(0,"编辑的歌手不存在",null);
//            }
//            if(Objects.equals(EditUser.getUserName(), "Failed to edit author")) {
//                return new Result(0,"数据库编辑用户失败",null);
//            }
//            return new Result(EditUser);
//        }
//        catch (Exception e) {
//            return new Result(e.getMessage());
//        }
//    }
}
