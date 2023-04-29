package com.yuyun.todochecker.todo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="label", schema="todo_checker", uniqueConstraints = {@UniqueConstraint(columnNames = "label_id")})
public class Label {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "label_id", unique = true, nullable = false)
    private Long labelId;

    @Column(name = "title")
    private String labelTitle;

    @Column(name = "color")
    private String labelColor;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "label")
    private List<Todo> todoList;
}
