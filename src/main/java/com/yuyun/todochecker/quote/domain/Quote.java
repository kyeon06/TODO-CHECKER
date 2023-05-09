package com.yuyun.todochecker.quote.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="quote", schema="todo_checker", uniqueConstraints = {@UniqueConstraint(columnNames = "quote_id")})
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quote_id", unique = true, nullable = false)
    private Long quoteId;

    @Column
    private String content;

    @Column
    private String author;

}
