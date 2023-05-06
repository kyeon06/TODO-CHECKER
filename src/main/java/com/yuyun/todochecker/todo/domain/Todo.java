package com.yuyun.todochecker.todo.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="todo", schema="todo_checker", uniqueConstraints = {@UniqueConstraint(columnNames = "todo_id")})
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_id", unique = true , nullable = false)
    private Long todoId;

    @Column(name = "content")
    private String content;

    @Column(name = "status", nullable = false)
    private Boolean status;

    @Column(name = "importance", nullable = false)
    private Boolean importance;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDate updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="label_id")
    private Label label;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "progress_id")
    private Progress progress;

}
