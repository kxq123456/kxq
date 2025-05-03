package com.example.demo.api.service.impl;
import com.example.demo.api.entity.Music;
import com.example.demo.api.mapper.MusicMapper;
import com.example.demo.api.service.MusicService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Transactional
@Service
public class MusicServiceImpl implements MusicService {
    @Resource
    private MusicMapper musicMapper;

    @Override
    public Music AddMusic(Music music) {
        QueryWrapper<Music> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("music_name", music.getMusicName());
        queryWrapper.eq("music_author", music.getMusicAuthor());
        queryWrapper.eq("music_url", music.getMusicUrl());
        Music existingMusic = musicMapper.selectOne(queryWrapper);

        // 如果已存在，返回已存在的音乐
        if (existingMusic != null) {
            // 可以根据需求返回现有的音乐，或者给出提示信息
            return new Music();  // 或者抛出异常提示音乐已存在
        } else {
            // 如果不存在相同的音乐，执行插入操作
            musicMapper.insert(music);
            return music;  // 返回插入的音乐对象
        }
    }

    public List<Music> getAllMusic() {
        return musicMapper.selectList(null);
    }

    public List<Music> getMusicByName(String name) {
        // 使用 MyBatis-Plus 提供的 QueryWrapper 简化查询
        QueryWrapper<Music> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("music_name", name); // 模糊查询，匹配包含 name 的音乐
        return musicMapper.selectList(queryWrapper);
    }

    public List<Music> getMusicByAuthor(String author) {
        // 使用 MyBatis-Plus 的 QueryWrapper 进行查询
        QueryWrapper<Music> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("music_author", author); // 精确匹配作者名称
        return musicMapper.selectList(queryWrapper);
    }

    public Music DeleteMusic(Music music) {
        // 创建查询条件
        QueryWrapper<Music> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("music_name", music.getMusicName());
        // 查询数据库，确保该音乐存在
        Music existingMusic = musicMapper.selectOne(queryWrapper);
        if (existingMusic == null) {
            // 如果音乐不存在，可以返回 null 或抛出异常提示
            return new Music("music not exist");
        }
        // 执行删除操作
        int rows = musicMapper.delete(queryWrapper);
        if (rows > 0) {
            // 删除成功，返回被删除的音乐信息
            return existingMusic;
        } else {
            return new Music("Database delete error");
        }
    }

    public Music EditMusic(Music music) {
        // 1. 检查是否存在
        Music existingMusic = musicMapper.selectById(music.getMusicId());
        if (existingMusic == null) {
            return new Music("Music does not exist");
        }

        // 2. 更新字段（将前端传入的新值赋给旧对象）
        existingMusic.setMusicName(music.getMusicName());
        existingMusic.setMusicAuthor(music.getMusicAuthor());

        // 3. 执行更新
        int rowsAffected = musicMapper.updateById(existingMusic);
        if (rowsAffected > 0) {
            return musicMapper.selectById(music.getMusicId());
        } else {
            return new Music("Failed to edit music");
        }
    }
}
