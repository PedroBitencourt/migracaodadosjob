package com.estudos.migracaodados.job;

import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class DataMigrationJobConfig {


    private final JobRepository jobRepository;

    @Bean
    public Job dataMigrationJob(@Qualifier("personMigrationStep") Step personMigrationStep, @Qualifier("bankDataMigrationStep") Step bankDataMigrationStep) {
        return new JobBuilder("dataMigrationJob", jobRepository)
                .start(personMigrationStep)
                .next(bankDataMigrationStep)
                .incrementer(new RunIdIncrementer())
                .build();
    }
}
