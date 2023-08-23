package com.spsm.decon.rightway.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "users")
public class User {
	private Long userId;
	private String firstName;
	private String lastName;
	private LocalDate dateOfBirth;
	private Address address;
	private String email;
	private String password;
	private String consecration;
	private List<Hymn> hymns = new ArrayList<>();
//	private Set<Authority> authorities = new HashSet<>();

	User() {};	// instead of @NoArgsConsturctors
	
	@Column(length = 15)
	public String getConsecration() {
		return consecration;
	}

	public User(Long userId, String firstName, String lastName, String email) {
	
	this.userId = userId;
	this.firstName = firstName;
	this.lastName = lastName;
	this.email = email;
}

	public void setConsecration(String consecration) {
		this.consecration = consecration;
	}

	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

//	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE,
//			CascadeType.REMOVE }, mappedBy = "user")
//	public Set<Authority> getAuthorities() {
//		return authorities;
//	}
//
//	public void setAuthorities(Set<Authority> authorities) {
//		this.authorities = authorities;
//	}

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinTable(name = "user_has_hymns", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "hymn_id"))
	public List<Hymn> getHymns() {
		return hymns;
	}

	public void setHymns(List<Hymn> hymns) {
		this.hymns = hymns;
	}

	@Column(length = 55)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@OneToOne(mappedBy = "user", cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REMOVE }, orphanRemoval = true)
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
