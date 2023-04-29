package com.yuyun.todochecker.todo.repository;

import com.yuyun.todochecker.todo.domain.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LabelRepository extends JpaRepository<Label, Long> {

    Optional<Label> findByLabelTitle(String title);
}
