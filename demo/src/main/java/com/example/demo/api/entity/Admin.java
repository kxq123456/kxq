package com.example.demo.api.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName(value = "admin")
public class Admin {
    @TableId(value = "admin_id", type = IdType.AUTO) // 这里指定主键及主键生成策略
    private Long adminId;

    @TableField(value = "admin_name")
    private String adminName;

    @TableField(value = "admin_password")
    private String adminPassword;

    @TableField(value = "token")
    private String token;

    @TableField(value = "avatar")
    private String avatar;

    @TableField(value = "roles")
    private String roles;

    @Override
    public String toString() {
        return "Admin{" +
                "adminId=" + adminId +
                ", adminName='" + adminName + '\'' +
                ", adminPassword='" + adminPassword + '\'' +
                ", token='" + token + '\'' +
                ", avatar='" + avatar + '\'' +
                ", roles='" + roles + '\'' +
                '}';
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public Admin(){

    }
}
