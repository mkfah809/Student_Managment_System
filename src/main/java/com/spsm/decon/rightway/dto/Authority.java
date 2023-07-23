//package com.spsm.decon.rightway.dto;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//
//import org.springframework.security.core.GrantedAuthority;
//@Entity
//@Table(name = "authorities")
//public class Authority implements GrantedAuthority {
//
//	private static final long serialVersionUID = 1L;
//
//	private Long id;
//	private String authority;
//	private User user;
//	
//	@JoinColumn(name = "user_Id")
//	@Override
//	public String getAuthority() {
//		return authority;
//	}
//	public void setAuthority(String authority) {
//		this.authority = authority;
//	}
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//	@ManyToOne
//	@JoinColumn(name = "user_id")
//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}
//
//
//	
//
//}
