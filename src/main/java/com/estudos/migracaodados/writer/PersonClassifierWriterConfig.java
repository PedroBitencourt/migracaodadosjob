package com.estudos.migracaodados.writer;

import com.estudos.migracaodados.entity.Person;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.batch.item.support.builder.ClassifierCompositeItemWriterBuilder;
import org.springframework.classify.Classifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonClassifierWriterConfig {

    @Bean
    public ClassifierCompositeItemWriter<Person> personClassifierWriter(JdbcBatchItemWriter<Person> personWriter, FlatFileItemWriter<Person> invalidPersonWriter) {
        return new ClassifierCompositeItemWriterBuilder<Person>()
                .classifier(classifier(personWriter, invalidPersonWriter))
                .build();
    }

    private Classifier<Person, ItemWriter<? super Person>> classifier(JdbcBatchItemWriter<Person> personWriter, FlatFileItemWriter<Person> invalidPersonWriter) {
        return person -> {
            if(person.isValid()) return personWriter;
            return invalidPersonWriter;
        };
    }
}
