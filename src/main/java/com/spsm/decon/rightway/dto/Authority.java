package com.spsm.decon.rightway.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
@Entity
@Table(name = "authorities")
public class Authority implements GrantedAuthority {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String authority;
	private Deacon deacon;
	
	@JoinColumn(name = "deacon_Id")
	@Override
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@ManyToOne
	@JoinColumn(name = "deacon_id")
	public Deacon getDeacon() {
		return deacon;
	}

	public void setDeacon(Deacon deacon) {
		this.deacon = deacon;
	}


	

}
