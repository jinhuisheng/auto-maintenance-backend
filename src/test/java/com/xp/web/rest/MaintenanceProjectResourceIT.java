package com.xp.web.rest;

import com.xp.AutoMaintenanceApp;
import com.xp.domain.MaintenanceProject;
import com.xp.repository.MaintenanceProjectRepository;
import com.xp.security.AuthoritiesConstants;
import com.xp.service.command.AddMaintenanceProjectCommand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WithMockUser(authorities = AuthoritiesConstants.ADMIN)
@SpringBootTest(classes = AutoMaintenanceApp.class)
@Transactional
public class MaintenanceProjectResourceIT {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    MaintenanceProjectRepository maintenanceProjectRepository;

    @Test
    void should_get_my_car_maintenanceProjects_success() throws Exception {
        givenMaintenanceProjects();
        mockMvc.perform(get("/api/maintenance-projects"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.size()").value(1))
            .andExpect(jsonPath("$.[0].name").value("机油"))
            .andExpect(jsonPath("$.[0].type").value("保养"))
            .andExpect(jsonPath("$.[0].cycle").value(3))
            .andExpect(jsonPath("$.[0].unit").value("day"))
            .andExpect(jsonPath("$.[0].lastMaintainDate").value("2020-12-01"));
    }

    @Test
    void should_add_car_maintenanceProject_success() throws Exception {
        AddMaintenanceProjectCommand addMaintenanceProject = createCommand();

        mockMvc.perform(post("/api/maintenance-projects")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(addMaintenanceProject)))
            .andExpect(status().isOk());

        List<MaintenanceProject> all = maintenanceProjectRepository.findAll();
        assertThat(all.size()).isEqualTo(1);
        MaintenanceProject savedMaintenanceProject = all.get(0);
        assertThat(savedMaintenanceProject.getName()).isEqualTo(addMaintenanceProject.getName());
        assertThat(savedMaintenanceProject.getCycle()).isEqualTo(addMaintenanceProject.getCycle());
        assertThat(savedMaintenanceProject.getType()).isEqualTo(addMaintenanceProject.getType());
        assertThat(savedMaintenanceProject.getUnit()).isEqualTo(addMaintenanceProject.getUnit());
        assertThat(savedMaintenanceProject.getLastMaintainDate()).isEqualTo(addMaintenanceProject.getLastMaintainDate());
    }

    private AddMaintenanceProjectCommand createCommand() {
        AddMaintenanceProjectCommand addMaintenanceProject = new AddMaintenanceProjectCommand();
        addMaintenanceProject.setName("机油");
        addMaintenanceProject.setType("保养");
        addMaintenanceProject.setCycle(3);
        addMaintenanceProject.setUnit("day");
        addMaintenanceProject.setLastMaintainDate(LocalDate.of(2020, 12, 1));
        return addMaintenanceProject;
    }

    private void givenMaintenanceProjects() {
        MaintenanceProject maintenanceProject = createProject();
        maintenanceProjectRepository.save(maintenanceProject);
    }

    private MaintenanceProject createProject() {
        return MaintenanceProject.builder()
            .name("机油")
            .type("保养")
            .cycle(3)
            .unit("day")
            .lastMaintainDate(LocalDate.of(2020, 12, 1))
            .build();
    }
}
