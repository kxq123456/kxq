package com.example.demo.api.service;
import java.util.List;
import com.example.demo.api.entity.Vip;

public interface VipService {
    Vip AddVip(Vip vip);

    Vip PostVip(Vip vip);

    List<Vip> getAllVip();
}
