package com.example.dronesystem.service.impl;

import com.example.dronesystem.entity.Drone;
import com.example.dronesystem.mapper.DroneMapper;
import com.example.dronesystem.service.DroneService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class DroneServiceImpl implements DroneService {

    private final DroneMapper droneMapper;
    private final Random random = new Random();

    public DroneServiceImpl(DroneMapper droneMapper) {
        this.droneMapper = droneMapper;
    }

    @Override
    @Transactional
    public int insert(Drone drone) {
        drone.setCreateTime(LocalDateTime.now());
        drone.setUpdateTime(LocalDateTime.now());
        if (drone.getStatus() == null) {
            drone.setStatus("ACTIVE");
        }
        return droneMapper.insert(drone);
    }

    @Override
    @Transactional
    public int update(Drone drone) {
        drone.setUpdateTime(LocalDateTime.now());
        return droneMapper.update(drone);
    }

    @Override
    @Transactional
    public int deleteById(Long id) {
        return droneMapper.deleteById(id);
    }

    @Override
    public Drone selectById(Long id) {
        return droneMapper.selectById(id);
    }

    @Override
    public List<Drone> selectList(Drone drone) {
        return droneMapper.selectList(drone);
    }

    @Override
    public int count(Drone drone) {
        return droneMapper.count(drone);
    }

    @Override
    public Drone generateAiDrone() {
        String[] models = {"DJI Phantom 4", "DJI Mavic Pro", "DJI Air 2S", "Parrot Anafi", "Autel Evo II", "Yuneec Typhoon"};
        String[] statuses = {"ACTIVE", "INACTIVE", "MAINTENANCE"};

        Drone drone = new Drone();
        drone.setSerialNumber("DRONE-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        drone.setModel(models[random.nextInt(models.length)]);
        drone.setWeight(BigDecimal.valueOf(1.0 + random.nextDouble() * 5.0));
        drone.setMaxHeight(100 + random.nextInt(4900));
        drone.setMaxSpeed(50 + random.nextInt(150));
        drone.setBatteryCapacity(2000 + random.nextInt(8000));
        drone.setStatus(statuses[random.nextInt(statuses.length)]);
        drone.setCreateTime(LocalDateTime.now());
        drone.setUpdateTime(LocalDateTime.now());

        return drone;
    }
}