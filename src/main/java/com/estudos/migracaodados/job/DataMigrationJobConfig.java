package com.estudos.migracaodados.job;

import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableBatchProcessing
@Configuration
@AllArgsConstructor
public class DataMigrationJobConfig {

    private JobBuilder jobBuilder;

    @Bean
    public Job dataMigrationJob(@Qualifier("personMigrationStep") Step personMigrationStep,@Qualifier("bankDataMigrationStep") Step bankDataMigrationStep) {
        return jobBuilder
                .start(personMigrationStep)
                .next(bankDataMigrationStep)
                .incrementer(new RunIdIncrementer())
                .build();
    }
}
