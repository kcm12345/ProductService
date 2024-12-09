package com.example.productservice.Inheritance.joinedtable;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="joinedtable_user")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    Long id;
    String name;
    String email;
    String password;
}
