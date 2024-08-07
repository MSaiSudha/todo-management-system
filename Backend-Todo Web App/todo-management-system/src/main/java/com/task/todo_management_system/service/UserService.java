package com.task.todo_management_system.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.todo_management_system.entity.Role;
import com.task.todo_management_system.entity.User;
import com.task.todo_management_system.exception.UserAlreadyExistsException;
import com.task.todo_management_system.exception.UserNotFoundException;
import com.task.todo_management_system.repository.UserRepository;
import com.task.todo_management_system.serializer.PasswordUtils;

import jakarta.transaction.Transactional;

@Service
public class UserService {

	@Autowired private UserRepository userRepository;

	@Transactional
	public User registerUser(User user) {
		if (userRepository.findByEmail(user.getEmail())!=null) {
	       throw new UserAlreadyExistsException("User with email " + user.getEmail() + " already exists.");
	     }
        // Encrypt the password
        user.setPassword(PasswordUtils.hashPassword(user.getPassword()));
        user.setCreatedAt(LocalDate.now());
        user.setUpdatedAt(LocalDate.now());
        // Set default role if not provided
        if (user.getRole() == null) {
            user.setRole(Role.USER);
        }

        return userRepository.save(user);
    }

    public User loginUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && PasswordUtils.hashPassword(password).equals(user.getPassword())) {
            return user;
        }else {
        	throw new UserNotFoundException("Invalid User Details");
        }
    }

    public User getUserByEmail(String email) {
    	User user = userRepository.findByEmail(email);
    	if(user!=null) {
    		return user;
    	}
    	throw new UserNotFoundException("User not found with email: " + email);
    }
    
    public User getByUserId(Long Id) {
    	User user = userRepository.findUserById(Id);
    	if(user!=null) {
    		return user;
    	}
    	throw new UserNotFoundException("User not found with id: " + Id);	
    }
    
    
    @Transactional
    public User updateUser(Long userId, User updatedUser) {
        User existingUser = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

        // Update user details
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPhoneNumber(updatedUser.getPhoneNumber());
        existingUser.setPlace(updatedUser.getPlace());
        existingUser.setUpdatedAt(LocalDate.now());
        return userRepository.save(existingUser);
    }
    
}
