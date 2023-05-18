package com.yuyun.todochecker.todo.service;

import com.yuyun.todochecker.todo.domain.Progress;
import com.yuyun.todochecker.todo.domain.Todo;
import com.yuyun.todochecker.todo.dto.RunRateResponseDto;
import com.yuyun.todochecker.todo.repository.ProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class ProgressService {

    @Autowired
    private ProgressRepository progressRepository;

    // 진행률 계산
    public RunRateResponseDto updateProgressRate(String runDate){
        SimpleDateFormat beforeFormat = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat afterFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date tempDate = null;

        try {
            tempDate = beforeFormat.parse(runDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        String reqDate = afterFormat.format(tempDate);

        Progress currentData = progressRepository.findByRunDate(LocalDate.parse(reqDate)).get();
        List<Todo> currentTodoList = currentData.getTodoList();

        int totalTodo = currentTodoList.size();
        int completeTodo = 0;
        for (Todo todo : currentTodoList){
            if (todo.getStatus()) {
                completeTodo++;
            }
        }

        int runRate = Math.round(completeTodo / totalTodo * 100);

        RunRateResponseDto runRateResponseDto = new RunRateResponseDto();
        runRateResponseDto.setRunDate(LocalDate.parse(reqDate));
        runRateResponseDto.setRunRate(Long.valueOf(runRate));

        return runRateResponseDto;
    }
}
