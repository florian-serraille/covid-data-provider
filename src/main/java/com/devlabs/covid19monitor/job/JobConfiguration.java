package com.devlabs.covid19monitor.job;

import com.devlabs.covid19monitor.domain.Report;
import com.devlabs.covid19monitor.dto.ReportRecord;
import com.devlabs.covid19monitor.dto.ReportRecordFieldMapper;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.Assert;

import javax.persistence.EntityManagerFactory;

@Configuration
public class JobConfiguration {
	
	@Bean
	public Job covid19Job(final JobBuilderFactory factory, final Step downloadStep, final Step processFileStep) {
		return factory.get("covid19Job")
		              .incrementer(new RunIdIncrementer())
		              .start(downloadStep)
		              .next(processFileStep)
		              .build();
	}
	
	@Bean
	public Step downloadStep(final StepBuilderFactory factory, final Tasklet downloadTasklet) {
		return factory.get("downloadStep").tasklet(downloadTasklet).build();
	}
	
	@Bean
	public Step processFileStep(@Value("${spring.batch.chunk-size}") final Integer chunkSize,
	                            final StepBuilderFactory factory,
	                            final ItemReader<ReportRecord> reader,
	                            final ItemProcessor<ReportRecord, Report> processor,
	                            final ItemWriter<Report> writer) {
		return factory.get("processFileStep")
				       .<ReportRecord, Report>chunk(chunkSize)
				       .reader(reader)
				       .processor(processor)
				       .writer(writer)
				       .build();
		
	}
	
	@Bean
	@JobScope
	public FlatFileItemReader<ReportRecord> reader(@Value("#{jobExecutionContext['FILE']}") String resourcePath) {
		
		final Resource resource = new PathMatchingResourcePatternResolver().getResource("file:" + resourcePath);
		Assert.isTrue(resource.exists(), "Resource should exist");
		return new FlatFileItemReaderBuilder<ReportRecord>()
				       .name("reader")
				       .resource(resource)
				       .delimited()
				       .delimiter(",")
				       .names(ReportRecord.getHeaders())
				       .fieldSetMapper(new ReportRecordFieldMapper())
				       .linesToSkip(1)
				       .build();
	}
	
	@Bean
	public ItemWriter<Report> itemWriter(final EntityManagerFactory entityManagerFactory) {
		return new JpaItemWriterBuilder<Report>()
				       .entityManagerFactory(entityManagerFactory)
				       .build();
	}
}
