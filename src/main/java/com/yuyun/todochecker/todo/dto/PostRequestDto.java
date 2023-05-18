package com.yuyun.todochecker.todo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
public class PostRequestDto {

    private String content;
    private String createdAt;
    private String labelTitle;
    private String labelColor;

}
