package com.jspider.app.Bus_Ticket_booking.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jspider.app.Bus_Ticket_booking.entity.Bus;
import com.jspider.app.Bus_Ticket_booking.repository.BusRepo;

@Repository
public class BusDao {
	
	@Autowired
	BusRepo busRepo;
	
	//save bus schedule
	
	public Bus savebus(Bus bus) {
		
		Bus existBus = busRepo.save(bus);
		if(existBus != null) {
			
			return existBus;
		}
		else return null;
	}
	
	//get bus schedule
	
	public Bus findBusByid(int busid) {
		
		 Optional<Bus> bus =  busRepo.findById(busid);
		 
		 if(bus != null) {
			 
			 return bus.get();
		 }
		 else return null;
	}
	
    //update bus schedule
	
	public Bus updatebus(Bus bus, int busid) {
		
		Bus existBus = findBusByid(busid);
		
		if(existBus != null) {
			
			bus.setBusid(busid);
			return busRepo.save(bus);
		}
		else return null;
		
	}
	
	//delete bus schedules
	
	public Bus deletebus(int busid) {
		
		Bus existBus = findBusByid(busid);
		
	    if(existBus != null) {
	    	
	    	busRepo.delete(existBus);
	    	return existBus;
	    }
	    else return null;
	}
	
	
	//get all bus schedules
	
	public List<Bus>  getAllbus() {
		
		List<Bus> bus = busRepo.findAll();
		if(bus != null) {
			
			return bus;
		}
		else return null;
	}
	
	//get bus by bus number and departure date
	
	public Bus findBusByBusNumberAndDepatureDate(String busno, String departureDate) {
		
		Bus bus = busRepo.findBusByBusNumberAndDepartureDate(busno, departureDate);
		if(bus != null) {
			
			return bus;
		}
		else return null;
	}

}
