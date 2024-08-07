package com.task.todo_management_system.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterUserDTO {
	 @NotBlank(message = "Username is mandatory")
	    private String username;
	    
	    @Email(message = "Email should be valid")
	    @NotBlank(message = "Email is mandatory")
	    private String email;
	    
	    @NotBlank(message = "Password is mandatory")
	    @Size(min = 4, message = "Password should have at least 6 characters")
	    private String password;
	    
	    @NotBlank(message = "Phone number is mandatory")
	    private String phoneNumber;
	    
	    @NotBlank(message = "Place is mandatory")
	    private String place;
	    
	    RegisterUserDTO(){
	    	super();
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

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
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

		public RegisterUserDTO(String username, String email, String password, String phoneNumber, String place) {
			super();
			this.username = username;
			this.email = email;
			this.password = password;
			this.phoneNumber = phoneNumber;
			this.place = place;
		}
	    
}
