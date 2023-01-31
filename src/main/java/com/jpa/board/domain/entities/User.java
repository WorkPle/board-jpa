package com.jpa.board.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.util.Assert;

@Entity
@Table(name = "users")
@SequenceGenerator(
        name = "user_seq_generator",
        sequenceName = "user_seq")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "user_seq_generator")
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @ColumnDefault("'없음'")
    private String hobby;

    @Column(nullable = false, length = 3)
    private Integer age;

    protected User(){ }

    public User(Long id, String name, String hobby, int age) {
        validateName(name);
        validateAge(age);

        this.id = id;
        this.name = name;
        this.hobby = hobby;
        this.age = age;
    }

    public User(String name, String hobby, int age) {
        this(null, name, hobby, age);
    }

    public User(String name, int age) {
        this(name, null, age);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getHobby() {
        return hobby;
    }

    public int getAge() {
        return age;
    }

    private void validateName(String name) {
        Assert.hasText(name, "이름은 적어도 한 글자 이상이어야 합니다.");
    }

    private void validateAge(int age) {
        Assert.isTrue(age > 0, "나이는 적어도 1살 이상이어야 합니다.");
    }
}
