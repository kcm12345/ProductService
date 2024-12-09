package com.example.productservice.Inheritance.joinedtable;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="joinedtable_mentor" )
public class Mentor extends User {
    String company;
    double avgRating;
}
