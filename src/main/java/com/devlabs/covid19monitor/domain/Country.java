package com.devlabs.covid19monitor.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Setter
@Getter
@Table(schema = "COVID19", name = "COUNTRY")
public class Country {
	
	@Id
	@NotNull
	@Size(min = 3, max = 3)
	private String isoCode;
	@NotNull
	private String fullName;
	@NotNull
	private String continent;
	
}
