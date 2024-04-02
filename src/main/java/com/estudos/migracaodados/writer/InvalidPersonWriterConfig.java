package com.estudos.migracaodados.writer;

import com.estudos.migracaodados.entity.Person;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class InvalidPersonWriterConfig {

    @Bean
    public FlatFileItemWriter<Person> invalidPersonWriter() {
        return new FlatFileItemWriterBuilder<Person>()
                .name("invalidPersonWriter")
                .resource(new FileSystemResource("files/pessoas_invalidas.csv"))
                .delimited()
                .names("id")
                .build();
    }
}
