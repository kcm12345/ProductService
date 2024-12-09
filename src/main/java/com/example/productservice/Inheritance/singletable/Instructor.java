package com.example.productservice.Inheritance.singletable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "singletable_instructor")
@DiscriminatorValue(value = "3")
public class Instructor extends User {
    String specialisation;
}
