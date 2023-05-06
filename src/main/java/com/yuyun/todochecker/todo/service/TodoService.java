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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
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

    // 일별 목록 조회
    public ProgressDto getDailyTodoList(String runDate){
        SimpleDateFormat beforeFormat = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat afterFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date tempDate = null;

        try {
            tempDate = beforeFormat.parse(runDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        String reqDate = afterFormat.format(tempDate);

        ProgressDto progressDto = ProgressMapper.convertToDto(progressRepository.findByRunDate(LocalDate.parse(reqDate)).get());

        progressDto.setTodoList(TodoMapper.convertToDtoList(todoRepository.findByProgress(ProgressMapper.convertToModel(progressDto))));

        return progressDto;
    }

    // 목록 삭제
    public String deleteTodo(long todoId) {
        this.todoRepository.deleteById(todoId);
        return "{\"message\" : \"삭제가 완료되었습니다.\"}";
    }

    // 목록 내용 변경 및 상태 변경
    public TodoDto updateToDo(long todoId, TodoDto todoDto) {
        Optional<Todo> todo = this.todoRepository.findById(todoId);
        if (todo.isEmpty()) {
            throw new NoSuchElementException(String.format("Todo ID '%d'가 존재하지 않습니다.", todoId));
        }

        Todo updateTodo = todo.get();

        // 변경 내용이 content일 경우
        if (todoDto.getContent() != null) {
            updateTodo.setContent(todoDto.getContent());
        }

        // 변경 내용이 상태일 경우
        if (todoDto.getStatus() != null) {
            updateTodo.setStatus(todoDto.getStatus());
        }

        // 변경 내용이 우선순위일 경우
        if (todoDto.getImportance() != null) {
            updateTodo.setImportance(todoDto.getImportance());
        }

        // 변경 내용 저장
        Todo savedTodo = this.todoRepository.save(updateTodo);

        return TodoMapper.convertToDto(savedTodo);
    }
}
