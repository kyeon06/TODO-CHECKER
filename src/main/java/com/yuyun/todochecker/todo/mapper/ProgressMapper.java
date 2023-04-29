package com.yuyun.todochecker.todo.mapper;

import com.yuyun.todochecker.todo.domain.Label;
import com.yuyun.todochecker.todo.domain.Progress;
import com.yuyun.todochecker.todo.dto.LabelDto;
import com.yuyun.todochecker.todo.dto.ProgressDto;

import java.util.List;
import java.util.stream.Collectors;

public class ProgressMapper {

    // Model -> Dto
    public static ProgressDto convertToDto(Progress progress) {
        ProgressDto progressDto = new ProgressDto();

        progressDto.setProgressId(progress.getProgressId());
        progressDto.setRunDate(progress.getRunDate());
        progressDto.setRunRate(progress.getRunRate());

        return progressDto;
    }

    // Dto -> List
    public static List<ProgressDto> convertToDtoList(List<Progress> progressList) {
        return progressList.stream().map(ProgressMapper::convertToDto).collect(Collectors.toList());
    }

    // Dto -> Model
    public static Progress convertToModel(ProgressDto progressDto) {
        Progress progress = new Progress();
        progress.setProgressId(progressDto.getProgressId());
        progress.setRunDate(progressDto.getRunDate());
        progress.setRunRate(progressDto.getRunRate());
        return progress;
    }
}
