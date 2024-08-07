package com.task.todo_management_system.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.task.todo_management_system.entity.Task;
import com.task.todo_management_system.entity.TaskStatus;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

	Page<Task> findAll(Pageable pageable);
    
    Page<Task> findByStatus(TaskStatus status, Pageable pageable);
    
    @Query("SELECT t FROM Task t WHERE "
            + "(:id IS NULL OR t.id = :id) AND "
            + "(:title IS NULL OR t.title LIKE %:title%) AND "
            + "(:description IS NULL OR t.description LIKE %:description%) AND "
            + "(:createdAt IS NULL OR t.createdAt = :createdAt) AND "
            + "(:status IS NULL OR t.status = :status) AND "
            + "(:updatedAt IS NULL OR t.updatedAt = :updatedAt) AND "
            + "t.user.email = :userEmail")
    List<Task> searchTasks(Long id, String title, String description, LocalDate createdAt, TaskStatus status, LocalDate updatedAt, String userEmail);
}
