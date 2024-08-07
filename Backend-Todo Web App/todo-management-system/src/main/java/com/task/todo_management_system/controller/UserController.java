package com.task.todo_management_system.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.todo_management_system.dto.LoginUserDTO;
import com.task.todo_management_system.dto.RegisterUserDTO;
import com.task.todo_management_system.dto.UserResponseDTO;
import com.task.todo_management_system.dto.UserUpdateDTO;
import com.task.todo_management_system.entity.User;
import com.task.todo_management_system.exception.ErrorResponse;
import com.task.todo_management_system.exception.UserNotFoundException;
import com.task.todo_management_system.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

@RestController
@RequestMapping("/user")
@CrossOrigin("http://localhost:3000/")
public class UserController {

    @Autowired private UserService userService;

    @Operation(summary = "Register a new user", description = "Register a new user with a username, email, password, phone number, and place.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "User successfully registered"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterUserDTO userRegisterDTO, BindingResult bindingResult) {
    	if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), errors.values().iterator().next());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        user.setUsername(userRegisterDTO.getUsername());
        user.setEmail(userRegisterDTO.getEmail());
        user.setPassword(userRegisterDTO.getPassword());
        user.setPhoneNumber(userRegisterDTO.getPhoneNumber());
        user.setPlace(userRegisterDTO.getPlace());

        User registeredUser = userService.registerUser(user);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    @Operation(summary = "Login a user", description = "Login a user with email and password.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Login successful"),
        @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@Valid @RequestBody LoginUserDTO userLoginDTO) {
        User user = userService.loginUser(userLoginDTO.getEmail(), userLoginDTO.getPassword());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Operation(summary = "Get user by email", description = "Retrieve user details using their email address.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User found"),
        @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponseDTO> getUserByEmail(@PathVariable @NotBlank String email) {
        User user = userService.getUserByEmail(email);
        if (user != null) {
            UserResponseDTO userResponseDTO = new UserResponseDTO();
            userResponseDTO.setId(user.getId());
            userResponseDTO.setUsername(user.getUsername());
            userResponseDTO.setEmail(user.getEmail());
            userResponseDTO.setPhoneNumber(user.getPhoneNumber());
            userResponseDTO.setPlace(user.getPlace());
            userResponseDTO.setTasks(user.getTasks());
            userResponseDTO.setCreatedAt(user.getCreatedAt());
            userResponseDTO.setUpdatedAt(user.getUpdatedAt());
            return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
        } else {
            throw new UserNotFoundException("User not found with email: " + email);
        }
    }

    @Operation(summary = "Get user by id", description = "Retrieve user details using their id.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User found"),
        @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
    	  User user = userService.getByUserId(id);
          if (user != null) {
              UserResponseDTO userResponseDTO = new UserResponseDTO();
              userResponseDTO.setId(user.getId());
              userResponseDTO.setUsername(user.getUsername());
              userResponseDTO.setEmail(user.getEmail());
              userResponseDTO.setPhoneNumber(user.getPhoneNumber());
              userResponseDTO.setPlace(user.getPlace());
              userResponseDTO.setTasks(user.getTasks());
              userResponseDTO.setCreatedAt(user.getCreatedAt());
              userResponseDTO.setUpdatedAt(user.getUpdatedAt());
              return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
          } else {
              throw new UserNotFoundException("User not found with id: " + id);
          }
    }

    @Operation(summary = "Update user details", description = "Update user details except for password.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User successfully updated"),
        @ApiResponse(responseCode = "404", description = "User not found"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @Valid @RequestBody UserUpdateDTO userUpdateDTO) {
        User updatedUser = new User();
        updatedUser.setEmail(userUpdateDTO.getEmail());
        updatedUser.setUsername(userUpdateDTO.getUsername());
        updatedUser.setPhoneNumber(userUpdateDTO.getPhoneNumber());
        updatedUser.setPlace(userUpdateDTO.getPlace());
        User user = userService.updateUser(userId, updatedUser);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
