package com.TaskManagementProject.Entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(
    name = "workflow_transaction",
    indexes = {
        @Index(name = "idx_wf_from_to", columnList = "workflowId,fromStatus,toStatus")
    }
)
public class WorkFlowTransaction {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="workflowId",nullable = false)
    private WorkFlow workFlow;

    private String fromStatus;
    private String toStatus;

    private String transactionName;
    private String allowedRole;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public WorkFlow getWorkFlow() {
		return workFlow;
	}
	public void setWorkFlow(WorkFlow workFlow) {
		this.workFlow = workFlow;
	}
	public String getFromStatus() {
		return fromStatus;
	}
	public void setFromStatus(String fromStatus) {
		this.fromStatus = fromStatus;
	}
	public String getToStatus() {
		return toStatus;
	}
	public void setToStatus(String toStatus) {
		this.toStatus = toStatus;
	}
	public String getTransactionName() {
		return transactionName;
	}
	public void setTransactionName(String transactionName) {
		this.transactionName = transactionName;
	}
	public String getAllowedRole() {
		return allowedRole;
	}
	public void setAllowedRole(String allowedRole) {
		this.allowedRole = allowedRole;
	}
    
}
