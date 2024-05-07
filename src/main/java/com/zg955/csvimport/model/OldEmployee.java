package com.zg955.csvimport.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "employee")
public class OldEmployee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName2;

    private String lastName2;

    private String mail2;

    public OldEmployee() {
    }

    public OldEmployee(String firstName, String lastName, String mail) {
        this.firstName2 = firstName;
        this.lastName2 = lastName;
        this.mail2 = mail;
    }
}
