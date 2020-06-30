package com.devlabs.covid19monitor.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Builder(access = AccessLevel.PACKAGE)
@ToString
@Getter
public class ReportRecord {
	
	private final String isoCode;
	private final String continent;
	private final String location;
	private final LocalDate date;
	private final Long totalCases;
	private final Long newCases;
	private final Long totalDeaths;
	private final Long newDeaths;
	private final Double totalCasesPerMillion;
	private final Double newCasesPerMillion;
	private final Double totalDeathsPerMillion;
	private final Double newDeathsPerMillion;
	private final Long totalTests;
	private final Long newTests;
	private final Long newTestsSmoothed;
	private final Double totalTestsPerThousand;
	private final Double newTestsPerThousand;
	private final Double newTestsSmoothedPerThousand;
	private final String testsUnits;
	private final Integer stringencyIndex;
	private final Long population;
	private final Double populationDensity;
	private final Double medianAge;
	private final Double aged65Older;
	private final Double aged70Older;
	private final Double gpdPerCapita;
	private final Double extremePoverty;
	private final Double cvdDeathRate;
	private final Double diabetesPrevalence;
	private final Double femaleSmokers;
	private final Double maleSmokers;
	private final Double handwashingFacilites;
	private final Double hospitalBedsPerThousand;
	private final Double lifeExpectancy;
	
	public static String[] getHeaders() {
		
		return new String[] {
				"iso_code",
				"continent",
				"location",
				"date",
				"total_cases",
				"new_cases",
				"total_deaths",
				"new_deaths",
				"total_cases_per_million",
				"new_cases_per_million",
				"total_deaths_per_million",
				"new_deaths_per_million",
				"total_tests",
				"new_tests",
				"total_tests_per_thousand",
				"new_tests_per_thousand",
				"new_tests_smoothed",
				"new_tests_smoothed_per_thousand",
				"tests_units",
				"stringency_index",
				"population",
				"population_density",
				"median_age",
				"aged_65_older",
				"aged_70_older",
				"gdp_per_capita",
				"extreme_poverty",
				"cvd_death_rate",
				"diabetes_prevalence",
				"female_smokers",
				"male_smokers",
				"handwashing_facilities",
				"hospital_beds_per_thousand",
				"life_expectancy"
		};
	}
}
