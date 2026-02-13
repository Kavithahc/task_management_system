package com.TaskManagementProject.Repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.TaskManagementProject.Entity.UserAuthentication;

@Repository

public interface UserAuthenticationRepository 	extends JpaRepository<UserAuthentication,Long> {
		
		Optional<UserAuthentication>findByUserOfficialEmail(String userOfficalEmail);


}
