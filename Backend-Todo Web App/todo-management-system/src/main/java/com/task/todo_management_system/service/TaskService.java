package com.task.todo_management_system.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.task.todo_management_system.dto.TaskDTO;
import com.task.todo_management_system.entity.Task;
import com.task.todo_management_system.entity.TaskStatus;
import com.task.todo_management_system.entity.User;
import com.task.todo_management_system.exception.ResourceNotFoundException;
import com.task.todo_management_system.exception.UserNotFoundException;
import com.task.todo_management_system.repository.TaskRepository;
import com.task.todo_management_system.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class TaskService {

	@Autowired TaskRepository taskRepository;
	@Autowired UserRepository userRepository;
	
	public Page<Task> getTasks(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return taskRepository.findAll(pageable);
    }
	
	public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }
	
	public Page<Task> getTasksByStatus(TaskStatus status, int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return taskRepository.findByStatus(status, pageable);
    }

	@Transactional
    public Task createTask(TaskDTO taskDto, Long userId) { 
		User user = userRepository.findById(userId)
	            .orElseThrow(() -> new UserNotFoundException("User not found"));
		Task task = new Task();
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setStatus(taskDto.getStatus());
        task.setCreatedAt(LocalDate.now());
        task.setUpdatedAt(LocalDate.now());
        task.setUser(user); 
        return taskRepository.save(task);
    }
    
	@Transactional
    public Task updateTask(Long id, Task taskDetails) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task not found"));
        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setStatus(taskDetails.getStatus());
        task.setUpdatedAt(LocalDate.now());
        return taskRepository.save(task);
    }
    
	@Transactional
    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task not found"));
        taskRepository.delete(task);
    }
	
	public List<Task> searchTasks(Long id, String title, String description, LocalDate createdAt, TaskStatus status, LocalDate updatedAt,String userEmail) {
        return taskRepository.searchTasks(id, title, description, createdAt, status, updatedAt,userEmail);
    }
}
