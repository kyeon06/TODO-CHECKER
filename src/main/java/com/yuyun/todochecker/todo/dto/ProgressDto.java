package com.yuyun.todochecker.todo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yuyun.todochecker.todo.domain.Todo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ProgressDto {

    private Long progressId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate runDate;
    private Long runRate;

}
