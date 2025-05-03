package com.example.demo.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.api.entity.User;
import com.example.demo.api.entity.UserLoveMusicDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import java.util.List;


public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT u.user_name, ul.music_name, ul.music_author " +
            "FROM user u " +
            "JOIN user_love ul ON u.user_name = ul.user_name " +
            "WHERE u.user_name = #{userName}")
    List<UserLoveMusicDTO> getMusicByUserName(@Param("userName") String userName);

    // 通过用户名查询用户
    @Select("SELECT * FROM user WHERE user_name = #{username}")
    User selectByUsername(@Param("username") String username);

}

