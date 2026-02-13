package com.TaskManagementProject.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.TaskManagementProject.Enum.IssueStatus;

@FeignClient(name="issue-service" , url="${http://localhost:8080}")
public interface IssueClient {
	@PutMapping("/api/issue/{id}/status")
	void updateStatus(@PathVariable Long id,
					  @RequestParam IssueStatus status,
					  @RequestParam String performedBy);
	@PostMapping("/api/issue/{id}/comment")
	void addComment(@PathVariable Long id,
					@RequestParam String author,
					@RequestParam String body);
}
