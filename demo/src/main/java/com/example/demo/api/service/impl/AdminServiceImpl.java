package com.example.demo.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.example.demo.api.entity.Admin;
import com.example.demo.api.mapper.AdminMapper;
import com.example.demo.api.mapper.UserMapper;
import com.example.demo.api.service.AdminService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;

@Transactional
@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminMapper adminMapper;

    @Value("${jwt.secret}") // JWT 密钥可以从配置文件中获取
    private String secretKey;


    @Override
    public Admin login(Admin admin) {
        // 通过用户名和密码查询管理员
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("admin_name", admin.getAdminName());
        queryWrapper.eq("admin_password", admin.getAdminPassword());
        Admin admin1 =  adminMapper.selectOne(queryWrapper);
        System.out.println(admin1);
        return admin1;
    }

    @Override
    public Admin getAdminByToken(String token) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("token", token);
        Admin admin1 =  adminMapper.selectOne(queryWrapper);
        System.out.println(admin1);
        return admin1;
    }

    // 生成 JWT token
    private String decodeSecretKey(String secretKey) {
        // 如果密钥是 URL 安全的 Base64 编码，转换为标准 Base64 编码
        try {
            String base64String = secretKey.replace('-', '+').replace('_', '/');
            System.out.println(base64String);
            return new String(Base64.getDecoder().decode(base64String));
        } catch (IllegalArgumentException e) {
            // 如果密钥不是 Base64 编码的，可以直接返回原始密钥
            System.err.println("密钥不是有效的Base64编码，直接使用原始密钥");
            return secretKey; // 返回原始密钥
        }
    }

    private String generateToken(Admin admin) {
        // 将原始密钥转换为字节数组
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8); // 使用 UTF-8 编码转换为字节数组

        return Jwts.builder()
                .setSubject(admin.getAdminName()) // 设置 JWT 的主题（通常是用户名）
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 设置过期时间为 24 小时
                .signWith(SignatureAlgorithm.HS256, keyBytes) // 使用字节数组作为密钥进行签名
                .compact();
    }
}
