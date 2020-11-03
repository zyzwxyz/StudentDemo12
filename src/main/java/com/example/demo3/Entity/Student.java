package com.example.demo3.Entity;


import lombok.Getter;
import lombok.Setter;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Student {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name",nullable = true,length = 20)
    private String name;

    @Column(name = "grade",nullable = true,length = 4)
    private String grade;

}
