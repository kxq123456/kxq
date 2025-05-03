package com.example.demo.api.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName(value = "music") // 映射到数据库表名
public class Music {
    @TableId(value = "music_id", type = IdType.AUTO)
    private Integer musicId;

    @TableField(value = "music_name")
    private String musicName;

    @TableField(value = "music_author")
    private String musicAuthor;

    @TableField(value = "music_url")
    private String musicUrl;

    @TableField(value = "music_time")
    private String musicTime;

    @Override
    public String toString() {
        return "Music{" +
                "musicId=" + musicId +
                ", musicName='" + musicName + '\'' +
                ", musicAuthor='" + musicAuthor + '\'' +
                ", musicUrl='" + musicUrl + '\'' +
                ", musicTime='" + musicTime + '\'' +
                '}';
    }

    public String getMusicTime() {
        return musicTime;
    }

    public void setMusicTime(String musicTime) {
        this.musicTime = musicTime;
    }

    public Integer getMusicId() {
        return musicId;
    }

    public void setMusicId(Integer musicId) {
        this.musicId = musicId;
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

    public String getMusicUrl() {
        return musicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        this.musicUrl = musicUrl;
    }

    public Music() {
    }

    public Music(String musicUrl){
        this.musicUrl = musicUrl;
    }
}
