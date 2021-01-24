package com.xp.domain;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "maintenance_project")
@Getter
@Builder
public class MaintenanceProject extends AbstractAuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    private Integer cycle;
    private String unit;
    private LocalDate lastMaintainDate;
}
