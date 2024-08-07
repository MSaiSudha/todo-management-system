package com.task.todo_management_system.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.task.todo_management_system.dto.TaskDTO;
import com.task.todo_management_system.entity.Task;
import com.task.todo_management_system.entity.TaskStatus;
import com.task.todo_management_system.exception.ResourceNotFoundException;
import com.task.todo_management_system.service.TaskService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/tasks")
@CrossOrigin("http://localhost:3000/")
@Tag(name = "Task Management", description = "Endpoints for managing tasks")
public class TaskController {

	@Autowired private TaskService taskService;
	
	@Operation(summary = "Get all tasks", description = "Retrieve a list of all tasks with pagination and sorting")
	@GetMapping
    public Page<Task> getTasks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        return taskService.getTasks(page, size, sortBy);
    }

	@Operation(summary = "Get tasks by Id", description = "Retrieve task filtered by Id")
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Task task = taskService.getTaskById(id).orElseThrow(() -> new ResourceNotFoundException("Task not found"));
        return ResponseEntity.ok().body(task);
    }

    @Operation(summary = "Get tasks by status", description = "Retrieve tasks filtered by status with pagination and sorting")
    @GetMapping("/status/{status}")
    public Page<Task> getTasksByStatus(
    		@PathVariable TaskStatus status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        return taskService.getTasksByStatus(status, page, size, sortBy);
    }
    
    @Operation(summary = "Create a new task", description = "Create a new task with title, description, and status")
    @PostMapping
    public Task createTask(@RequestBody TaskDTO taskDto, @RequestParam Long userId) {
        return taskService.createTask(taskDto,userId);
    }

    @Operation(summary = "Update a task", description = "Update task details by ID")
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task taskDetails) {
        Task updatedTask = taskService.updateTask(id, taskDetails);
        return ResponseEntity.ok(updatedTask);
    }

    @Operation(summary = "Delete a task", description = "Delete a task by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<Task>> searchTasks(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) LocalDate createdAt,
            @RequestParam(required = false) TaskStatus status,
            @RequestParam(required = false) LocalDate updatedAt,
            @RequestParam(required = true) String userEmail) {
    	List<Task> searchTasks = taskService.searchTasks(id, title, description, createdAt, status, updatedAt,userEmail);
        return ResponseEntity.ok(searchTasks);
    }
}
