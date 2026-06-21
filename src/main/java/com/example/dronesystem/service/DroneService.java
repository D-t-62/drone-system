package com.example.dronesystem.service;

import com.example.dronesystem.entity.Drone;
import java.util.List;

public interface DroneService {

    int insert(Drone drone);

    int update(Drone drone);

    int deleteById(Long id);

    Drone selectById(Long id);

    List<Drone> selectList(Drone drone);

    int count(Drone drone);

    Drone generateAiDrone();
}