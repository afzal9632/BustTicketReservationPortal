package com.root.controllers;

import java.util.List;

import jakarta.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.root.DTO.ReservationDTO;
import com.root.exceptions.BusException;
import com.root.exceptions.ReservationException;
import com.root.exceptions.UserException;
import com.root.models.Reservation;
import com.root.services.ReservationService;

@RestController
public class ReservationController {
	
	
	@Autowired
	private ReservationService reservationService;
	
	@PostMapping("/reservation/user/{busId}")
	public ResponseEntity<Reservation> addReservation(@Valid @RequestBody ReservationDTO reservationDTO,
													  @PathVariable Integer busId ) throws ReservationException, BusException, UserException{

		Reservation savedReservation =reservationService.addReservation(reservationDTO,busId);
		return new ResponseEntity<>(savedReservation,HttpStatus.ACCEPTED);
		
	}
	
	
	@DeleteMapping("/reservation/user/{reservationId}")
	public ResponseEntity<Reservation> deleteReservation(@PathVariable Integer reservationId ) throws ReservationException, BusException, UserException{
		Reservation deletedReservation = reservationService.deleteReservation(reservationId
		);
		return new ResponseEntity<>(deletedReservation,HttpStatus.OK);
	}
	
	
	@GetMapping("/reservation/admin/{reservationId}")
	public ResponseEntity<Reservation> viewReservation(@PathVariable Integer reservationId) throws ReservationException{
		Reservation foundReservation = reservationService.viewReservation(reservationId
		);
		return new ResponseEntity<>(foundReservation,HttpStatus.OK);
	}
	
	
	@GetMapping("/reservation/admin")
	public ResponseEntity<List<Reservation>> viewAllReservation() throws ReservationException{
		List<Reservation> reservationList = reservationService.viewAllReservation();
		return new ResponseEntity<>(reservationList,HttpStatus.OK);
	}
	
	
//	@GetMapping("/reservation/user")
//	public ResponseEntity<List<Reservation>> viewReservationByUser(@RequestParam(required = false) String key) throws ReservationException, UserException{
//
//		List<Reservation> reservationList = reservationService.viewReservationByUser(key);
//		return new ResponseEntity<List<Reservation>>(reservationList,HttpStatus.OK);
//	}
	
}
