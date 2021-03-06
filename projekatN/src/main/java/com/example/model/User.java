package com.example.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class User {
	@Id
	@GeneratedValue
	private Long id;
	private String username;
	private String password;
	private String email;
	@OneToOne
	private Address address;
	private String phoneNo;

	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	private Set<UserAuthority> userAuthorities = new HashSet<>();

	public User() {

	}

	public User(Long id, String username, String email, Address address, String phoneNo) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.address = address;
		this.phoneNo = phoneNo;
	}

	public User(String username, String password, String email, Address address, String phoneNo) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.address = address;
		this.phoneNo = phoneNo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public Set<UserAuthority> getUserAuthorities() {
		return userAuthorities;
	}

	public void setUserAuthorities(Set<UserAuthority> userAuthorities) {
		this.userAuthorities = userAuthorities;
	}
	
	public boolean hasAuthority(String authority){
		for (UserAuthority userAuthority : userAuthorities) {
			if (userAuthority.getAuthority().getName().equals(authority)){
				return true;
			}
		}
		return false;
	}

}
