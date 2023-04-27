package com.yuyun.todochecker.todo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

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

    @Column(name = "status")
    private boolean status;

    @Column(name = "importance")
    private boolean importance;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name="label_id")
    private Label label;

    @ManyToOne
    @JoinColumn(name = "progress_id")
    private Progress progress;

}
