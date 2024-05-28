package com.jspider.app.Bus_Ticket_booking.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jspider.app.Bus_Ticket_booking.entity.Passenger;
import com.jspider.app.Bus_Ticket_booking.repository.PassengerRepo;



@Repository
public class PassengerDao {
	
	
	@Autowired
	PassengerRepo repo;
	
	//save Passenger
	
	public Passenger savePassenger(Passenger p) {
		
		Passenger Passenger =  repo.save(p);
		if(Passenger != null) {
			
			return Passenger;
		}
		else return null;
	}
	
	
	//get Passenger
	
	public Passenger findByPassengerId(int Passengerid) {
		
		Optional<Passenger> opt =  repo.findById(Passengerid);
		
		if(opt.isPresent()) {
			
			return opt.get();
		}
		else return null;	
	}
	
	
	//update Passenger
	
	public Passenger updatePassenger(Passenger p, int Passengerid) {
		
		Passenger existingPassenger = findByPassengerId(Passengerid);
		
		if(existingPassenger != null) {
			
			p.setPassengerid(existingPassenger.getPassengerid());
			return repo.save(p);
		}
		else return null;
	}
	
	
	//delete Passenger
	
    public Passenger deletePassenger(int Passengerid) {
    
    	Passenger p = findByPassengerId(Passengerid);
        if(p!=null) {
        	
        	repo.delete(p);
        	return p;
        }
        else return null;
    }
    
    
    //get all Passenger
    
    public List<Passenger> getAllPassenger(){
    	
    	List<Passenger> Passengers = repo.findAll();
    	if(Passengers != null) {
    		
    		return Passengers;
    	}
    	else return null;
    	
    }


	
	
	
}
