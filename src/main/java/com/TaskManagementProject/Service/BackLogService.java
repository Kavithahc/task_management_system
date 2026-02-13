package com.TaskManagementProject.Service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TaskManagementProject.Entity.Issue;
import com.TaskManagementProject.Entity.Sprint;
import com.TaskManagementProject.Enum.IssueType;
import com.TaskManagementProject.Enum.SprintState;
import com.TaskManagementProject.Repository.IssueRepository;
import com.TaskManagementProject.Repository.SprintRepository;

import jakarta.transaction.Transactional;

@Service
public class BackLogService {
	@Autowired
    private IssueRepository issueRepo;

    @Autowired
    private SprintRepository sprintRepo;

    public List<Issue> getBackLog(Long projectId) {
        if (projectId == null) return new ArrayList<>();
        // Note: Ensure your Repository has findByProjectIdAndSprintIdIsNullOrderByBackLogPositionAsc
        return issueRepo.findByProjectIdAndSprintIdOrderByBackLogPositionAsc(projectId, null);
    }

    @Transactional
   
    public void recordBackLog(Long projectId, List<Long> orderIssueId) {
        Long pos = 0L; // Change 'int' to 'Long' and add 'L'
        for (Long issueId : orderIssueId) {
            Issue issue = issueRepo.findById(issueId).orElseThrow(() -> new RuntimeException("Issue not found"));
            issue.setBackLogPosition(pos++); // This will now work
            issueRepo.save(issue);
        }
    }

    @Transactional
    public Issue addIssueToSprint(Long issueId, Long sprintId) {
        Issue issue = issueRepo.findById(issueId).orElseThrow(() -> new RuntimeException("issue not found"));
        Sprint sprint = sprintRepo.findById(sprintId).orElseThrow(() -> new RuntimeException("sprint not found"));

        SprintState sprintState = sprint.getState();
        if (sprintState != SprintState.PLANNED && sprintState != SprintState.ACTIVE) {
            throw new RuntimeException("cannot add issue to sprint in state: " + sprintState);
        }
        issue.setSprintId(sprintId);
        issue.setBackLogPosition(null);
        return issueRepo.save(issue);
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> getBackLogHierarchy(Long projectId) {
        List<Issue> backLog = getBackLog(projectId);
        
        // Use Long as the key because Issue IDs are Long
        Map<Long, Map<String, Object>> epicMap = new LinkedHashMap<>();

        // 1. Identify Epics
        for (Issue i : backLog) {
            if (i.getIssueType() == IssueType.EPICS) {
                Map<String, Object> data = new LinkedHashMap<>();
                data.put("epic", i);
                data.put("stories", new ArrayList<Issue>());
                data.put("subtasks", new HashMap<Long, List<Issue>>());
                epicMap.put(i.getId(), data);
            }
        }

        // 2. Assign Stories to Epics
        for (Issue i : backLog) {
            if (i.getIssueType() == IssueType.STORIES && i.getEpicId() != null) {
                Map<String, Object> epicData = epicMap.get(i.getEpicId());
                if (epicData != null) {
                    List<Issue> stories = (List<Issue>) epicData.get("stories");
                    stories.add(i);
                }
            }
        }

        // 3. Assign Subtasks to Stories
        for (Issue i : backLog) {
            if (i.getIssueType() == IssueType.SUBTASKS && i.getSourceIssueId() != null) {
                for (Map<String, Object> epicData : epicMap.values()) {
                    List<Issue> stories = (List<Issue>) epicData.get("stories");
                    for (Issue story : stories) {
                        if (story.getId().equals(i.getSourceIssueId())) {
                            Map<Long, List<Issue>> subTasksMap = (Map<Long, List<Issue>>) epicData.get("subtasks");
                            subTasksMap.computeIfAbsent(story.getId(), k -> new ArrayList<>()).add(i);
                        }
                    }
                }
            }
        }
        return Collections.singletonMap("epics", new ArrayList<>(epicMap.values()));
    }
}
