package com.TaskManagementProject.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TaskManagementProject.Entity.Issue;
import com.TaskManagementProject.Service.BackLogService;

@RestController
@RequestMapping("/api/backlogs")
public class BackLogController {
	@Autowired
    private BackLogService backLogService;

	 @GetMapping("/{projectId}")
	    public ResponseEntity<List<Issue>> getBackLog(@PathVariable Long projectId){
	        return ResponseEntity.ok(backLogService.getBackLog(projectId));
	    }

	    @PostMapping("/{projectId}/record")
	    public ResponseEntity<String> record(@PathVariable Long projectId,@RequestBody List<Long> orderIssueId){
	        backLogService.recordBackLog(projectId, orderIssueId);
	        return ResponseEntity.ok("BackLog recorded");
	    }

	    @PutMapping("/add_to_sprint/{issueId}/{sprintId}")
	    public ResponseEntity<Issue> addIssueToSprint(@PathVariable Long issueId,@PathVariable Long sprintId){
	        return ResponseEntity.ok(backLogService.addIssueToSprint(issueId, sprintId));
	    }

	    @GetMapping("/{projectId}/hierarchy")
	    public ResponseEntity<Map<String, Object>> getHierarchy(@PathVariable Long projectId){
	        return ResponseEntity.ok(backLogService.getBackLogHierarchy(projectId));
	    }
}
