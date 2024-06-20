package com.root.models;

import java.util.ArrayList;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;

	@NotBlank(message = "Name connot be null or blank!")
	private String firstName;
	
	private String lastName;
	
	@NotBlank(message= "Password cannot be null or blank!")
	private String password;
	
	
	@NotBlank(message= "Mobile number cannot be null or blank!")
	@Size(min = 10, max = 10, message = "Enter valid 10 digit mobile number")
	@Pattern(regexp = "[6789]{1}[0-9]{9}", message = "Enter valid 10 digit mobile number")
	private String mobileNumber;
	
	@Email
	@Column(unique = true)
	private String email;


	@OneToMany(cascade = CascadeType.ALL,mappedBy = "user",fetch = FetchType.EAGER)
	@JsonIgnore
	private List<Reservation> reservations =new ArrayList<>();


	@OneToMany(mappedBy="user",fetch=FetchType.EAGER,cascade = CascadeType.ALL)
	private List<Authority> authorities = new ArrayList<>();

	@OneToOne(cascade = CascadeType.ALL,mappedBy = "user")
	@JsonIgnore
	private  Feedback feedback;
	
}