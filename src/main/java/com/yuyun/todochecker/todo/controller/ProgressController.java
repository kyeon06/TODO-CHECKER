package com.yuyun.todochecker.todo.controller;

import com.yuyun.todochecker.todo.dto.RunRateResponseDto;
import com.yuyun.todochecker.todo.service.ProgressService;
import com.yuyun.todochecker.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/progress")
public class ProgressController {

    @Autowired
    private TodoService todoService;

    @Autowired
    private ProgressService progressService;

    // 진행률 업데이트
    @RequestMapping(value = "/{runDate}", method = RequestMethod.GET)
    public ResponseEntity<RunRateResponseDto> getRunRate(@PathVariable("runDate") final String runDate){
        RunRateResponseDto res = progressService.updateProgressRate(runDate);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
