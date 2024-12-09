package com.example.productservice.Inheritance.tableperclass;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="tpc_mentor" )
public class Mentor extends User {
    String company;
    double avgRating;
}
