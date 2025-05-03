package com.example.demo.api.controller;
import com.example.demo.api.entity.Music;
import com.example.demo.api.service.MusicService;
import com.example.demo.api.dto.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@CrossOrigin
@RestController
public class MusicController {

    @Autowired
    private MusicService musicService;

    @PostMapping("/addMusic")
    public Result addMusic(@RequestBody Music music) {
        Music addMusic = musicService.AddMusic(music);
        if(addMusic != null) {
            return new Result(addMusic);
        }
        return new Result(0,"新添加的音乐已存在",null);
    }

    @GetMapping("/getAllMusic")
    public Result getMusic() {
        try {
            // 调用 Service 方法查询所有音乐记录
            List<Music> musicList = musicService.getAllMusic();
            // 计算总数
            int total = musicList.size();

            // 构造返回数据
            Map<String, Object> responseData = new HashMap<>();
            responseData.put("musicList", musicList);
            responseData.put("total", total);

            // 返回查询结果
            if (total == 0) {
                return new Result(0, "未查询到该音乐");
            }
            return new Result(0, "ok", responseData);
        } catch (Exception e) {
            // 捕获异常并返回错误信息
            return new Result("获取音乐失败: " + e.getMessage());
        }
    }

    @GetMapping("/getMusicByName")
    public Result getMusicByName(@RequestParam("music_name") String name) {
        try {
            // 调用 Service 层方法
            List<Music> musicList = musicService.getMusicByName(name);
            if (musicList == null || musicList.isEmpty()) {
                return new Result(0, "未找到该音乐", null);
            }
            return new Result(0, "ok", musicList);
        } catch (Exception e) {
            return new Result(500,"查询音乐失败: " + e.getMessage(),null);
        }
    }

    @GetMapping("/getMusicByAuthor")
    public Result getMusicByAuthor(@RequestParam("music_author") String author) {
        try {
            // 调用 Service 层方法
            List<Music> musicList = musicService.getMusicByAuthor(author);
            if (musicList == null || musicList.isEmpty()) {
                return new Result(0, "未找到该作者的音乐", null);
            }
            return new Result(0, "ok", musicList);
        } catch (Exception e) {
            return new Result(500,"查询音乐失败: " + e.getMessage(),null);
        }
    }

    @DeleteMapping("/deleteMusic")
    public Result deleteMusic(@RequestBody Music music) {
        try{
            Music DeleteMusic = musicService.DeleteMusic(music);
            if(Objects.equals(DeleteMusic.getMusicUrl(), "music not exist")) {
                return new Result(0,"删除的音乐不存在",null);
            }
            if(Objects.equals(DeleteMusic.getMusicUrl(), "Database delete error")) {
                return new Result(0,"数据库删除音乐失败",null);
            }
            return new Result(DeleteMusic);
        }
        catch (Exception e) {
            return new Result("删除音乐失败: " + e.getMessage());
        }
    }

    @PutMapping("/editMusic")
    public Result editMusic(@RequestBody Music music) {
        try {
            Music editMusic = musicService.EditMusic(music);
            if(Objects.equals(editMusic.getMusicUrl(), "Music does not exist")) {
                return new Result(0,"编辑的音乐不存在",null);
            }
            if(Objects.equals(editMusic.getMusicUrl(), "Failed to edit music")) {
                return new Result(0,"数据库编辑音乐失败",null);
            }
            return new Result(editMusic);
        }
        catch (Exception e) {
            return new Result(e.getMessage());
        }
    }
}
