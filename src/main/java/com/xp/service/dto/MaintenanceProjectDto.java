package com.xp.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MaintenanceProjectDto {
    private String name;
    private String type;
    private Integer cycle;
    private String unit;
    private LocalDate lastMaintainDate;
}
