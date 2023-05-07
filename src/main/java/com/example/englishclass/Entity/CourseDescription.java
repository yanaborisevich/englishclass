package com.example.englishclass.Entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@RequiredArgsConstructor
@Entity
@Table(name="CourseDescription")
@Getter
@Setter
public class CourseDescription {
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

    @Column String value;



}
