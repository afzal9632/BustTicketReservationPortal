package com.root.services;

import java.util.List;

import com.root.exceptions.BusException;
import com.root.models.Bus;

public interface BusService {

	public Bus addBus(Bus bus) throws BusException;
	
	public Bus updateBus(Bus bus) throws BusException;
	
	public Bus deleteBus(Integer busId) throws BusException;
	
	public Bus viewBus(Integer busId) throws BusException;
	
	public List<Bus> viewBusByType(String BusType) throws BusException;
	
	public List<Bus> viewAllBuses() throws BusException;
	
}
