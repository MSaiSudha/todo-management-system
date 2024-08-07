package com.task.todo_management_system.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserUpdateDTO {
	 @NotBlank(message = "Username is mandatory")
	    private String username;

	    @NotBlank(message = "Email is mandatory")
	    @Email(message = "Email should be valid")
	    private String email;

	    @NotBlank(message = "Phone number is mandatory")
	    private String phoneNumber;

	    @NotBlank(message = "Place is mandatory")
	    private String place;
	    
	    UserUpdateDTO(){
	    	super();
	    }

		public UserUpdateDTO(String username, String email, String phoneNumber, String place) {
			super();
			this.username = username;
			this.email = email;
			this.phoneNumber = phoneNumber;
			this.place = place;
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
	    
}
