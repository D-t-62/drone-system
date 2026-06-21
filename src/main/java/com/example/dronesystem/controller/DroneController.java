package com.example.dronesystem.controller;

import com.example.dronesystem.entity.Drone;
import com.example.dronesystem.service.DroneService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/drone")
@Validated
public class DroneController {

    private final DroneService droneService;

    public DroneController(DroneService droneService) {
        this.droneService = droneService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody Drone drone) {
        Map<String, Object> result = new HashMap<>();
        int rows = droneService.insert(drone);
        if (rows > 0) {
            result.put("code", 200);
            result.put("message", "创建成功");
            result.put("data", drone);
        } else {
            result.put("code", 500);
            result.put("message", "创建失败");
        }
        return ResponseEntity.ok(result);
    }

    @PutMapping
    public ResponseEntity<Map<String, Object>> update(@Valid @RequestBody Drone drone) {
        Map<String, Object> result = new HashMap<>();
        int rows = droneService.update(drone);
        if (rows > 0) {
            result.put("code", 200);
            result.put("message", "更新成功");
            result.put("data", drone);
        } else {
            result.put("code", 500);
            result.put("message", "更新失败");
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable @NotNull(message = "ID不能为空") Long id) {
        Map<String, Object> result = new HashMap<>();
        int rows = droneService.deleteById(id);
        if (rows > 0) {
            result.put("code", 200);
            result.put("message", "删除成功");
        } else {
            result.put("code", 500);
            result.put("message", "删除失败");
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable @NotNull(message = "ID不能为空") Long id) {
        Map<String, Object> result = new HashMap<>();
        Drone drone = droneService.selectById(id);
        if (drone != null) {
            result.put("code", 200);
            result.put("data", drone);
        } else {
            result.put("code", 404);
            result.put("message", "未找到");
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> list(
            @RequestParam(required = false) String serialNumber,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) String status) {
        Map<String, Object> result = new HashMap<>();
        Drone drone = new Drone();
        drone.setSerialNumber(serialNumber);
        drone.setModel(model);
        drone.setStatus(status);
        List<Drone> list = droneService.selectList(drone);
        result.put("code", 200);
        result.put("data", list);
        result.put("count", droneService.count(drone));
        return ResponseEntity.ok(result);
    }

    @GetMapping("/generate")
    public ResponseEntity<Map<String, Object>> generate() {
        Map<String, Object> result = new HashMap<>();
        Drone drone = droneService.generateAiDrone();
        result.put("code", 200);
        result.put("data", drone);
        result.put("message", "AI生成无人机信息成功");
        return ResponseEntity.ok(result);
    }

    @PostMapping("/generate-and-save")
    public ResponseEntity<Map<String, Object>> generateAndSave() {
        Map<String, Object> result = new HashMap<>();
        Drone drone = droneService.generateAiDrone();
        droneService.insert(drone);
        result.put("code", 200);
        result.put("data", drone);
        result.put("message", "AI生成并保存无人机信息成功");
        return ResponseEntity.ok(result);
    }
}