package com.jspider.app.Bus_Ticket_booking.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jspider.app.Bus_Ticket_booking.dto.BusDto;
import com.jspider.app.Bus_Ticket_booking.entity.Bus;
import com.jspider.app.Bus_Ticket_booking.repository.BusRepo;


@Repository
public class BusDao {

	@Autowired
	BusRepo repo;
	
	@Autowired
	BusDto dto;

	public Bus saveBus(Bus bus) {
		return repo.save(bus);
	}

	public Bus findById(int id) {
		Optional<Bus> op = repo.findById(id);
		if(op.isPresent()) {
			return op.get();
		}
		else {
			return null;
		}
	}
	public Bus deleteBus(int id) {
		Bus bus=findById(id);
		if(bus!=null) {
			repo.delete(bus);
			return bus;
		}
		else {
			return null;
		}
	}
	public Bus updateBus(Bus bus,int id) {
		Bus exbus=findById(id);
		if(exbus!=null) {
			bus.setId(id);
			return repo.save(bus);
		}
		else {
			return null;
		}
	}
	public List<Bus> getAllBus(){
		return repo.findAll();
	}
	
	public BusDto busDtoConverstion(Bus bus) {
		dto.setBusno(bus.getBusno());
		dto.setCompany(bus.getCompany());
		dto.setType(bus.getType());
		dto.setCapacity(bus.getCapacity());
		dto.setDepartureplace(bus.getDepartureplace());
		dto.setDepartureTime(bus.getDepartureTime());
		dto.setArrivaltime(bus.getArrivaltime());
		dto.setArrivalplace(bus.getArrivalplace());
		dto.setJournyDuration(bus.getJournyDuration());
		dto.setTicketprice(bus.getTicketprice());
		dto.setBusschedule(bus.getBusschedule());
		dto.setTickets(bus.getTickets());
		dto.setSeats(bus.getSeats());
		return dto;
	}
}
