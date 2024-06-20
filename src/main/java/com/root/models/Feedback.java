package com.root.models;

import java.time.LocalDateTime;


import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Feedback {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer feedbackId;

	private Integer driverRating;

	private Integer serviceRating;

	@Min(value=1, message="Rating must be in range of 1-5")
	@Max(value=5, message="Rating must be in range of 1-5")
	private Integer overallRating;
	
	private String comments;

	private LocalDateTime feedbackDateTime;

//	@OneToOne (cascade = CascadeType.ALL)
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
	
//	@ManyToOne(cascade = CascadeType.ALL)
	@ManyToOne
	@JoinColumn(name = "bus_id")
	private Bus bus;

	
}
