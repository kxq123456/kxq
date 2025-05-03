package com.example.demo.api.controller;

import com.example.demo.api.dto.Result;
import com.example.demo.api.entity.Author;
import com.example.demo.api.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@CrossOrigin
@RestController
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping("/addAuthor")
    public Result addAuthor(@RequestBody Author author) {
        try {
            Author addAuthor = authorService.AddAuthor(author);
            if(Objects.equals(addAuthor.getMusicAuthor(), "添加的歌手已存在")){
                return new Result(0,"添加的歌手已存在",null);
            }
            return new Result(addAuthor);
        }
        catch (Exception e){
            return new Result("添加歌手失败: " + e.getMessage());
        }
    }

    @GetMapping("/getAllAuthor")
    public Result getAllAuthor() {
        try {
            // 调用 Service 方法查询所有音乐记录
            List<Author> AuthorList = authorService.getAllAuthor();
            // 计算总数
            int total = AuthorList.size();

            // 构造返回数据
            Map<String, Object> responseData = new HashMap<>();
            responseData.put("authorList", AuthorList);
            responseData.put("total", total);

            // 返回查询结果
            if (total == 0) {
                return new Result(0, "未查询到该歌手");
            }
            return new Result(0, "ok", responseData);
        } catch (Exception e) {
            // 捕获异常并返回错误信息
            return new Result("获取歌手失败: " + e.getMessage());
        }
    }


    @GetMapping("/getAuthorByName")
    public Result getAuthorByName(@RequestParam("name") String name) {
        try{
            Author author = authorService.getAuthorByName(name);
            if(author == null){
                return new Result(0,"未查找到该歌手",null);
            }
            return new Result(0,"ok", author);
        }
        catch (Exception e){
            return new Result("获取歌手失败: " + e.getMessage());
        }
    }

    @DeleteMapping("/deleteAuthor")
    public Result deleteAuthor(@RequestBody Author author) {
        try{
            Author DeleteAuthor = authorService.DeleteAuthor(author);
            if(Objects.equals(DeleteAuthor.getMusicAuthor(), "author not exist")) {
                return new Result(0,"删除的歌手不存在",null);
            }
            if(Objects.equals(DeleteAuthor.getMusicAuthor(), "Database delete error")) {
                return new Result(0,"数据库删除歌手失败",null);
            }
            return new Result(DeleteAuthor);
        }
        catch (Exception e) {
            return new Result("删除歌手失败: " + e.getMessage());
        }
    }

    @PutMapping("/editAuthor")
    public Result editAuthor(@RequestBody Author author) {
        try {
            Author EditAuthor = authorService.EditAuthor(author);
            if(Objects.equals(EditAuthor.getMusicAuthor(), "Author does not exist")) {
                return new Result(0,"编辑的歌手不存在",null);
            }
            if(Objects.equals(EditAuthor.getMusicAuthor(), "Failed to edit author")) {
                return new Result(0,"数据库编辑歌手失败",null);
            }
            return new Result(EditAuthor);
        }
        catch (Exception e) {
            return new Result(e.getMessage());
        }
    }
}
