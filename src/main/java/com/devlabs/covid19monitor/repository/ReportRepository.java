package com.devlabs.covid19monitor.repository;

import com.devlabs.covid19monitor.domain.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface ReportRepository extends JpaRepository<Report, Long> {
	Optional<Report> findByCountryFullNameAndReportDate(String countryName, LocalDate reportDate);
}
