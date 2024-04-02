package com.estudos.migracaodados.job;

import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

@Configuration
@AllArgsConstructor
public class DataMigrationJobConfig {


    private final JobRepository jobRepository;

    @Bean
    public Job dataMigrationJob(@Qualifier("personMigrationStep") Step personMigrationStep, @Qualifier("bankDataMigrationStep") Step bankDataMigrationStep) {
        return new JobBuilder("dataMigrationJob", jobRepository)
                .start(startSteps(personMigrationStep, bankDataMigrationStep))
                .end()
                .incrementer(new RunIdIncrementer())
                .build();
    }

    private Flow startSteps(Step personMigrationStep, Step bankDataMigrationStep) {
        Flow bankDataMigrationFlow = new FlowBuilder<Flow>("bankDataMigrationFlow")
                .start(bankDataMigrationStep)
                .build();

        return new FlowBuilder<Flow>("parallelStepsFlow")
                .start(personMigrationStep)
                .split(new SimpleAsyncTaskExecutor())
                .add(bankDataMigrationFlow)
                .build();
    }
}
