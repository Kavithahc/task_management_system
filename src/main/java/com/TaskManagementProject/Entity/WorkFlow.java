package com.TaskManagementProject.Entity;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="work_flow")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class WorkFlow {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    @Column(nullable = false, unique = true)
	    private String workflowName;
	    @Column(length = 5000)
	    private String workFlowDescription;

	    private LocalDateTime createdAt=LocalDateTime.now();

	    @OneToMany(mappedBy = "workFlow" ,cascade = CascadeType.ALL,orphanRemoval = true)
	    private List<WorkFlowTransaction> transaction=new ArrayList<>();

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getWorkflowName() {
			return workflowName;
		}

		public void setWorkflowName(String workflowName) {
			this.workflowName = workflowName;
		}

		public String getWorkFlowDescription() {
			return workFlowDescription;
		}

		public void setWorkFlowDescription(String workFlowDescription) {
			this.workFlowDescription = workFlowDescription;
		}

		public LocalDateTime getCreatedAt() {
			return createdAt;
		}

		public void setCreatedAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
		}

		public List<WorkFlowTransaction> getTransaction() {
			return transaction;
		}

		public void setTransaction(List<WorkFlowTransaction> transaction) {
			this.transaction = transaction;
		}

}

