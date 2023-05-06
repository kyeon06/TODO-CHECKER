package com.yuyun.todochecker.todo.mapper;

import com.yuyun.todochecker.todo.domain.Label;
import com.yuyun.todochecker.todo.dto.LabelDto;

import java.util.List;
import java.util.stream.Collectors;

public class LabelMapper {

    // Model -> Dto
    public static LabelDto convertToDto(Label label) {
        LabelDto labelDto = new LabelDto();

        labelDto.setLabelId(label.getLabelId());
        labelDto.setLabelTitle(label.getLabelTitle());
        labelDto.setLabelColor(label.getLabelColor());

        return labelDto;
    }

    // Dto -> List
    public static List<LabelDto> convertToDtoList(List<Label> labelList) {
        return labelList.stream().map(LabelMapper::convertToDto).collect(Collectors.toList());
    }

    // Dto -> Model
    public static Label convertToModel(LabelDto labelDto) {
        Label label = new Label();
        label.setLabelId(labelDto.getLabelId());
        label.setLabelTitle(labelDto.getLabelTitle());
        label.setLabelColor(labelDto.getLabelColor());
        return label;
    }
}
