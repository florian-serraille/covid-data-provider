package com.devlabs.covid19monitor.dto;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class ReportRecordFieldMapper implements FieldSetMapper<ReportRecord> {
	
	@Override
	public ReportRecord mapFieldSet(final FieldSet fieldSet) {
		return ReportRecord.builder()
		                   .isoCode(fieldSet.readString(0))
		                   .continent(fieldSet.readString(1))
		                   .location(fieldSet.readString(2))
		                   .date(LocalDate.parse(fieldSet.readString(3), DateTimeFormatter.ISO_DATE))
		                   .totalCases(readLong(fieldSet, 4))
		                   .newCases(readLong(fieldSet, 5))
		                   .totalDeaths(readLong(fieldSet, 6))
		                   .newDeaths(readLong(fieldSet, 7))
		                   .totalCasesPerMillion(readDouble(fieldSet, 8))
		                   .newCasesPerMillion(readDouble(fieldSet, 9))
		                   .totalDeathsPerMillion(readDouble(fieldSet, 10))
		                   .newDeathsPerMillion(readDouble(fieldSet, 11))
		                   .totalTests(readLong(fieldSet, 12))
		                   .newTests(readLong(fieldSet, 13))
		                   .newTestsSmoothed(readLong(fieldSet, 14))
		                   .totalTestsPerThousand(readDouble(fieldSet, 15))
		                   .newTestsPerThousand(readDouble(fieldSet, 16))
		                   .newTestsSmoothedPerThousand(readDouble(fieldSet, 17))
		                   .testsUnits(fieldSet.readString(18))
		                   .stringencyIndex(readInteger(fieldSet, 19))
		                   .population(readLong(fieldSet, 20))
		                   .populationDensity(readDouble(fieldSet, 21))
		                   .medianAge(readDouble(fieldSet, 22))
		                   .aged65Older(readDouble(fieldSet, 23))
		                   .aged70Older(readDouble(fieldSet, 24))
		                   .gpdPerCapita(readDouble(fieldSet, 25))
		                   .extremePoverty(readDouble(fieldSet, 26))
		                   .cvdDeathRate(readDouble(fieldSet, 27))
		                   .diabetesPrevalence(readDouble(fieldSet, 28))
		                   .femaleSmokers(readDouble(fieldSet, 29))
		                   .maleSmokers(readDouble(fieldSet, 30))
		                   .handwashingFacilites(readDouble(fieldSet, 31))
		                   .hospitalBedsPerThousand(readDouble(fieldSet, 32))
		                   .lifeExpectancy(readDouble(fieldSet, 33))
		                   .build();
		
	}
	
	private String readString(FieldSet fieldSet, int index) {
		return fieldSet.readString(index);
	}
	
	private Double readDouble(FieldSet fieldSet, int index) {
		final String rawValue = readString(fieldSet, index);
		return Objects.isNull(rawValue) || rawValue.isEmpty() ? null : Double.parseDouble(rawValue);
	}
	
	private Long readLong(FieldSet fieldSet, int index) {
		final String rawValue = readString(fieldSet, index);
		return Objects.isNull(rawValue) || rawValue.isEmpty() ? null : Long.parseLong(rawValue.split("\\.")[0]);
	}
	
	private Integer readInteger(FieldSet fieldSet, int index) {
		final String rawValue = readString(fieldSet, index);
		return Objects.isNull(rawValue) || rawValue.isEmpty() ? null : Integer.parseInt(rawValue.split("\\.")[0]);
	}
}
