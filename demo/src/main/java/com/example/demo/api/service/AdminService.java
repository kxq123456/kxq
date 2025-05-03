package com.example.demo.api.service;
import com.example.demo.api.entity.Admin;

public interface AdminService {
    Admin login(Admin admin);

    Admin getAdminByToken(String token);
}
