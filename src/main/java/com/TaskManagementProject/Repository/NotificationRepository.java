package com.TaskManagementProject.Repository;
import org.springframework.stereotype.Repository;

import com.TaskManagementProject.Entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {


}
