package com.cn.homeControlSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//Add the lombok annotations a for auto generating constructors, getters and setters.
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeviceDTO
{
    private String name;
    private String type;
    private String status;
    private Integer roomId;
}
