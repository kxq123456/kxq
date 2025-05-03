package com.example.demo.api.service.impl;
import com.example.demo.api.entity.Music;
import com.example.demo.api.service.AuthorService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.api.entity.Author;
import com.example.demo.api.mapper.AuthorMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class AuthorServiceImpl implements AuthorService {
    @Resource
    private AuthorMapper authorMapper;

    @Override
    public Author AddAuthor(Author author) {
        QueryWrapper<Author> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("music_author", author.getMusicAuthor());
        Author existingAuthor = authorMapper.selectOne(queryWrapper);
        // 如果已存在，返回已存在的音乐
        if (existingAuthor != null) {
            // 可以根据需求返回现有的音乐，或者给出提示信息
            return new Author("添加的歌手已存在");
        } else {
            // 如果不存在相同的音乐，执行插入操作
            authorMapper.insert(author);
            return author;  // 返回插入的音乐对象
        }
    }

    public List<Author> getAllAuthor() {
        return authorMapper.selectList(null);
    }

    public Author DeleteAuthor(Author author) {
        // 创建查询条件
        QueryWrapper<Author> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("music_author", author.getMusicAuthor());
        // 查询数据库，确保该音乐存在
        Author existingAuthor = authorMapper.selectOne(queryWrapper);
        if (existingAuthor == null) {
            // 如果音乐不存在，可以返回 null 或抛出异常提示
            return new Author("author not exist");
        }
        // 执行删除操作
        int rows = authorMapper.delete(queryWrapper);
        if (rows > 0) {
            // 删除成功，返回被删除的音乐信息
            return existingAuthor;
        } else {
            return new Author("Database delete error");
        }
    }

    public Author EditAuthor(Author author) {
        // 检查目标记录是否存在
        Author existingAuthor = authorMapper.selectById(author.getAuthorId());
        if (existingAuthor == null) {
            return new Author("Author does not exist");
        }

        // 更新记录
        int rowsAffected = authorMapper.updateById(author);
        if (rowsAffected > 0) {
            // 更新成功，返回更新后的音乐对象
            return authorMapper.selectById(author.getAuthorId());
        } else {
            return new Author("Failed to edit author");
        }
    }

    @Override
    public Author getAuthorByName(String name) {
        QueryWrapper<Author> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("music_author", name);
        return authorMapper.selectOne(queryWrapper);
    }
}
