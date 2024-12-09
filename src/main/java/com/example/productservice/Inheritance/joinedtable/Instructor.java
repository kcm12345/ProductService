package com.example.productservice.Inheritance.joinedtable;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "joinedtable_instructor")
public class Instructor extends User {
    String specialisation;
}
