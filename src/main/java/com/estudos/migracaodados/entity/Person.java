package com.estudos.migracaodados.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

import static org.apache.logging.log4j.util.Strings.isNotBlank;

@Getter
@Setter
@Builder
public class Person {

    private int id;

    private String name;

    private String email;

    private Date birthDate;

    private int age;

    public boolean isValid() {
        return isNotBlank(name) && isNotBlank(email) && birthDate != null;
    }
}
