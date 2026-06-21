package com.example.dronesystem.mapper;

import com.example.dronesystem.entity.Drone;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface DroneMapper {

    int insert(Drone drone);

    int update(Drone drone);

    int deleteById(Long id);

    Drone selectById(Long id);

    List<Drone> selectList(@Param("drone") Drone drone);

    int count(@Param("drone") Drone drone);
}