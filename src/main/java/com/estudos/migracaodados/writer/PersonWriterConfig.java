package com.estudos.migracaodados.writer;

import com.estudos.migracaodados.entity.Person;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Date;

@Configuration
public class PersonWriterConfig {

    @Bean
    public JdbcBatchItemWriter<Person> personWriter(@Qualifier("appDataSource")DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Person>()
                .dataSource(dataSource)
                .sql("INSERT INTO person (id, name, email, birthDate, age) VALUES(?, ?, ?, ?, ?)")
                .itemPreparedStatementSetter(itemPreparedStatementSetter())
                .build();
    }

    private ItemPreparedStatementSetter<Person> itemPreparedStatementSetter() {
        return (item, ps) -> {
            ps.setInt(1, item.getId());
            ps.setString(2, item.getName());
            ps.setString(3, item.getEmail());
            ps.setDate(4, new Date(item.getBirthDate().getTime()));
            ps.setInt(5, item.getAge());
        };
    }
}
