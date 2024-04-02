package com.estudos.migracaodados.reader;

import com.estudos.migracaodados.entity.Person;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import java.util.Date;

@Configuration
public class PersonItemReaderConfig {

    @Bean
    public FlatFileItemReader<Person> personFlatFileItemReader() {
        return new FlatFileItemReaderBuilder<Person>()
                .name("personItemReader")
                .resource(new FileSystemResource("files/pessoas.csv"))
                .delimited()
                .names("name", "email", "birthDate", "age", "id")
                .addComment("--")
                .fieldSetMapper(fieldSetMapper())
                .build();
    }

    private FieldSetMapper<Person> fieldSetMapper() {
        return fieldSet -> Person.builder()
                .name(fieldSet.readString("name"))
                .email(fieldSet.readString("email"))
                .birthDate(new Date(fieldSet.readDate("birthDate", "yyyy-MM-dd HH:mm:ss").getTime()))
                .age(fieldSet.readInt("age"))
                .id(fieldSet.readInt("id"))
                .build();
    }
}
