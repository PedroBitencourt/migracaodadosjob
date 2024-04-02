package com.estudos.migracaodados.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class Person {

    private int id;

    private String name;

    private String email;

    private Date birthDate;

    private int age;
}
