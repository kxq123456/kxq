package com.example.demo.api.entity;

public class UserLoveMusicDTO {
    private String userName;
    private String musicName;
    private String musicAuthor;

    // Getters and Setters
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
        return "UserLoveMusicDTO{" +
                "userName='" + userName + '\'' +
                ", musicName='" + musicName + '\'' +
                ", musicAuthor='" + musicAuthor + '\'' +
                '}';
    }
}