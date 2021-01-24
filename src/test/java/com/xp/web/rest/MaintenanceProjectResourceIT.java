package com.xp.web.rest;

import com.xp.AutoMaintenanceApp;
import com.xp.domain.MaintenanceProject;
import com.xp.repository.MaintenanceProjectRepository;
import com.xp.security.AuthoritiesConstants;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WithMockUser(authorities = AuthoritiesConstants.ADMIN)
@SpringBootTest(classes = AutoMaintenanceApp.class)
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
