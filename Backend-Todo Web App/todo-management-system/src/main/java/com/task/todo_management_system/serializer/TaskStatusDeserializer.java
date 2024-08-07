package com.task.todo_management_system.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.task.todo_management_system.entity.TaskStatus;

public class TaskStatusDeserializer extends JsonDeserializer<TaskStatus>{

	@Override
	public TaskStatus deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
		
		String value = p.getValueAsString().toUpperCase().replace(" ", "_");
        return TaskStatus.valueOf(value);
	}

}
