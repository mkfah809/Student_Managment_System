package com.spsm.decon.rightway.dto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "address")
public class Address {
	private Long deaconId;
	private Deacon deacon;
	private String addressLine;
	private String city;
	private String state;
	private String zipCode;

	@Id
	public Long getDeaconId() {
		return deaconId;
	}

	public void setDeaconId(Long deaconId) {
		this.deaconId = deaconId;
	}

	@OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE }, orphanRemoval = true)
	@MapsId
	@JoinColumn(name = "deacon_id")
	public Deacon getDeacon() {
		return deacon;
	}

	public void setDeacon(Deacon deacon) {
		this.deacon = deacon;
	}

	@Column(length = 100)
	public String getAddressLine() {
		return addressLine;
	}

	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}

	@Column(length = 30)
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(length = 5)
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(length = 5)
	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

}
