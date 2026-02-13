package com.TaskManagementProject.Controller;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.TaskManagementProject.DTO.IssueDTO;
import com.TaskManagementProject.Entity.Issue;
import com.TaskManagementProject.Entity.IssueComent;
import com.TaskManagementProject.Enum.IssueStatus;
import com.TaskManagementProject.Service.IssueService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/issues")
@RequiredArgsConstructor
public class IssueController {
	@Autowired
    private IssueService issueService;

    // ✅ CREATE ISSUE
    @PostMapping("/create")
    public ResponseEntity<IssueDTO> createIssue(@RequestBody IssueDTO dto) {
        return ResponseEntity.ok(issueService.createIssue(dto));
    }

    // ✅ GET ISSUE BY ID
    @GetMapping("/{id}")
    public ResponseEntity<IssueDTO> getIssueById(@PathVariable Long id) {
        return ResponseEntity.ok(issueService.getById(id));
    }

    // ✅ UPDATE ISSUE STATUS
    @PutMapping("/{id}/status")
    public ResponseEntity<IssueDTO> updateStatus(
            @PathVariable Long id,
            @RequestParam IssueStatus status,
            @RequestParam String performBy) {

        return ResponseEntity.ok(
                issueService.updateIssueStatus(id, status, performBy));
    }

    // ✅ ADD COMMENT
    @PostMapping("/{id}/comments")
    public ResponseEntity<IssueComent> addComment(
            @PathVariable Long id,
            @RequestParam String authorEmail,
            @RequestParam String body) {

        return ResponseEntity.ok(
                issueService.addComment(id, authorEmail, body));
    }

    // ✅ SEARCH ISSUES
    @GetMapping("/search")
    public ResponseEntity<List<IssueDTO>> search(
            @RequestParam Map<String, String> filter) {

        return ResponseEntity.ok(issueService.search(filter));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteIssue(@PathVariable Long id) {
        issueService.deleteIssue(id);
        return ResponseEntity.ok("Issue deleted successfully");
    }
    @PatchMapping("/{id}/status")
    public ResponseEntity<Issue> updateIssueStatus(
            @PathVariable Long id,
            @RequestParam IssueStatus status) {

        return ResponseEntity.ok(issueService.updateStatus(id, status));
    }
}
