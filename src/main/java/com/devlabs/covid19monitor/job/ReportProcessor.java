package com.devlabs.covid19monitor.job;

import com.devlabs.covid19monitor.domain.Report;
import com.devlabs.covid19monitor.dto.ReportRecord;
import com.devlabs.covid19monitor.mapper.ReportMapper;
import com.devlabs.covid19monitor.repository.ReportRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.validator.BeanValidatingItemProcessor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;
import java.util.Optional;

@Component
public class ReportProcessor implements ItemProcessor<ReportRecord, Report> {
	
	private static final Report SUPPRESS_RECORD = null;
	
	private final ReportRepository reportRepository;
	private final ReportMapper reportMapper;
	private final BeanValidatingItemProcessor<Report> validatorProcessor;
	
	public ReportProcessor(final ReportRepository reportRepository,
	                       final ReportMapper reportMapper) {
		this.reportRepository = reportRepository;
		this.reportMapper = reportMapper;
		this.validatorProcessor = new BeanValidatingItemProcessor<>();
	}
	
	@PostConstruct
	public void init() throws Exception {
		
		validatorProcessor.setFilter(true);
		validatorProcessor.afterPropertiesSet();
	}
	
	@Override
	public Report process(final ReportRecord record) {
		
		final Report report = map(record);
		final Report validatedReport = validate(report);
		return filterExistingReport(validatedReport);
		
	}
	
	private Report filterExistingReport(final Report validatedReport) {
		
		if (Objects.nonNull(validatedReport)) {
			
			final Optional<Report> persistedReport = reportRepository.findByCountryFullNameAndReportDate(
					validatedReport.getCountry().getFullName(),
					validatedReport.getReportDate());
			
			return persistedReport.isPresent() ? SUPPRESS_RECORD : validatedReport;
		}
		
		return SUPPRESS_RECORD;
	}
	
	private Report validate(final Report report) {
		return validatorProcessor.process(report);
	}
	
	private Report map(final ReportRecord record) {
		return reportMapper.map(record);
	}
}
