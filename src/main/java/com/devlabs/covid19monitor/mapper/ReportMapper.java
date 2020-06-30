package com.devlabs.covid19monitor.mapper;

import com.devlabs.covid19monitor.domain.Report;
import com.devlabs.covid19monitor.dto.ReportRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ReportMapper {
	
	@Mapping(target = "reportDate", source = "date")
	@Mapping(target = "country.isoCode", source = "isoCode")
	@Mapping(target = "country.fullName", source = "location")
	@Mapping(target = "country.continent", source = "continent")
	Report map(ReportRecord reportRecord);
	
}
