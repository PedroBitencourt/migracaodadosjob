package com.estudos.migracaodados.step;

import com.estudos.migracaodados.entity.Person;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@AllArgsConstructor
public class PersonMigrationStepConfig {

    private final JobRepository jobRepository;

    private final PlatformTransactionManager transactionManager;

    @Bean
    public Step personMigrationStep(ItemReader<Person> personItemReader, ItemWriter<Person> personItemWriter) {
        return new StepBuilder("personMigrationStep", jobRepository).
                <Person, Person>chunk(1, transactionManager)
                .reader(personItemReader)
                .writer(personItemWriter)
                .build();
    }
}
