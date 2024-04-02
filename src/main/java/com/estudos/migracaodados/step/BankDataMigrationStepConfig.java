package com.estudos.migracaodados.step;

import com.estudos.migracaodados.entity.BankData;
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
public class BankDataMigrationStepConfig {

    private final JobRepository jobRepository;

    private final PlatformTransactionManager transactionManager;

    @Bean
    public Step bankDataMigrationStep(ItemReader<BankData> bankDataItemReader, ItemWriter<BankData> bankDataItemWriter) {
        return new StepBuilder("bankDataMigrationStep", jobRepository).
                <BankData, BankData>chunk(1, transactionManager)
                .reader(bankDataItemReader)
                .writer(bankDataItemWriter)
                .build();
    }
}
