package com.root.models;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Bus {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer busId;

	@NotBlank(message = "Bus name cannot be null or blank!")
	private String busName;

	@NotBlank(message = "Bus driver name cannot be null or blank!")
	private String driverName;

	private String busType;

	@NotBlank(message = "Bus registeration number cannot be null or blank!")
	private String busRegNumber;
	
	@NotBlank(message = "Start point cannot be blank!")
	private String routeFrom;

	@NotBlank(message = "Destination point cannot be null or blank!")
	private String routeTo;

	@NotNull(message = "Arrival time cannot be null")
	private LocalTime arrivalTime;

	@NotNull(message = "Departure time cannot be null")
	private LocalTime departureTime;

	@NotNull(message = "Total Seats cannot be null!")
	private Integer seats;

	@NotNull(message = "Available seats cannot be null!")
	private Integer availableSeats;

	@NotNull(message = "Fare cannot be null!")
	private Integer farePerSeat;

	@NotNull(message = "Bus journey date cannot be null!")
//	@JsonFormat(pattern="dd-MM-yyyy")
	private LocalDate busJourneyDate;
	
	@ManyToOne
	@JoinColumn(name = "route_id")
	private Route route;
	
	
}
