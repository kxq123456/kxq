package com.example.demo.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName(value = "author")
public class Author {

    @TableId(value = "Author_id", type = IdType.AUTO)
    private Integer authorId;

    @TableField(value = "music_author")
    private String musicAuthor;

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getMusicAuthor() {
        return musicAuthor;
    }

    public void setMusicAuthor(String musicAuthor) {
        this.musicAuthor = musicAuthor;
    }


    @Override
    public String toString() {
        return "Author{" +
                "authorId=" + authorId +
                ", musicAuthor='" + musicAuthor + '\'' +
                '}';
    }

    public Author() {}

    public Author(String musicAuthor) {
        this.musicAuthor = musicAuthor;
    }
}
