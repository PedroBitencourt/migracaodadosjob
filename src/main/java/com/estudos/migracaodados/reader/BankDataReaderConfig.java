package com.estudos.migracaodados.reader;

import com.estudos.migracaodados.entity.BankData;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class BankDataReaderConfig {

    @Bean
    public FlatFileItemReader<BankData> bankDataFlatFileItemReader() {
        return new FlatFileItemReaderBuilder<BankData>()
                .name("bankDataItemReader")
                .resource(new FileSystemResource("files/dados_bancarios.csv"))
                .delimited()
                .names("personId", "agency", "account", "bank", "id")
                .addComment("--")
                .targetType(BankData.class)
                .build();
    }
}
