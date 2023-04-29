package com.yuyun.todochecker.todo.service;

import com.yuyun.todochecker.todo.domain.Label;
import com.yuyun.todochecker.todo.domain.Progress;
import com.yuyun.todochecker.todo.domain.Todo;
import com.yuyun.todochecker.todo.dto.LabelDto;
import com.yuyun.todochecker.todo.dto.PostRequestDto;
import com.yuyun.todochecker.todo.dto.ProgressDto;
import com.yuyun.todochecker.todo.dto.TodoDto;
import com.yuyun.todochecker.todo.mapper.LabelMapper;
import com.yuyun.todochecker.todo.mapper.ProgressMapper;
import com.yuyun.todochecker.todo.mapper.TodoMapper;
import com.yuyun.todochecker.todo.repository.LabelRepository;
import com.yuyun.todochecker.todo.repository.ProgressRepository;
import com.yuyun.todochecker.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.Optional;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private LabelRepository labelRepository;

    @Autowired
    private ProgressRepository progressRepository;

    // create
    public TodoDto createTodo(PostRequestDto requestDto) {
        // Progress
        ProgressDto progressDto = new ProgressDto();

        // progress에 해당 날짜가 존재가 없다면 새로 저장
        if (progressRepository.findByRunDate(requestDto.getCreatedAt()).isEmpty()){
            progressDto.setRunDate(requestDto.getCreatedAt());
            Progress progress = ProgressMapper.convertToModel(progressDto);
            this.progressRepository.save(progress);
        }

        Optional<Progress> resProgress = progressRepository.findByRunDate(requestDto.getCreatedAt());

        // Label
        LabelDto labelDto = new LabelDto();

        if (labelRepository.findByLabelTitle(requestDto.getLabelTitle()).isEmpty()){
            labelDto.setLabelTitle(requestDto.getLabelTitle());
            labelDto.setLabelColor(requestDto.getLabelColor());
            Label label = LabelMapper.convertToModel(labelDto);
            this.labelRepository.save(label);
        }

        Optional<Label> resLabel = labelRepository.findByLabelTitle(requestDto.getLabelTitle());

        // todo저장
        TodoDto todoDto = new TodoDto();

        todoDto.setContent(requestDto.getContent());
        todoDto.setCreatedAt(requestDto.getCreatedAt());
        todoDto.setStatus(false);
        todoDto.setImportance(false);
        todoDto.setLabel(LabelMapper.convertToDto(resLabel.get()));
        todoDto.setProgress(ProgressMapper.convertToDto(resProgress.get()));

        Todo todo = TodoMapper.convertToModel(todoDto);
        this.todoRepository.save(todo);
        return TodoMapper.convertToDto(todo);
    }
}
