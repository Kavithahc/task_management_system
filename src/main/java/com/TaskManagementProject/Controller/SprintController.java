package com.TaskManagementProject.Controller;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TaskManagementProject.Entity.Issue;
import com.TaskManagementProject.Entity.Sprint;
import com.TaskManagementProject.Service.SprintService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/sprints")
@RequiredArgsConstructor
public class SprintController {
	private final SprintService sprintService;

    // ✅ Constructor Injection (Best Practice)
    public SprintController(SprintService sprintService) {
        this.sprintService = sprintService;
    }

    // ✅ Create Sprint
    @PostMapping("/create")
    public ResponseEntity<Sprint> createSprint(@RequestBody Sprint sprint) {
        return new ResponseEntity<>(sprintService.createSprint(sprint), HttpStatus.CREATED);
    }

    // ✅ Assign Issue to Sprint
    @PutMapping("/{sprintId}/issues/{issueId}")
    public ResponseEntity<Issue> assignIssueToSprint(
            @PathVariable Long sprintId,
            @PathVariable Long issueId) {

        return ResponseEntity.ok(
                sprintService.assignIssueToSprint(sprintId, issueId));
    }

    // ✅ Start Sprint
    @PutMapping("/{sprintId}/start")
    public ResponseEntity<Sprint> startSprint(@PathVariable Long sprintId) {
        return ResponseEntity.ok(
                sprintService.startSprint(sprintId));
    }

    // ✅ Close Sprint
    @PutMapping("/{sprintId}/close")
    public ResponseEntity<Sprint> closeSprint(@PathVariable Long sprintId) {
        return ResponseEntity.ok(
                sprintService.closeSprint(sprintId));
    }

    // ✅ Get BurnDown Chart Data
    @GetMapping("/{sprintId}/burndown")
    public ResponseEntity<Map<String, Object>> getBurnDown(
            @PathVariable Long sprintId) {

        return ResponseEntity.ok(
                sprintService.getBurnDownData(sprintId));
    }
}
