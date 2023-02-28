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
@Table(name="address")
public class Address {
	private Long deconId;
	private Decon decon;
	private String addressLine;
	private String zipCode;
	
	@Id
	public Long getDeconId() {
		return deconId;
	}
	public void setDeconId(Long deconId) {
		this.deconId = deconId;
	}
	
	@OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE }, orphanRemoval = true)
	@MapsId
	@JoinColumn(name = "decon_id")
	public Decon getDecon() {
		return decon;
	}
	public void setDecon(Decon decon) {
		this.decon = decon;
	}

	@Column(length = 100)
	public String getAddressLine() {
		return addressLine;
	}
	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}
	@Column(length = 5)
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	
}
