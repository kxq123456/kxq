package com.example.demo.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName(value = "vip")
public class Vip {

    @TableId(value = "vip_id", type = IdType.AUTO) // 这里指定主键及主键生成策略
    private Long vipId;

    @TableField(value = "vip")
    private String vip;

    @TableField(value = "isUse")
    private String isUse;  // 修改为小驼峰命名

    // Getter 和 Setter 方法
    public String getVip() {
        return vip;
    }

    public void setVip(String vip) {
        this.vip = vip;
    }

    public String getIsUse() {
        return isUse;
    }

    public void setIsUse(String isUse) {  // 方法名也进行修改
        this.isUse = isUse;
    }

    public Long getVipId() {
        return vipId;
    }

    public void setVipId(Long vipId) {
        this.vipId = vipId;
    }

    // 构造函数
    public Vip(String isUse, String vip) {
        this.isUse = isUse;
        this.vip = vip;
    }

    // toString 方法
    @Override
    public String toString() {
        return "Vip{" +
                "vipId=" + vipId +
                ", vip='" + vip + '\'' +
                ", isUse='" + isUse + '\'' +  // 调整为新的字段名
                '}';
    }
}
