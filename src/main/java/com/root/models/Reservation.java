package com.root.models;

import java.time.LocalDate;
import java.time.LocalTime;




import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer reservationId;
	private String reservationStatus;

	private LocalDate reservationDate;
	private LocalTime reservationTime;
	private String source;
	private String destination;
	private LocalDate journeyDate;
	private Integer noOfSeatsBooked;
	private Integer fare;

//	@ManyToOne(cascade = CascadeType.ALL)
	@ManyToOne
	@JoinColumn(name = "bus_id")
	private Bus bus;

//	@ManyToOne(cascade = CascadeType.ALL)
    @ManyToOne
    @JoinColumn(name = "user_id")
	private User user;


}
