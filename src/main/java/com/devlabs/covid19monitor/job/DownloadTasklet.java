package com.devlabs.covid19monitor.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Slf4j
@Component
public class DownloadTasklet implements Tasklet {
	
	private final URI owidCovidUri;
	private final RestTemplate restTemplate;
	
	public DownloadTasklet(@Value("${owid-covid-uri}") final URI owidCovidUri) {
		this.owidCovidUri = owidCovidUri;
		this.restTemplate = new RestTemplateBuilder().build();
	}
	
	@Override
	public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) {
		
		try {
			
			final Path file = buildPathFile();
			logDownload(file);
			final ResponseEntity<byte[]> responseEntity = downloadFile();
			
			Assert.isTrue(Objects.nonNull(responseEntity.getBody()),
			              () -> "Response body should not be null for get to URI: " + owidCovidUri);
			
			writeFile(file, responseEntity);
			promoteFileToJobExecutionContext(contribution, file);
			
		} catch (RestClientException | IllegalStateException | IOException exception) {
			
			log.error("Fail to download file due to: " + exception.getMessage());
			contribution.setExitStatus(ExitStatus.FAILED);
		}
		
		return RepeatStatus.FINISHED;
	}
	
	private void promoteFileToJobExecutionContext(final StepContribution contribution, final Path file) {
		contribution.getStepExecution().getJobExecution().getExecutionContext().put("FILE", file.toString());
	}
	
	private void writeFile(final Path file, final ResponseEntity<byte[]> responseEntity) throws IOException {
		Files.write(file, responseEntity.getBody());
	}
	
	private ResponseEntity<byte[]> downloadFile() {
		return restTemplate.getForEntity(owidCovidUri, byte[].class);
	}
	
	private void logDownload(final Path file) {
		log.info("Start download resource from: " + owidCovidUri.toString() + ", at: " + file.toString());
	}
	
	private Path buildPathFile() throws IOException {
		
		final Path dirFile = Files.createTempDirectory("covid");
		return dirFile.resolve(Paths.get(LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE)));
	}
	
}
