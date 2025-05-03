package com.example.demo.api.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.api.entity.Author;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuthorMapper extends BaseMapper<Author> {

}
