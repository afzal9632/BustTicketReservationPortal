package com.root.services;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.root.exceptions.RouteException;
import com.root.models.Bus;
import com.root.models.Route;
import com.root.repository.BusDao;
import com.root.repository.RouteDao;
@Service
public class RouteServiceImplemetation implements RouteService{

	@Autowired
	private RouteDao routeDao;
	
	@Autowired
	private BusDao busDao;
	

	@Override
	public Route addRoute(Route route) throws RouteException {

		
		Route newRoute = routeDao.findByRouteFromAndRouteTo(route.getRouteFrom(), route.getRouteTo());
		
		if(newRoute != null) throw new RouteException("Route "+ newRoute.getRouteFrom() +" to "+ newRoute.getRouteTo()+ " is already present!");

			return routeDao.save(route);
	}

	@Override
	public Route updateRoute(Route route) throws RouteException {


		
		Optional<Route> existedRoute = routeDao.findById(route.getRouteId());
		if(existedRoute.isPresent()) {
			
			Route presentRoute = existedRoute.get();
			List<Bus> busList = presentRoute.getBusList();
			
			if(!busList.isEmpty()) throw new RouteException("Cannot update running route! Buses are already scheduled in the route.");
			
			return routeDao.save(route);
		}
		else
			throw new RouteException("Route doesn't exist with routeId "+ route.getRouteId());

	}

	@Override
	public Route deleteRoute(int routeId) throws RouteException {

	
		Optional<Route> route=routeDao.findById(routeId);
		
		if(route.isPresent()) {
			Route existingRoute=route.get();
			routeDao.delete(existingRoute);
			return existingRoute;
		}
		else
			throw new RouteException("There is no route of routeId "+ routeId);

	}


	@Override
	public Route viewRoute(int routeId) throws RouteException {
		
		Optional<Route> existedRoute=routeDao.findById(routeId);
		
		if(existedRoute.isPresent()) {
			return existedRoute.get();
		}
		else
			throw new RouteException("There is no route present of routeId " + routeId);
		
	}

	@Override
	public List<Route> viewAllRoute() throws RouteException {
			
		List<Route> routes=routeDao.findAll();
		
		if(routes.size()>0)
			return routes;
		else
			throw new RouteException("There is no route available");
			
	}

}
