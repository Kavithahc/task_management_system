package com.TaskManagementProject.Repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TaskManagementProject.Entity.attachment;

@Repository

public interface AttachmentRepository extends JpaRepository<attachment,Long>{
	List<attachment>findByIssueId(Long issueId);

}
