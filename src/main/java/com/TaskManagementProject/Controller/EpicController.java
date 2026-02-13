package com.TaskManagementProject.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.TaskManagementProject.Entity.Epic;
import com.TaskManagementProject.Repository.EpicRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/epics")
@RequiredArgsConstructor
public class EpicController {
	private final EpicRepository epicRepo;

    // ✅ Constructor Injection
    public EpicController(EpicRepository epicRepo) {
        this.epicRepo = epicRepo;
    }

    @PostMapping
    public ResponseEntity<Epic> createEpic(@RequestBody Epic epic) {
        return ResponseEntity.ok(epicRepo.save(epic));
    }

    @GetMapping
    public ResponseEntity<List<Epic>> getAllEpics() {
        return ResponseEntity.ok(epicRepo.findAll());
    }
}
