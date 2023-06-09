package com.yuyun.todochecker.todo.controller;

import com.yuyun.todochecker.todo.dto.PostRequestDto;
import com.yuyun.todochecker.todo.dto.ProgressDto;
import com.yuyun.todochecker.todo.dto.TodoDto;
import com.yuyun.todochecker.todo.dto.TodoListDto;
import com.yuyun.todochecker.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    // 목록 수정
    @RequestMapping(value = "/{todoId}", method = RequestMethod.PUT)
    public ResponseEntity<TodoDto> updateToDo(@PathVariable("todoId") final long todoId, @RequestBody final TodoDto todoDto) {
        TodoDto res = todoService.updateToDo(todoId, todoDto);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    // 일별 목록 필터 목록 조회
    @RequestMapping(value = "/{runDate}/{labelId}", method = RequestMethod.GET)
    public ResponseEntity<TodoListDto> getFilterList(@PathVariable("runDate") String runDate, @PathVariable(value = "labelId") final long label) {
        TodoListDto res = todoService.getFilter(runDate, label);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
