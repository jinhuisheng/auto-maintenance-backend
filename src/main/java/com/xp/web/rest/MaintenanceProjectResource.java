package com.xp.web.rest;

import com.xp.service.command.AddMaintenanceProjectCommand;
import com.xp.service.dto.MaintenanceProjectDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/maintenance-projects")
public class MaintenanceProjectResource {
    private final MaintenanceProjectService maintenanceProjectService;

    public MaintenanceProjectResource(MaintenanceProjectService maintenanceProjectService) {
        this.maintenanceProjectService = maintenanceProjectService;
    }

    @GetMapping
    public List<MaintenanceProjectDto> getProjects() {
        return maintenanceProjectService.getMaintenanceProjects();
    }

    @PostMapping
    public void addProject(@RequestBody AddMaintenanceProjectCommand command) {
        maintenanceProjectService.addProject(command);
    }


}
