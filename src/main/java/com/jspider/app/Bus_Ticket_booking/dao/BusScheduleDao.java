package com.jspider.app.Bus_Ticket_booking.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jspider.app.Bus_Ticket_booking.dto.BusDto;
import com.jspider.app.Bus_Ticket_booking.dto.BusScheduleDto;
import com.jspider.app.Bus_Ticket_booking.entity.Bus;
import com.jspider.app.Bus_Ticket_booking.entity.BusSchedule;
import com.jspider.app.Bus_Ticket_booking.repository.BusScheduleRepo;


@Repository
public class BusScheduleDao {
	
	@Autowired
	BusScheduleRepo repo;
	
	@Autowired
	BusScheduleDto scheduledto;
	@Autowired
	BusDao busDao;
	
	public BusSchedule saveBusSchedule(BusSchedule schedule) {
		return repo.save(schedule);
	}
	
	public BusSchedule findById(int id) {
		Optional<BusSchedule> op=repo.findById(id);
		if(op.isPresent()) {
			return op.get();
		}
		else {
			return null;
		}
	}
	public BusSchedule deleteBusschedule(int id) {
		BusSchedule exschedule=findById(id);
		if(exschedule!=null) {
			repo.delete(exschedule);
			return exschedule;
		}
		else {
			return null;
		}
	}
	public BusSchedule updateBusSchedule(BusSchedule schedule,int id) {
		BusSchedule exschedule=findById(id);
		if(exschedule!=null) {
			schedule.setSid(id);
			return repo.save(schedule);
		}
		else {
			return null;
		}
	}
	public List<BusSchedule> getAllSchedules(){
		return repo.findAll();
	}
	
	public BusScheduleDto busScheduleDtoConversiton(BusSchedule schedule)
	{
		scheduledto.setArrivaltime(schedule.getArrivaltime());
		scheduledto.setBusFrom(schedule.getBusFrom());
		scheduledto.setBusTo(schedule.getBusTo());
		scheduledto.setBus(busScheduleDtoConversiton(schedule.getBus()));
		scheduledto.setDepartureTime(schedule.getDepartureTime());
		scheduledto.setDistance(schedule.getDistance());
		scheduledto.setJournyDuration(schedule.getJournyDuration());
		scheduledto.setTicketprice(schedule.getTicketprice());
		scheduledto.setVia(schedule.getVia());
		return scheduledto;
	}
	
	public List<BusDto> busScheduleDtoConversiton(List<Bus> bus){
		List<BusDto> buslist=new ArrayList<BusDto>();
		for(Bus b:bus) {
			busDao.busDtoConverstion(b);
		}
		return buslist;
	}
	
	public List<BusScheduleDto> busScheduleDtoList(List<BusSchedule> scheduleList ){
		List<BusScheduleDto> scheduleDtoList=new ArrayList<BusScheduleDto>();
		for(BusSchedule schedule:scheduleList) {
			scheduleDtoList.add(busScheduleDtoConversiton(schedule));
		}
		return scheduleDtoList;
	}
}
