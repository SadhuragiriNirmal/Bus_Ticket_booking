package com.jspider.app.Bus_Ticket_booking.service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jspider.app.Bus_Ticket_booking.dao.BusDao;
import com.jspider.app.Bus_Ticket_booking.dao.BusScheduleDao;
import com.jspider.app.Bus_Ticket_booking.dto.BusDto;
import com.jspider.app.Bus_Ticket_booking.dto.BusScheduleDto;
import com.jspider.app.Bus_Ticket_booking.dto.SeatDto;
import com.jspider.app.Bus_Ticket_booking.entity.Bus;
import com.jspider.app.Bus_Ticket_booking.entity.BusSchedule;
import com.jspider.app.Bus_Ticket_booking.entity.Seat;
import com.jspider.app.Bus_Ticket_booking.entity.Ticket;
import com.jspider.app.Bus_Ticket_booking.util.ResponseStructure;



@Service
public class BusService {
	
	@Autowired
	BusDao dao;
	
	@Autowired
	BusDto dto;
	
	@Autowired
	BusScheduleDto bsDto;
	
	@Autowired
	BusScheduleDao bsdao;
	
	//save bus
	
	public ResponseEntity<ResponseStructure<BusDto>> saveBus(Bus bus, int scheduleid){
		
		ResponseStructure<BusDto> structure = new ResponseStructure<BusDto>();
		
		Bus existBus = dao.savebus(bus);
		if(existBus != null) {
			
			insertBusToEveryDate(existBus, scheduleid, true);
			dto = convertBusToBusDto(existBus);
			dto.setBusSchedules(convertBusScheduleToBusSchedelDto(bsdao.findBusScheduleByid(scheduleid)));
			dto.setSeat(convertSeatToSeatDto(existBus.getSeat()));
			structure.setMessage("bus saved successfully");
			structure.setData(dto);
			structure.setStatus(HttpStatus.CREATED.value());
			if(bsdao.findBusScheduleByid(scheduleid) != null) {
				
				establishedReltionBwtBusandBusScheduel(bus, scheduleid);
				
			}
			return new ResponseEntity<ResponseStructure<BusDto>>(structure,HttpStatus.CREATED);
		}
		else return null;
	}
	
	//get bus
	
	public ResponseEntity<ResponseStructure<BusDto>> findByBusId(int busid){
		
		ResponseStructure<BusDto> structure = new ResponseStructure<BusDto>();
		Bus existBus = dao.findBusByid(busid);
		if(existBus != null) {
			
			dto = convertBusToBusDto(existBus);
			dto.setBusSchedules(convertBusScheduleToBusSchedelDto(existBus.getBusSchedules()));
			dto.setSeat(convertSeatToSeatDto(existBus.getSeat()));
			structure.setMessage("bus get successfully");
			structure.setData(dto);
			structure.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<BusDto>>(structure,HttpStatus.FOUND);
		}
		else return null;
	}
	
	//update bus
	
	public ResponseEntity<ResponseStructure<BusDto>> updateBus(Bus bus, int busid){
		
		ResponseStructure<BusDto> structure = new ResponseStructure<BusDto>();
		
		Bus existingBus = dao.findBusByid(busid);
		if(existingBus.getBusSchedules() != null) {
			
			bus.setBusSchedules(existingBus.getBusSchedules());
		}
		
		Bus existBus = dao.updatebus(bus, busid);
		if(existBus != null) {
			
			dto = convertBusToBusDto(existBus);
			dto.setBusSchedules(convertBusScheduleToBusSchedelDto(existBus.getBusSchedules()));
			dto.setSeat(convertSeatToSeatDto(existBus.getSeat()));
			structure.setMessage("bus update successfully");
			structure.setData(dto);
			structure.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<BusDto>>(structure,HttpStatus.OK);
		}
		else return null;
	}
	
	//delete bus
	
	public ResponseEntity<ResponseStructure<BusDto>> deleteBus(int busid){
		
		ResponseStructure<BusDto> structure = new ResponseStructure<BusDto>();
		Bus bus = dao.findBusByid(busid);
		if(bus != null) {
			
			brakeRelationBwtBusandBusScheduel(bus);		
		}
		
		Bus existBus = dao.deletebus(busid);
		if(existBus != null) {
			
			dto = convertBusToBusDto(existBus);
			dto.setBusSchedules(convertBusScheduleToBusSchedelDto(existBus.getBusSchedules()));
			dto.setSeat(convertSeatToSeatDto(existBus.getSeat()));
			structure.setMessage("bus deleted successfully");
			structure.setData(dto);
			structure.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<BusDto>>(structure,HttpStatus.OK);
		}
		else return null;
	}
	
	//get all bus 
	
	public ResponseEntity<ResponseStructure<List<BusDto>>> getAllBus(){
		
		ResponseStructure<List<BusDto>> structure = new ResponseStructure<>();
		List<Bus> existBus = dao.getAllbus();
		List<BusDto> newBusDto = new ArrayList<BusDto>();
		if(existBus != null) {
			
			for(Bus bus:existBus) {
				
				dto = convertBusToBusDto(bus);
				dto.setBusSchedules(convertBusScheduleToBusSchedelDto(bus.getBusSchedules()));
				dto.setSeat(convertSeatToSeatDto(bus.getSeat()));
				newBusDto.add(dto);
				
			}
			structure.setMessage("buses got successfully");
			structure.setData(newBusDto);
			structure.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<List<BusDto>>>(structure,HttpStatus.FOUND);
			
		}
		else return null;
	}
	
	//bus schedule to bus scheduleDto conversion
	
	public BusScheduleDto convertBusScheduleToBusSchedelDto(BusSchedule busSchedule) {
		
		
		if(busSchedule != null) {
			
			bsDto.setFrom(busSchedule.getFrom());
			bsDto.setTo(busSchedule.getTo());
			bsDto.setVia(busSchedule.getVia());
			bsDto.setKm(busSchedule.getKm());
			return bsDto;
			
		}else return null;
		
	}

	//bus to busDto conversion
	
	public BusDto convertBusToBusDto(Bus bus) {
		
		dto.setCompany(bus.getCompany());
		dto.setBusno(bus.getBusno());
		dto.setBusCapacity(bus.getBusCapacity());
		dto.setSeatsAvailable(bus.getSeatsAvailable());
		dto.setBusType(bus.getBusType());
		dto.setDeparturePlace(bus.getDeparturePlace());
		dto.setArrivalPlace(bus.getArrivalPlace());
		dto.setDepartureDate(bus.getDepartureDate());
		dto.setArrivalDate(bus.getArrivalDate());
		dto.setDepartureTime(bus.getDepartureTime());
		dto.setArrivalTime(bus.getArrivalTime());
		dto.setJourneyDuration(bus.getJourneyDuration());
		return dto;

	}
	
	
	//seat to seatDto conversion
	
	public List<SeatDto> convertSeatToSeatDto(List<Seat> seats) {
		
		List<SeatDto> listSeats = new ArrayList<SeatDto>();
		
		if(seats != null) {
			
			for(Seat s:seats) {
				
				SeatDto seatDto = new SeatDto();
				seatDto.setSeatno(s.getSeatno());
				seatDto.setSeatType(s.getSeatType());
				seatDto.setSeatPosition(s.getSeatPosition());
				seatDto.setSeatPosition(s.getSeatPosition());
				seatDto.setBookedDate(s.getBookedDate());
				listSeats.add(seatDto);
				
			}
			return listSeats;
			
		}else return null;
		
	}
	
	
	//assign schedule to bus
	
	public void establishedReltionBwtBusandBusScheduel(Bus bus, int scheduleid) {
	
		if(bus != null) {
			
			BusSchedule schedule = bsdao.findBusScheduleByid(scheduleid);
			List<Bus> buses = schedule.getBus();
			buses.add(bus);
			bus.setBusSchedules(schedule);
			dao.updatebus(bus, bus.getBusid());
			schedule.setBus(buses);
			bsdao.updateBusSchedule(schedule, scheduleid);
		}
		
	}
	
	//brake relation btw bus and busSchedule
	
	public void brakeRelationBwtBusandBusScheduel(Bus bus) {
		
		BusSchedule schedule = bus.getBusSchedules();
		List<Bus> buses = schedule.getBus();
		if(buses != null) {
			
			List<Bus> newBuses = new ArrayList<Bus>(); 
			for(Bus b:buses) {
				
				if(bus.getBusid() != b.getBusid()) {
					
					newBuses.add(b);
				}
			}
			schedule.setBus(newBuses);
			bsdao.updateBusSchedule(schedule, schedule.getBusScheduleid());
			
		}
	}
	
	
	//assigne ticket to bus
	
	public void assignTicketsToBus(Ticket ticket, int busid) {
		
		Bus bus = dao.findBusByid(busid);
		List<Ticket> tickets = bus.getTicket();
		tickets.add(ticket);
		bus.setTicket(tickets);
		dao.updatebus(bus, busid);
	}
		
	//update bus ticket List
	
	public void updateBusTickets(Ticket ticket) {
		
		List<Ticket> tickets = ticket.getBus().getTicket();
		if(tickets != null) {
			
			List<Ticket> new_tickets = new ArrayList<Ticket>();
			for(Ticket t:tickets) {
				
				if(t.getTicketid() != ticket.getTicketid()) {
					
					new_tickets.add(t);
				}
			}
		    Bus bus = ticket.getBus();
		    bus.setTicket(new_tickets);
		    dao.updatebus(bus, bus.getBusid());
			
		}
		else return;
		
	}
	
	//auto inserter of bus respect to date and each new bus insertion
	
	public void insertBusToEveryDate(Bus bus, int scheduleid, boolean newEntery) {
		
		LocalDate localDate = LocalDate.now();
		int year = localDate.getYear();
		int month = localDate.getMonthValue();
		int day = localDate.getDayOfMonth();
		int range = lengthOfTheMonth(year, month) - day;
		List<String> dates = getAllDatesOfMonth(year, month);
		if(newEntery) {
			
			bus.setDepartureDate(dates.get(day-1));
			bus.setArrivalDate(dates.get(day));
			dao.updatebus(bus, bus.getBusid());
		}
		else {
			
			Bus newBus = getNewBus(bus);
			newBus.setDepartureDate(dates.get(day-1));
			newBus.setArrivalDate(dates.get(day));
			dao.savebus(newBus);
			establishedReltionBwtBusandBusScheduel(newBus, scheduleid);
			
		}
		autoBusInserter(year, month, range, day, scheduleid, bus, dates);
		
	}
	
	//bus inserter
	
	public void autoBusInserter(int year, int month, int range, int day, int scheduleid, Bus bus, List<String> dates) {
		
		for(int i = 0; i < range; i++) {
			
			Bus newBus = getNewBus(bus);
			newBus.setDepartureDate(dates.get(day));
			if((day+1) < dates.size())newBus.setArrivalDate(dates.get(day+1));
			else {
				
				List<String> nextDates = getAllDatesOfMonth(year, month+1);
				newBus.setArrivalDate(nextDates.get(0));
			}
			dao.savebus(newBus);
			establishedReltionBwtBusandBusScheduel(newBus, scheduleid);
			day++;
		}
		
	}
	
	
	//date list provider respect to year and month
	
    public static List<String> getAllDatesOfMonth(int year, int month) {
        
    	List<String> dates = new ArrayList<>();
        YearMonth yearMonth = YearMonth.of(year, month);
        int daysInMonth = yearMonth.lengthOfMonth();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (int day = 1; day <= daysInMonth; day++) {
            LocalDate date = yearMonth.atDay(day);
            dates.add(date.format(formatter));
        }
        return dates;
    }
    
    //provide length(days) of the given month and year
    
    public int lengthOfTheMonth(int year, int month) {
    	
    	YearMonth yearMonth = YearMonth.of(year, month);
        int daysInMonth = yearMonth.lengthOfMonth();
        return daysInMonth;
    }
    
    //getNewBus
    
    public Bus getNewBus(Bus bus) {
    	
    	Bus b =	new Bus();
    	b.setCompany(bus.getCompany());
		b.setBusno(bus.getBusno());
		b.setBusCapacity(bus.getBusCapacity());
		b.setSeatsAvailable(bus.getSeatsAvailable());
		b.setBusType(bus.getBusType());
		b.setDeparturePlace(bus.getDeparturePlace());
		b.setArrivalPlace(bus.getArrivalPlace());
		b.setDepartureDate(bus.getDepartureDate());
		b.setArrivalDate(bus.getArrivalDate());
		b.setDepartureTime(bus.getDepartureTime());
		b.setArrivalTime(bus.getArrivalTime());
		b.setJourneyDuration(bus.getJourneyDuration());
    	return b;
    	
    }
    
    //filter bus by bus number
    
    public List<Bus> getDistinctBusByBusNo() {
    	
    	List<Bus> buses = dao.getAllbus();
    	List<Bus> newBuses = new ArrayList<Bus>();
    	for(Bus bus:buses) {
    		
    		if(newBuses.size()==0) {
    			
    			newBuses.add(bus);
    		}
    		else {
    			
    			int count = 0;
    			for(Bus b:newBuses) {
    				
    				if(!b.getBusno().equals(bus.getBusno())) {
    					count++;
    				}	
    			}
    			
    			if(newBuses.size()==count)newBuses.add(bus);
    		}
    		
    	}
    	
    	return newBuses;
    }
    
	//filter bus by respected given departure date
	
	public List<Bus> filterBusByDepartureDate(List<Bus> buses, String departureDate){
		
		List<Bus> filteredBusList = new ArrayList<Bus>();
		
		for(Bus bus:buses) {
			
			if(bus.getDepartureDate().equals(departureDate)) {
				
				filteredBusList.add(bus);
			}
			
		}
		return filteredBusList;
		
	}
    
}