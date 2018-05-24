package org.groupevents.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name = "address")
public class Address extends BaseEntity {
	
	private String street;
	private String zipCode;
	private String city;
	private String country;
	private BigDecimal longitude;
	private BigDecimal latitude;

}
