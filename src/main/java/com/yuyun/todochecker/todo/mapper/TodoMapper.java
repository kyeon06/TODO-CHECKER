package com.yuyun.todochecker.todo.mapper;

import com.yuyun.todochecker.todo.domain.Progress;
import com.yuyun.todochecker.todo.domain.Todo;
import com.yuyun.todochecker.todo.dto.TodoDto;

import java.util.List;
import java.util.stream.Collectors;

public class TodoMapper {

    // Model -> Dto
    public static TodoDto convertToDto(Todo todo) {
        TodoDto todoDto = new TodoDto();

        // todo관련
        todoDto.setTodoId(todo.getTodoId());
        todoDto.setContent(todo.getContent());
        todoDto.setStatus(todo.getStatus());
        todoDto.setImportance(todo.getImportance());
        todoDto.setLabel(LabelMapper.convertToDto(todo.getLabel()));
        todoDto.setProgress(ProgressMapper.convertToDto(todo.getProgress()));
        todoDto.setCreatedAt(todo.getCreatedAt());
        todoDto.setUpdatedAt(todo.getUpdatedAt());

        return todoDto;

    }

    // Dto -> List
    public static List<TodoDto> convertToDtoList(List<Todo> todoList) {
        return todoList.stream().map(TodoMapper::convertToDto).collect(Collectors.toList());
    }

    // Dto -> Model
    public static Todo convertToModel(TodoDto todoDto) {
        Todo todo = new Todo();

        todo.setTodoId(todoDto.getTodoId());
        todo.setContent(todoDto.getContent());
        todo.setStatus(todoDto.getStatus());
        todo.setImportance(todoDto.getImportance());
        todo.setCreatedAt(todoDto.getCreatedAt());
        todo.setUpdatedAt(todoDto.getUpdatedAt());
        todo.setLabel(LabelMapper.convertToModel(todoDto.getLabel()));
        todo.setProgress(ProgressMapper.convertToModel(todoDto.getProgress()));

        return todo;
    }
}
