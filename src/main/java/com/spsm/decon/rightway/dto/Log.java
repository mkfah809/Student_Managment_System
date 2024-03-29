package com.spsm.decon.rightway.dto;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Table
@Entity(name = "logs")
public class Log {
	private Long logId;
	private Deacon decon;
	private LocalDate date;
	private String timeIn;
	
	public String getTime() {
		return timeIn;
	}

	public void setTime(String time) {
		this.timeIn = time;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getLogId() {
		return logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	@ManyToOne
	@JoinColumn(name = "decon_id")
	public Deacon getDecon() {
		return decon;
	}

	public void setDecon(Deacon decon) {
		this.decon = decon;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

}