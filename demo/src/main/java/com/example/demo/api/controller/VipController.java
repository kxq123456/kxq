package com.example.demo.api.controller;

import com.example.demo.api.dto.Result;
import com.example.demo.api.entity.Vip;
import com.example.demo.api.service.VipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.Map;
import java.util.List;

@CrossOrigin
@RestController
public class VipController {
    @Autowired
    private VipService vipService;

    @PostMapping("/addVip")
    public Result addVip(@RequestBody Vip vip) {
        System.out.println(vip);
        try {
            Vip addResult = vipService.AddVip(vip);
            if(addResult == null){
                return new Result(0,"添加的会员已存在",null);
            }
            return new Result(0,"ok",addResult);
        }
        catch (Exception e){
            return new Result(500,"添加会员失败: " + e.getMessage(),null);
        }
    }

    @PostMapping("/postVip")
    public Result postVip(@RequestBody Vip vip) {
        try {
            Vip postResult = vipService.PostVip(vip);
            if(postResult == null){
                return new Result(0,"兑换会员失败", null);
            }
            return new Result(0,"ok",postResult);
        }catch (Exception e){
            return new Result(500,"兑换会员失败" + e.getMessage(),null);
        }
    }

    @GetMapping("/getAllVip")
    public Result getAllVip() {
        try {
            List<Vip> vipList = vipService.getAllVip();
            return new Result(0, "ok", Map.of("vipList", vipList));
        } catch (Exception e) {
            return new Result(500, "获取会员兑换码失败：" + e.getMessage(), null);
        }
    }
}
