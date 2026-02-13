package com.TaskManagementProject.Repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TaskManagementProject.Entity.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board,Long> {
	Optional<Board>findByProjectKey(String projectKey);
}
