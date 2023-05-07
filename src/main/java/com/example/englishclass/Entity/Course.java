package com.example.englishclass.Entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@RequiredArgsConstructor
@Entity
@Table(name="course")
@Getter
@Setter
public class Course {

    @Id
    @GeneratedValue
    @Column
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String dateStart;

    @Column
    private String dateOver;

    @Column
    private String price;

    @Column
    private String userEmail;
}
