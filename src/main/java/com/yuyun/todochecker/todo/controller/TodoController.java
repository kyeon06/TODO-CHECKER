package com.yuyun.todochecker.todo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.yuyun.todochecker.todo.domain.Label;
import com.yuyun.todochecker.todo.domain.Todo;
import com.yuyun.todochecker.todo.dto.PostRequestDto;
import com.yuyun.todochecker.todo.dto.TodoDto;
import com.yuyun.todochecker.todo.mapper.TodoMapper;
import com.yuyun.todochecker.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
public class TodoController {

    @Autowired
    private TodoService todoService;

    // 목록 생성
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<TodoDto> createToDo(@RequestBody final PostRequestDto requestDto) {
        TodoDto savedTodo = todoService.createTodo(requestDto);
        return new ResponseEntity<>(savedTodo, HttpStatus.OK);
    }
}