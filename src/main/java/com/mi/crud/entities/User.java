package com.mi.crud.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Entity
@Data
@Accessors(chain = true)
@Table(name = "users")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "^[0-9]{10}$")
    @Column(name = "passport_number", length = 10)
    private String passportNumber;
    private String name;
    private int age;
    private MaleType male;
    private String comment;

    public User(String name, String passportNumber, int age, MaleType male, String comment) {
        this.name = name;
        this.passportNumber = passportNumber;
        this.age = age;
        this.male = male;
        this.comment = comment;
    }


}