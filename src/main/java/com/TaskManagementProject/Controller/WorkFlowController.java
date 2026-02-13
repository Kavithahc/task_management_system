package com.TaskManagementProject.Controller;

import java.util.Optional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.TaskManagementProject.Entity.WorkFlow;
import com.TaskManagementProject.Entity.WorkFlowTransaction;
import com.TaskManagementProject.Service.WorkFlowService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class WorkFlowController {
	
	@Autowired
	private WorkFlowService workflowService;
	
	
	@PostMapping("/create")
	public ResponseEntity<WorkFlow> create(@RequestBody WorkFlow wf){
	    return ResponseEntity.ok(workflowService.createWorkFlow(wf));
	}

	@GetMapping("/list")
	public ResponseEntity<List<WorkFlow>> allList(){
	    return ResponseEntity.ok(workflowService.listAll());
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<WorkFlow> get(@PathVariable Long id) {
	    return ResponseEntity.ok(workflowService.getById(id));
	}

	@GetMapping("/name/{WorkFlowName}")
	public ResponseEntity<Optional<WorkFlow>> getByName(@PathVariable String WorkFlowName) {
	    return ResponseEntity.ok(workflowService.findByWorkFlowName(WorkFlowName));
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<WorkFlow> update(@PathVariable Long id,@RequestBody WorkFlow wf){
	    return ResponseEntity.ok(workflowService.updateWorkFlowStatus(id, wf));
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id){
	    workflowService.deleteWorkFlow(id);
	    return ResponseEntity.ok("Deleted");
	}

	@GetMapping("/{id}/transaction/{from}")
	public ResponseEntity<List<WorkFlowTransaction>> allowed(@PathVariable Long id,@PathVariable String fromStatus){
	    return ResponseEntity.ok(workflowService.allowedTransactions(id, fromStatus));
	
	}
}
