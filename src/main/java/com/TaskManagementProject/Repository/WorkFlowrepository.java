package com.TaskManagementProject.Repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TaskManagementProject.Entity.WorkFlow;

@Repository
public interface WorkFlowrepository extends JpaRepository<WorkFlow,Long>{
	Optional<WorkFlow> findByWorkflowName(String workflowName);
}
