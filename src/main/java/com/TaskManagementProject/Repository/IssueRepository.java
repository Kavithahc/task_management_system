package com.TaskManagementProject.Repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TaskManagementProject.Entity.Issue;
import com.TaskManagementProject.Enum.IssueStatus;

@Repository

public interface IssueRepository extends JpaRepository<Issue, Long> {
	Optional<Issue>findByIssueKey(String issueKey);
	Optional<Issue>findById(Long id);
	List<Issue>findByAssigneeEmail(String assigneeEmail);
	List<Issue>findBySprintId(Long sprintId);
	List<Issue>findByStatus(IssueStatus status);
	List<Issue> findByProjectIdAndSprintIdOrderByBackLogPositionAsc(Long projectId, Long sprintId);
	List<Issue> findByEpicId(Long epicId);
	
}

