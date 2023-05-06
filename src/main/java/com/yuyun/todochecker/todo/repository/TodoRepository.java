package com.yuyun.todochecker.todo.repository;

import com.yuyun.todochecker.todo.domain.Progress;
import com.yuyun.todochecker.todo.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

    List<Todo> findByProgress(Progress progress);
}
