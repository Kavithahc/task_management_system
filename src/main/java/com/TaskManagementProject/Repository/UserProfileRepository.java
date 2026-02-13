package com.TaskManagementProject.Repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TaskManagementProject.Entity.UserProfile;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile,Long>{
	Optional<UserProfile> findByUserOfficialEmail(String userOfficialEmail);
    List<UserProfile> findByDesignation(String designation);
    List<UserProfile> findByDepartment(String department);
}
