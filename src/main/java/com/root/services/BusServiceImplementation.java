package com.root.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.root.exceptions.BusException;
import com.root.models.Bus;
import com.root.models.Route;
import com.root.repository.BusDao;
import com.root.repository.RouteDao;

@Service
public class BusServiceImplementation implements BusService{
	
	@Autowired
	private BusDao busDao;
	
	@Autowired
	private RouteDao routeDao;
	


	@Override
	public Bus addBus(Bus bus) throws BusException {


		if(bus.getBusJourneyDate().isBefore(LocalDate.now()))
			throw new BusException("Bus journey date can't be in past. Please add future date!");

		if(bus.getDepartureTime().isBefore(bus.getArrivalTime()))
			throw new BusException("Bus can't departure before arrival. Please add correct time!");


		Route route=routeDao.findByRouteFromAndRouteTo(bus.getRouteFrom(), bus.getRouteTo());
		
		if(route != null) {
			route.getBusList().add(bus);
			bus.setRoute(route);
			return busDao.save(bus);
		}
		else
			throw new BusException("Bus detail is not correct");
	}

	@Override
	public Bus updateBus(Bus bus) throws BusException {
		

		
		Optional<Bus> existingBusOpt=busDao.findById(bus.getBusId());
		
		if(existingBusOpt.isPresent()) {
			
			Bus existingBus = existingBusOpt.get();
			
			if(existingBus.getAvailableSeats()!=existingBus.getSeats()) throw new BusException("Cannot update already scheduled bus!");
			
			Route route=routeDao.findByRouteFromAndRouteTo(bus.getRouteFrom(), bus.getRouteTo());
			if(route == null) throw new BusException("Invalid route!");
			bus.setRoute(route);
			return busDao.save(bus);
		}
		else
			throw new BusException("Bus doesn't exist with busId : "+ bus.getBusId());

	}

	@Override
	public Bus deleteBus(Integer busId) throws BusException {

		
		Optional<Bus> bus=busDao.findById(busId);
		
		
		if(bus.isPresent()) {
			Bus existingBus = bus.get();			
			
			if(LocalDate.now().isBefore(existingBus.getBusJourneyDate()) && existingBus.getAvailableSeats()!=existingBus.getSeats())
				throw new BusException("Cannot delete as the bus is scheduled and reservations are booked for the bus.");
			
			
			busDao.delete(existingBus);
			
			return existingBus;
		}
		else
			throw new BusException("Bus doesn't exist with busId : "+busId);
		
	}

	@Override
	public List<Bus> viewBusByType(String BusType) throws BusException {
		List<Bus> listOfBusType = busDao.findByBusType(BusType);
		
		if(listOfBusType.size() >0)
			return listOfBusType;
		else
			throw new BusException("There is no bus of type "+ BusType);
	
	}

	@Override
	public List<Bus> viewAllBuses() throws BusException {
		
		List<Bus> buses= busDao.findAll();
		if(buses.size()>0)
			return buses;
		else
			throw new BusException("There is no bus availabe now");

	}

	@Override
	public Bus viewBus(Integer busId) throws BusException {
		Optional<Bus> bus=busDao.findById(busId);
		
		if(bus.isPresent()) {
			Bus existingBus = bus.get();
			return existingBus;
		}
		else
			throw new BusException("Bus doesn't exist with busId : "+busId);
	}
	
}
