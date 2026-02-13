package com.TaskManagementProject.Service;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.TaskManagementProject.Client.UserClient;
import com.TaskManagementProject.DTO.IssueDTO;
import com.TaskManagementProject.Entity.Issue;
import com.TaskManagementProject.Entity.IssueComent;
import com.TaskManagementProject.Enum.IssuePriority;
import com.TaskManagementProject.Enum.IssueStatus;
import com.TaskManagementProject.Enum.Role;
import com.TaskManagementProject.Repository.EpicRepository;
import com.TaskManagementProject.Repository.IssueCommentRepository;
import com.TaskManagementProject.Repository.IssueRepository;
import com.TaskManagementProject.Repository.SprintRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class IssueService {
	@Autowired
    private IssueRepository issueRepo;

    @Autowired
    private IssueCommentRepository issueCommentRepo;

    @Autowired
    private SprintRepository sprintRepo;

    @Autowired
    private EpicRepository epicRepo;

    // ✅ Proper Key Generator
    private String generateKey(Long id) {
        return "PROJECT-" + id;
    }

    // ✅ CREATE ISSUE
    @Transactional
    public IssueDTO createIssue(IssueDTO dto) {

        Issue issue = new Issue();

        issue.setIssueTitle(dto.getIssueTitle());
        issue.setIssueType(dto.getIssueType());
        issue.setAssigneeEmail(dto.getAssigneeEmail());
        issue.setRepoterEmail(dto.getReporterEmail());
        issue.setDescription(dto.getDescription());
        issue.setDueDate(dto.getDueDate());

        issue.setPriority(
                dto.getIssuePriority() != null ? dto.getIssuePriority() : IssuePriority.LOW);

        issue.setStatus(IssueStatus.OPEN);

        if (dto.getEpicId() != null) {
            epicRepo.findById(dto.getEpicId())
                    .orElseThrow(() -> new RuntimeException("Epic not found"));
            issue.setEpicId(dto.getEpicId());
        }

        if (dto.getSprintId() != null) {
            sprintRepo.findById(dto.getSprintId())
                    .orElseThrow(() -> new RuntimeException("Sprint not found"));
            issue.setSprintId(dto.getSprintId());
        }

        // ✅ Generate Key BEFORE saving
        long count = issueRepo.count();
        issue.setIssueKey("PROJECT-" + (count + 1));

        Issue savedIssue = issueRepo.save(issue);

        return toDTO(savedIssue);
    }
 // ✅ GET ISSUE BY ID
    public IssueDTO getById(Long id) {

        Issue issue = issueRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Issue not found"));
        
        return toDTO(issue);
    }
    public void deleteIssue(Long id) {
        Issue issue = issueRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Issue not found"));

        issueRepo.delete(issue);
    }
    public Issue updateStatus(Long id, IssueStatus status) {
        Issue issue = issueRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Issue not found"));

        issue.setStatus(status);
        return issueRepo.save(issue);
    }

    // ✅ UPDATE STATUS
    @Transactional
    public IssueDTO updateIssueStatus(Long id, IssueStatus status, String performBy) {

        Issue issue = issueRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Issue not found"));

        IssueStatus newStatus;

        try {
            newStatus = IssueStatus.valueOf(status.name().toUpperCase().trim());
        } catch (Exception e) {
            throw new RuntimeException("Invalid Status: " + status);
        }

        issue.setStatus(newStatus);
        issueRepo.save(issue);

        // Save comment
        IssueComent comment = new IssueComent();
        comment.setIssueId(id);
        comment.setAuthorEmail(performBy);
        comment.setBody("Status changed to: " + newStatus);

        issueCommentRepo.save(comment);

        return toDTO(issue);
    }

    // ✅ ADD COMMENT
    @Transactional
    public IssueComent addComment(Long issueId, String authorEmail, String body) {

        issueRepo.findById(issueId)
                .orElseThrow(() -> new RuntimeException("Issue not found"));

        IssueComent comment = new IssueComent();
        comment.setIssueId(issueId);
        comment.setAuthorEmail(authorEmail);
        comment.setBody(body);

        return issueCommentRepo.save(comment);
    }

    // ✅ SEARCH
    public List<IssueDTO> search(Map<String, String> filter) {

        if (filter.containsKey("assignee")) {
            return issueRepo.findByAssigneeEmail(filter.get("assignee"))
                    .stream()
                    .map(this::toDTO)
                    .collect(Collectors.toList());
        }

        if (filter.containsKey("status")) {
            String statusStr = filter.get("status");
            IssueStatus status;

            try {
                status = IssueStatus.valueOf(statusStr.toUpperCase().trim());
            } catch (Exception e) {
                throw new RuntimeException(
                        "Invalid Status: " + statusStr +
                        " | Allowed: " + Arrays.toString(IssueStatus.values()));
            }

            return issueRepo.findByStatus(status)
                    .stream()
                    .map(this::toDTO)
                    .collect(Collectors.toList());
        }

        if (filter.containsKey("sprint")) {
            Long sprintId = Long.valueOf(filter.get("sprint"));
            return issueRepo.findBySprintId(sprintId)
                    .stream()
                    .map(this::toDTO)
                    .collect(Collectors.toList());
        }

        return issueRepo.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // ✅ DTO MAPPER
    private IssueDTO toDTO(Issue issue) {

        IssueDTO dto = new IssueDTO();

        dto.setIssueTitle(issue.getIssueTitle());
        dto.setDescription(issue.getDescription());
        dto.setCreatedAt(issue.getCreatedAt());
        dto.setIssueKey(issue.getIssueKey());
        dto.setIssuePriority(issue.getPriority());
        dto.setIssueStatus(issue.getStatus());
        dto.setIssueType(issue.getIssueType());
        dto.setAssigneeEmail(issue.getAssigneeEmail());
        dto.setReporterEmail(issue.getRepoterEmail());
        dto.setUpdateAt(issue.getUpdateAt());
        dto.setEpicId(issue.getEpicId());
        dto.setSprintId(issue.getSprintId());

        return dto;
    }


}