package com.TaskManagementProject.Repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TaskManagementProject.Entity.Epic;
import com.TaskManagementProject.Entity.Issue;

@Repository

public interface EpicRepository extends JpaRepository<Epic,Long>{
	List<Issue>findByProjectIdAndSprintIdOrderByBackLogPosition(Long ProjectId, Long sprintId);
}
