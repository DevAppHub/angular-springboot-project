package com.cts.flight.modal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="location")
@Data
public class Location {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="state_code")
	private String stateCode;
	
	@Column(name="state_name")
	private String stateName;

	public Location(Long id, String stateCode, String stateName) {
		super();
		this.id = id;
		this.stateCode = stateCode;
		this.stateName = stateName;
	}
	
	public Location() {
	}

}
