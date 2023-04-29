package com.yuyun.todochecker.todo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.yuyun.todochecker.todo.domain.Label;
import com.yuyun.todochecker.todo.domain.Todo;
import com.yuyun.todochecker.todo.dto.PostRequestDto;
import com.yuyun.todochecker.todo.dto.ProgressDto;
import com.yuyun.todochecker.todo.dto.TodoDto;
import com.yuyun.todochecker.todo.mapper.TodoMapper;
import com.yuyun.todochecker.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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

    // 일별 목록 조회
    @RequestMapping(value = "/{runDate}", method = RequestMethod.GET)
    public ResponseEntity<ProgressDto> getDailyTodoList(@PathVariable("runDate") final String runDate){
        ProgressDto res = todoService.getDailyTodoList(runDate);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    // 목록 삭제
    @RequestMapping(value = "/{todoId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteToDo(@PathVariable("todoId") final long todoId){
        String mg = todoService.deleteTodo(todoId);
        HttpHeaders header = new HttpHeaders();
        header.add("Content-Type", "application/json; charset=UTF-8");
        return new ResponseEntity<>(mg, header ,HttpStatus.OK);
    }
}
