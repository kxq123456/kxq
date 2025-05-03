package com.example.demo.api.controller;
import com.example.demo.api.dto.Result;
import com.example.demo.api.entity.Admin;
import com.example.demo.api.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("/admin_login")
    public Result login(@RequestBody Admin admin) {
        try {
            Admin login = adminService.login(admin);
            if(login == null) {
                return new Result(0,"error","用户名或密码不正确");
            }
            return new Result(login);
        } catch (Exception e) {
            return new Result("获取管理员用户密码失败: " + e.getMessage());
        }

    }

    @GetMapping(value = "getAdminByToken")
    public Result getAdminByToken(@RequestParam("token") String token) {
        return new Result(adminService.getAdminByToken(token));
    }
}
