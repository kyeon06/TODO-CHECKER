package com.yuyun.todochecker.todo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class TodoDto {

    private Long todoId;
    private String content;
    private Boolean status;
    private Boolean importance;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate createdAt;
    private LocalDate updatedAt;

    private LabelDto label;
    private ProgressDto progress;

}
