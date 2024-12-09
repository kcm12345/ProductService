package com.example.productservice.Inheritance.MappedSuperClass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "mps_user")
public class Student extends User {
    String course;
    String batch;
}
