package com.TaskManagementProject.Controller;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.TaskManagementProject.Entity.Task;
import com.TaskManagementProject.Repository.TaskRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController {
	@Autowired
    private TaskRepository taskRepository;

    // ===================== GET =====================
    // GET /tasks
    @GetMapping
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // ===================== POST =====================
    // POST /tasks
    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskRepository.save(task);
    }

    // ===================== PUT =====================
    // PUT /tasks/{id}
    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task task) {
        Task existing = taskRepository.findById(id).orElseThrow();
        existing.setTitle(task.getTitle());
        existing.setStatus(task.getStatus());
        return taskRepository.save(existing);
    }

    // ===================== PATCH =====================
    // PATCH /tasks/{id}
    @PatchMapping("/{id}")
    public Task patchTask(@PathVariable Long id, @RequestBody Map<String, String> updates) {
        Task task = taskRepository.findById(id).orElseThrow();

        if (updates.containsKey("title")) {
            task.setTitle(updates.get("title"));
        }
        if (updates.containsKey("status")) {
            task.setStatus(updates.get("status"));
        }

        return taskRepository.save(task);
    }

    // ===================== DELETE =====================
    // DELETE /tasks/{id}
    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskRepository.deleteById(id);
        return "Task deleted successfully";
    }

    // ===================== HEAD =====================
    // HEAD /tasks
    @RequestMapping(method = RequestMethod.HEAD)
    public ResponseEntity<Void> headRequest() {
        return ResponseEntity.ok().build();
    }

    // ===================== OPTIONS =====================
 // ===================== OPTIONS =====================
 // OPTIONS /tasks
 @RequestMapping(method = RequestMethod.OPTIONS)
 public ResponseEntity<Void> optionsRequest() {

     HttpHeaders headers = new HttpHeaders();
     headers.setAllow(Set.of(
             org.springframework.http.HttpMethod.GET,
             org.springframework.http.HttpMethod.POST,
             org.springframework.http.HttpMethod.PUT,
             org.springframework.http.HttpMethod.PATCH,
             org.springframework.http.HttpMethod.DELETE,
             org.springframework.http.HttpMethod.HEAD,
             org.springframework.http.HttpMethod.OPTIONS
     ));

     return new ResponseEntity<>(headers, HttpStatus.OK);
 }
}
