package com.TaskManagementProject.Entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="user_profiles")
public class UserProfile {
	 @Id
	    @GeneratedValue(strategy=GenerationType.IDENTITY)
	    private Long id;

	    @Column(unique=true, nullable=false)
	    private String userOfficialEmail;

	    @Column(nullable=false)
	    private String userName;

	    private String designation;
	    private String department;
	    private String organizationName;

	    private LocalDateTime createdAt;

	    private boolean active = true;

	    public UserProfile() {
	    }

	    public UserProfile(String userOfficialEmail, String userName, String designation, String department, String organizationName) {
	        this.userOfficialEmail = userOfficialEmail;
	        this.userName = userName;
	        this.designation = designation;
	        this.department = department;
	        this.organizationName = organizationName;
	        this.createdAt = LocalDateTime.now();
	        this.active = true;
	    }

	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getUserOfficialEmail() {
	        return userOfficialEmail;
	    }

	    public void setUserOfficialEmail(String userOfficialEmail) {
	        this.userOfficialEmail = userOfficialEmail;
	    }

	    public String getUserName() {
	        return userName;
	    }

	    public void setUserName(String userName) {
	        this.userName = userName;
	    }

	    public String getDesignation() {
	        return designation;
	    }

	    public void setDesignation(String designation) {
	        this.designation = designation;
	    }

	    public String getDepartment() {
	        return department;
	    }

	    public void setDepartment(String department) {
	        this.department = department;
	    }

	    public String getOrganizationName() {
	        return organizationName;
	    }

	    public void setOrganizationName(String organizationName) {
	        this.organizationName = organizationName;
	    }

	    public LocalDateTime getCreatedAt() {
	        return createdAt;
	    }

	    public void setCreatedAt(LocalDateTime createdAt) {
	        this.createdAt = createdAt;
	    }

	    public boolean isActive() {
	        return active;
	    }

	    public void setActive(boolean active) {
	        this.active = active;
	    }

}
