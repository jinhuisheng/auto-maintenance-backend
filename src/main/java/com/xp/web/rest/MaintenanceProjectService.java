package com.xp.web.rest;

import com.xp.repository.MaintenanceProjectRepository;
import com.xp.service.dto.MaintenanceProjectDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
public class MaintenanceProjectService {
    private MaintenanceProjectRepository maintenanceProjectRepository;

    public MaintenanceProjectService(MaintenanceProjectRepository maintenanceProjectRepository) {
        this.maintenanceProjectRepository = maintenanceProjectRepository;
    }

    List<MaintenanceProjectDto> getMaintenanceProjects() {
        MaintenanceProjectDto maintenanceProjectDto = new MaintenanceProjectDto();
        maintenanceProjectDto.setName("机油");
        maintenanceProjectDto.setCycle(3);
        maintenanceProjectDto.setUnit("day");
        maintenanceProjectDto.setType("保养");
        maintenanceProjectDto.setLastMaintainDate(LocalDate.of(2020, 12, 1));
        return Arrays.asList(maintenanceProjectDto);
    }
}
