package com.devlabs.covid19monitor.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name = "REPORT")
public class Report {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private LocalDate reportDate;
	@Valid
	@NotNull
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "COUNTRY_ISO_CODE")
	private Country country;
	@NotNull
	private Long totalCases;
	@NotNull
	private Long newCases;
	@NotNull
	private Long totalDeaths;
	@NotNull
	private Long newDeaths;
	@NotNull
	private Double totalCasesPerMillion;
	@NotNull
	private Double newCasesPerMillion;
	@NotNull
	private Double totalDeathsPerMillion;
	@NotNull
	private Double newDeathsPerMillion;
	private Long totalTests;
	private Long newTests;
	private Long newTestsSmoothed;
	private Double totalTestsPerThousand;
	private Double newTestsPerThousand;
	private Double newTestsSmoothedPerThousand;
	private String testsUnits;
	private Integer stringencyIndex;
	private Long population;
	private Double populationDensity;
	private Double medianAge;
	@Column(name = "aged_65_older")
	private Double aged65Older;
	@Column(name = "aged_70_older")
	private Double aged70Older;
	private Double gpdPerCapita;
	private Double extremePoverty;
	private Double cvdDeathRate;
	private Double diabetesPrevalence;
	private Double femaleSmokers;
	private Double maleSmokers;
	private Double handWashingFacilites;
	private Double hospitalBedsPerThousand;
	private Double lifeExpectancy;
}
