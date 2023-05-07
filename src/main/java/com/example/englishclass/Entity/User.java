package com.example.englishclass.Entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@RequiredArgsConstructor
@Entity
@Table(name="_user")
@Getter
@Setter
public class User {



    @Column
    private String firstname;

    @Column
    private String lastname;

    @Id
    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String phone;

    @Column
    private String status;

    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL) //FetchType.EAGER - роли будут загружаться вместе с пользователем
    @JoinTable(
            name = "_user_roles",
            joinColumns = @JoinColumn(name = "_user_email"),
            inverseJoinColumns = @JoinColumn(name = "role_name")
    )
    private Set<Role> roles = new HashSet<>();






    public User(String firstname, String lastname, String email, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;

    }




}
