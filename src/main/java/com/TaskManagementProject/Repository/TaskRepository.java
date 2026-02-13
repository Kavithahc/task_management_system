package com.TaskManagementProject.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TaskManagementProject.Entity.Task;
@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{

}
