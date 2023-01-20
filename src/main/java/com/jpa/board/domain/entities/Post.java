package com.jpa.board.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Table(name = "posts")
@Entity
@SequenceGenerator(
        name = "post_seq_generator",
        sequenceName = "post_seq",
        initialValue = 1, allocationSize = 50)
public class Post {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "post_seq_generator")
    @Column(name = "post_id", updatable = false)
    private Long id;

    @Column(nullable = false, length = 30)
    private String title;

    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id", updatable = false, nullable = false)
    private User user;

    protected Post()  { }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public User getUser() {
        return user;
    }

}
