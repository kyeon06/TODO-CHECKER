package com.yuyun.todochecker.todo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="progress", schema="todo_checker", uniqueConstraints = {@UniqueConstraint(columnNames = "progress_id")})
public class Progress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "progress_id", unique = true, nullable = false)
    private Long progressId;

    @Column(name = "run_date")
    private LocalDate runDate;

    @Column(name = "run_rate")
    private Long runRate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "progress", cascade = CascadeType.ALL)
    private List<Todo> todoList;
}
