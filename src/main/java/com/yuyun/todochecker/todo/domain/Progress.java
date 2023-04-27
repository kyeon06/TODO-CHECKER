package com.yuyun.todochecker.todo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

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
    private Date runDate;

    @Column(name = "run_rate")
    private int runRate;
}
