package com.task.todo_management_system.dto;

import java.time.LocalDate;
import java.util.List;

import com.task.todo_management_system.entity.Task;

public class UserResponseDTO {
	 private Long id;
	    private String username;
	    private String email;
	    private String phoneNumber;
	    private String place;
	    private LocalDate createdAt;
	    private LocalDate updatedAt;
	    private List<Task> tasks;
	    public UserResponseDTO(){
	    	super();
	    }
		
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPhoneNumber() {
			return phoneNumber;
		}
		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}
		public String getPlace() {
			return place;
		}
		public void setPlace(String place) {
			this.place = place;
		}
		public List<Task> getTasks() {
			return tasks;
		}
		public void setTasks(List<Task> tasks) {
			this.tasks = tasks;
		}

		public UserResponseDTO(Long id, String username, String email, String phoneNumber, String place,
				LocalDate createdAt, LocalDate updatedAt, List<Task> tasks) {
			super();
			this.id = id;
			this.username = username;
			this.email = email;
			this.phoneNumber = phoneNumber;
			this.place = place;
			this.createdAt = createdAt;
			this.updatedAt = updatedAt;
			this.tasks = tasks;
		}

		public LocalDate getCreatedAt() {
			return createdAt;
		}

		public void setCreatedAt(LocalDate createdAt) {
			this.createdAt = createdAt;
		}

		public LocalDate getUpdatedAt() {
			return updatedAt;
		}

		public void setUpdatedAt(LocalDate updatedAt) {
			this.updatedAt = updatedAt;
		}
		
	    
}
