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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.root.exceptions.BusException;
import com.root.models.Bus;
import com.root.services.BusService;

@RestController
public class BusController {

	@Autowired
	private BusService busService;
	
	@PostMapping("/bus/add")
	public ResponseEntity<Bus> addBus(@Valid @RequestBody Bus bus) throws BusException{
		
		Bus newBus=busService.addBus(bus);
		
		return new ResponseEntity<>(newBus,HttpStatus.CREATED);
	}
	
	@PutMapping("/bus/update")
	public ResponseEntity<Bus> updateBus(@Valid @RequestBody Bus bus) throws BusException{
		
		Bus newBus=busService.updateBus(bus);
		
		return new ResponseEntity<>(newBus,HttpStatus.FOUND);
	}
	
	@DeleteMapping("/bus/delete/{busId}")
	public ResponseEntity<Bus> DeleteBus(@PathVariable("busId") Integer busId) throws BusException{
		
		Bus bus=busService.deleteBus(busId);
		
		return new ResponseEntity<>(bus, HttpStatus.OK);
	}
	
	@GetMapping("/bus/get")
	public ResponseEntity<Bus> getBusesById(@RequestParam Integer busId) throws BusException{
		
		Bus bus=busService.viewBus(busId);
		
		return new ResponseEntity<>(bus,HttpStatus.OK);
	}
	
	
	@GetMapping("/buses/type/{busType}")
	public ResponseEntity<List<Bus>> getBusesByType(@PathVariable("busType") String busType) throws BusException{
		
		List<Bus> listOfBuses=busService.viewBusByType(busType);
		
		return new ResponseEntity<>(listOfBuses,HttpStatus.OK);
	}
	
	@GetMapping("/buses")
	public ResponseEntity<List<Bus>> getAllBuses() throws BusException{
		
		List<Bus> listOfBuses=busService.viewAllBuses();
		
		return new ResponseEntity<>(listOfBuses,HttpStatus.OK);
	}
}

