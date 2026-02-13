package com.TaskManagementProject.Repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TaskManagementProject.Entity.NotificationScheme;
import com.TaskManagementProject.Enum.NotificationEvent;

@Repository
public interface NotificationSchemeRepository extends JpaRepository<NotificationScheme,Long> {
	Optional<NotificationScheme>findByProjectIdAndEventType(Long projectId,NotificationEvent eventType);


}
