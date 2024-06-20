package com.root.DTO;


import java.time.LocalDate;

import jakarta.validation.constraints.*;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDTO {
	
	@NotBlank(message = "Start point cannot be blank!")
	private String source;
	
	@NotBlank(message = "Destination point cannot be blank!")
	private String destination;
	
	@NotNull(message = "Seats to be booked can't be null")
	private Integer noOfSeatsToBook;
	
	@NotNull
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate journeyDate;

}
