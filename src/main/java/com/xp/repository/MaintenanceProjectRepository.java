package com.xp.repository;

import com.xp.domain.MaintenanceProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceProjectRepository extends JpaRepository<MaintenanceProject, String> {
}
