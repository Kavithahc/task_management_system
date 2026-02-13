package com.TaskManagementProject.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TaskManagementProject.Entity.Board;
import com.TaskManagementProject.Entity.BoardColumn;

@Repository
public interface BoardColumnRepository extends JpaRepository<BoardColumn,Long> {
	List<Board> findByboardIdOrderByPosition(Long boardId);
}
