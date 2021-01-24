package com.xp.service.command;

import com.xp.domain.MaintenanceProject;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AddMaintenanceProjectCommand {
    private String name;
    private String type;
    private Integer cycle;
    private String unit;
    private LocalDate lastMaintainDate;

    public MaintenanceProject create() {
        return MaintenanceProject.builder()
            .name(name)
            .type(type)
            .cycle(cycle)
            .unit(unit)
            .lastMaintainDate(lastMaintainDate)
            .build();
    }
}
