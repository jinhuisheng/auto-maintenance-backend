package com.xp.web.rest;

import com.xp.domain.MaintenanceProject;
import com.xp.repository.MaintenanceProjectRepository;
import com.xp.service.command.AddMaintenanceProjectCommand;
import com.xp.service.dto.MaintenanceProjectDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MaintenanceProjectService {
    private MaintenanceProjectRepository maintenanceProjectRepository;
    private ModelMapper modelMapper;

    public MaintenanceProjectService(MaintenanceProjectRepository maintenanceProjectRepository,
                                     ModelMapper modelMapper) {
        this.maintenanceProjectRepository = maintenanceProjectRepository;
        this.modelMapper = modelMapper;
    }

    List<MaintenanceProjectDto> getMaintenanceProjects() {
        List<MaintenanceProject> all = maintenanceProjectRepository.findAll();
        return convert(all);
    }

    private List<MaintenanceProjectDto> convert(List<MaintenanceProject> all) {
        return all.stream()
            .map(project -> modelMapper.map(project, MaintenanceProjectDto.class))
            .collect(Collectors.toList());
    }

    public void addProject(AddMaintenanceProjectCommand command) {
        MaintenanceProject addingProject = command.create();
        maintenanceProjectRepository.save(addingProject);
    }
}
