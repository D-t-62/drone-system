package com.example.dronesystem.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Drone {

    private Long id;

    @NotBlank(message = "序列号不能为空")
    private String serialNumber;

    @NotBlank(message = "型号不能为空")
    private String model;

    @NotNull(message = "重量不能为空")
    @Positive(message = "重量必须为正数")
    private BigDecimal weight;

    @NotNull(message = "最大高度不能为空")
    @Positive(message = "最大高度必须为正数")
    private Integer maxHeight;

    @NotNull(message = "最大速度不能为空")
    @Positive(message = "最大速度必须为正数")
    private Integer maxSpeed;

    @NotNull(message = "电池容量不能为空")
    @Positive(message = "电池容量必须为正数")
    private Integer batteryCapacity;

    private String status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}