package com.zg955.springbatch.configuration;

import com.zg955.CustomProperties;
import com.zg955.springbatch.model.Employee;
import com.zg955.springbatch.model.EmployeeEntity;
import com.zg955.springbatch.processor.EmployeeProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.TaskExecutorJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Slf4j
public class SpringBatchConfig {

    @Autowired
    CustomProperties customProperties;

    @Bean(name = "dataSource")
    public DataSource getDataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:h2:file:/data/demo")
                .username("sa")
                .password("password")
                .build();
    }

    @Bean(name = "transactionManager")
    public DataSourceTransactionManager getTransactionManager() {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(getDataSource());
        transactionManager.afterPropertiesSet();
        return transactionManager;
    }

    @Bean(name = "jobRepository")
    public JobRepository getJobRepository() throws Exception {
        JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
        factory.setDataSource(getDataSource());
        factory.setTransactionManager(getTransactionManager());
        factory.afterPropertiesSet();
        return factory.getObject();
    }

    @Bean(name = "jobLauncher")
    public JobLauncher getJobLauncher() throws Exception {
        TaskExecutorJobLauncher jobLauncher = new TaskExecutorJobLauncher();
        jobLauncher.setJobRepository(getJobRepository());
        jobLauncher.afterPropertiesSet();
        return jobLauncher;
    }

    @Bean
    public Job getJob(JobRepository jobRepository, Step step) {
        return new JobBuilder("jobEmployee", jobRepository)
                .start(step) // Can have more step
                .build();
    }

    @Bean
    @Qualifier("step")
    public Step getStep(JobRepository jobRepository, DataSourceTransactionManager transactionManager) {
        return new StepBuilder("stepEmployee1", jobRepository)
                .<Employee, EmployeeEntity>chunk(customProperties.getChunkSize(), transactionManager)
                .reader(reader())
                .processor(processor()) //FIXME issue in the employee bean definition ?
                .writer(writer(transactionManager))
                .build();
    }

    @Bean
    @StepScope
    public ItemReader<Employee> reader() { //TODO replace with FlatFileItemReader
        return new FlatFileItemReaderBuilder<Employee>()
                .name("employeeReader")
                .resource(new ClassPathResource("data.csv")) //FIXME check if path is accurate
                .delimited()
                .delimiter(customProperties.getDelimiter())
                .names("firstName", "lastName", "email")
                .targetType(Employee.class)
                .build();
    }

    @Bean
    @StepScope
    public ItemProcessor<Employee, EmployeeEntity> processor() {
        return new EmployeeProcessor();
    }

    @Bean
    @StepScope
    public ItemWriter<EmployeeEntity> writer(DataSourceTransactionManager transactionManager) {
        JdbcBatchItemWriter<EmployeeEntity> itemWriter = new JdbcBatchItemWriter<>();
        itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        itemWriter.setSql("INSERT INTO table (first_name, last_name, mail) VALUES (:firstName, :lastName, :mail)"); //FIXME check query
        itemWriter.setDataSource(transactionManager.getDataSource());
        itemWriter.afterPropertiesSet();
        return itemWriter;
    }
}
