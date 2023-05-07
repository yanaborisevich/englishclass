package com.example.englishclass.Entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "roles")
public class Role {



    @Id
    @Column
    private String name;



    public Role() {

    }





    public Role(String name) {
        this.name = name;
    }





    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Role other = (Role) obj;
        return Objects.equals(name, other.name);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
