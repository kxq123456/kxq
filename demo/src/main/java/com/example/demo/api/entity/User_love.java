package com.example.demo.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName(value = "user_love")
public class User_love {

    @TableId(value = "User_love_id",type = IdType.AUTO)
    private Integer UserLoveId;

    @TableField(value = "User_name")
    private String userName;

    @TableField(value = "music_name")
    private String musicName;

    @TableField(value = "music_author")
    private String musicAuthor;

    public Integer getUserLoveId() {
        return UserLoveId;
    }

    public void setUserLoveId(Integer userLoveId) {
        UserLoveId = userLoveId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public String getMusicAuthor() {
        return musicAuthor;
    }

    public void setMusicAuthor(String musicAuthor) {
        this.musicAuthor = musicAuthor;
    }

    @Override
    public String toString() {
        return "User_love{" +
                "UserLoveId=" + UserLoveId +
                ", userName='" + userName + '\'' +
                ", musicName='" + musicName + '\'' +
                ", musicAuthor='" + musicAuthor + '\'' +
                '}';
    }

    public User_love(){

    }

    public User_love(String User_name){
        this.userName = User_name;
    }
}
