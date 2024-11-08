package com.mock.repositoty;


import com.mock.entities.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, String> {
    List<Project> findAll();

    Project findByProjectName(String projectName);

    Page<Project> findAll(Pageable pageable);
}
