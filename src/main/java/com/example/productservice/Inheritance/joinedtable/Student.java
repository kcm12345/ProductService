package com.example.productservice.Inheritance.joinedtable;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "joinedtable_student")
public class Student extends User {
    String course;
    String batch;
}
