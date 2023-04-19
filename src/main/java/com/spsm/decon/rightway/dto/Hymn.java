package com.spsm.decon.rightway.dto;

import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import java.util.List;

@Entity
@Table(name = "hymns")
public class Hymn {
	private Long hymnId;
	private String name;
	private String whenToSay;
	private String whoSay;
	private String description;
	private List<Deacon> decons = new ArrayList<>();

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "hymns", cascade = { CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REMOVE })
	public List<Deacon> getDecons() {
		return decons;
	}

	public void setDecons(List<Deacon> decons) {
		this.decons = decons;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getHymnId() {
		return hymnId;
	}

	public void setHymnId(Long hymnId) {
		this.hymnId = hymnId;
	}

	@Column(length = 50)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(length = 50)
	public String getWhenToSay() {
		return whenToSay;
	}

	public void setWhenToSay(String whenToSay) {
		this.whenToSay = whenToSay;
	}

	@Column(length = 50)
	public String getWhoSay() {
		return whoSay;
	}

	public void setWhoSay(String whoSay) {
		this.whoSay = whoSay;
	}

	@Column(length = 255)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
