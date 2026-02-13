package com.TaskManagementProject.Entity;
import java.time.LocalDateTime;

import com.TaskManagementProject.Enum.IssuePriority;
import com.TaskManagementProject.Enum.IssueStatus;
import com.TaskManagementProject.Enum.IssueType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name="issues")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Issue {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique=true,nullable=false)
	private String issueKey;
	@Column(nullable=false)
	private String issueTitle;
	
	@Column(columnDefinition = "TEXT")
	private String description;
	
	@Enumerated(EnumType.STRING)
	private IssueType issueType;
	
	@Enumerated(EnumType.STRING)
	private IssuePriority priority;
	
	@Enumerated(EnumType.STRING)
	private IssueStatus status;
	@PrePersist
	public void prePersist() {
	    this.createdAt = java.time.LocalDateTime.now();
	    this.updateAt = java.time.LocalDateTime.now();
	}

	@PreUpdate
	public void preUpdate() {
	    this.updateAt = java.time.LocalDateTime.now();
	}
	
	private String assigneeEmail;
	private String repoterEmail;
	
	private LocalDateTime createdAt=LocalDateTime.now();
	private LocalDateTime  updateAt= LocalDateTime.now();
	
	private LocalDateTime dueDate;
	
	
	private Long SourceIssueId;
	private Long projectId;
	private Long sprintId;
	private Long epicId;
	private Long backLogPosition;
	
	private Long WorkFlowId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIssueKey() {
		return issueKey;
	}

	public void setIssueKey(String issueKey) {
		this.issueKey = issueKey;
	}

	public String getIssueTitle() {
		return issueTitle;
	}

	public void setIssueTitle(String issueTitle) {
		this.issueTitle = issueTitle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public IssueType getIssueType() {
		return issueType;
	}

	public void setIssueType(IssueType issueType) {
		this.issueType = issueType;
	}

	public IssuePriority getPriority() {
		return priority;
	}

	public void setPriority(IssuePriority priority) {
		this.priority = priority;
	}

	public IssueStatus getStatus() {
		return status;
	}

	public void setStatus(IssueStatus status) {
		this.status = status;
	}

	public String getAssigneeEmail() {
		return assigneeEmail;
	}

	public void setAssigneeEmail(String assigneeEmail) {
		this.assigneeEmail = assigneeEmail;
	}

	public String getRepoterEmail() {
		return repoterEmail;
	}

	public void setRepoterEmail(String repoterEmail) {
		this.repoterEmail = repoterEmail;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(LocalDateTime updateAt) {
		this.updateAt = updateAt;
	}

	public LocalDateTime getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDateTime dueDate) {
		this.dueDate = dueDate;
	}

	public Long getSourceIssueId() {
		return SourceIssueId;
	}

	public void setSourceIssueId(Long sourceIssueId) {
		SourceIssueId = sourceIssueId;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public Long getSprintId() {
		return sprintId;
	}

	public void setSprintId(Long sprintId) {
		this.sprintId = sprintId;
	}

	public Long getEpicId() {
		return epicId;
	}

	public void setEpicId(Long epicId) {
		this.epicId = epicId;
	}

	public Long getBackLogPosition() {
		return backLogPosition;
	}

	public void setBackLogPosition(Long backLogPosition) {
		this.backLogPosition = backLogPosition;
	}

	public Long getWorkFlowId() {
		return WorkFlowId;
	}

	public void setWorkFlowId(Long workFlowId) {
		WorkFlowId = workFlowId;
	}
	
	
	}
	
		
	
	
	
	

	


