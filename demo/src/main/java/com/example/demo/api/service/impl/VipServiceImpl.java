package com.example.demo.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.demo.api.entity.Vip;
import com.example.demo.api.mapper.VipMapper;
import com.example.demo.api.service.VipService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class VipServiceImpl implements VipService {
    @Resource
    private VipMapper vipMapper;

    @Override
    public Vip AddVip(Vip vip) {
        QueryWrapper<Vip> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("vip", vip.getVip());
        Vip existingVip = vipMapper.selectOne(queryWrapper);
        if (existingVip != null) {
            return null;
        }
        vipMapper.insert(vip);
        return vip;
    }

    @Override
    public Vip PostVip(Vip vip) {
        QueryWrapper<Vip> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("vip", vip.getVip());

        Vip existingVip = vipMapper.selectOne(queryWrapper);

        if (existingVip != null) {
            // 动态根据传入值设置 isUse 状态
            UpdateWrapper<Vip> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("vip", vip.getVip()).set("isUse", vip.getIsUse());

            int rowsAffected = vipMapper.update(null, updateWrapper);
            if (rowsAffected > 0) {
                return new Vip(vip.getVip(), vip.getIsUse());
            }
        }

        return null;
    }

    @Override
    public List<Vip> getAllVip() {
        return vipMapper.selectList(null); // 查询所有 vip 数据
    }
}