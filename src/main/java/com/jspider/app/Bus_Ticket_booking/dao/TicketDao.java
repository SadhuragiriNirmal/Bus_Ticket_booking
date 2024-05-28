package com.jspider.app.Bus_Ticket_booking.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jspider.app.Bus_Ticket_booking.entity.Ticket;
import com.jspider.app.Bus_Ticket_booking.repository.TicketRepo;


@Repository
public class TicketDao {
	
	@Autowired
	TicketRepo repo;
	
	
	//save Ticket
	
		public Ticket saveTicket(Ticket t) {
			
			Ticket Ticket =  repo.save(t);
			if(Ticket != null) {
				
				return Ticket;
			}
			else return null;
		}
		
		
		//get Ticket
		
		public Ticket findByTicketId(int Ticketid) {
			
			Optional<Ticket> opt =  repo.findById(Ticketid);
			
			if(opt.isPresent()) {
				
				return opt.get();
			}
			else return null;	
		}
		
		
		//update Ticket
		
		public Ticket updateTicket(Ticket t, int Ticketid) {
			
			Ticket existingTicket = findByTicketId(Ticketid);
			
			if(existingTicket != null) {
				
				t.setTicketid(existingTicket.getTicketid());
				return repo.save(t);
			}
			else return null;
		}
		
		
		//delete Ticket
		
	    public Ticket deleteTicket(int Ticketid) {
	    
	    	Ticket t = findByTicketId(Ticketid);
	        if(t!=null) {
	        	
	        	repo.delete(t);
	        	return t;
	        }
	        else return null;
	    }
	    
	    
	    //get all Ticket
	    
	    public List<Ticket> getAllTicket(){
	    	
	    	List<Ticket> Tickets = repo.findAll();
	    	if(Tickets != null) {
	    		
	    		return Tickets;
	    	}
	    	else return null;
	    	
	    }


}
