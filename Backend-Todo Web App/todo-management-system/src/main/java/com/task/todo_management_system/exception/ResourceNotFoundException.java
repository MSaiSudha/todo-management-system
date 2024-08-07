package com.task.todo_management_system.exception;

public class ResourceNotFoundException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2298612740303535410L;

	public ResourceNotFoundException(String message) {
        super(message);
    }
}
