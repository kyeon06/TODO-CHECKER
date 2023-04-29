package com.yuyun.todochecker.todo.repository;

import com.yuyun.todochecker.todo.domain.Progress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ProgressRepository extends JpaRepository<Progress, Long> {

    Optional<Progress> findByRunDate(LocalDate runDate);
}
