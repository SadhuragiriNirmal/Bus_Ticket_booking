package com.jspider.app.Bus_Ticket_booking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jspider.app.Bus_Ticket_booking.dao.BusScheduleDao;
import com.jspider.app.Bus_Ticket_booking.dto.BusScheduleDto;
import com.jspider.app.Bus_Ticket_booking.entity.BusSchedule;
import com.jspider.app.Bus_Ticket_booking.util.ResponseStructure;


@Service
public class BusScheduleService {
	
	@Autowired
	BusScheduleDao dao;
	
	
	public ResponseEntity<ResponseStructure<BusScheduleDto>> saveBus(BusSchedule schedule){
		ResponseStructure<BusScheduleDto> structure=new ResponseStructure<BusScheduleDto>();
		BusSchedule saveBusSchedule=dao.saveBusSchedule(schedule);
		if(saveBusSchedule!=null) {
			structure.setData(dao.busScheduleDtoConversiton(saveBusSchedule));
			structure.setMessage("Schedule saved success");
			structure.setStatus(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<BusScheduleDto>>(structure,HttpStatus.CREATED);
		}
		return null;
	}
	
	public ResponseEntity<ResponseStructure<BusScheduleDto>> findSchedule(int id){
		ResponseStructure<BusScheduleDto> structure=new ResponseStructure<BusScheduleDto>();
		BusSchedule saveBusSchedule=dao.findById(id);
		if(saveBusSchedule!=null) {
			structure.setData(dao.busScheduleDtoConversiton(saveBusSchedule));
			structure.setMessage("Schedule find by given id");
			structure.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<BusScheduleDto>>(structure,HttpStatus.CREATED);
		}
		return null;
	}
	
	public ResponseEntity<ResponseStructure<BusScheduleDto>> deleteSchedule(int id){
		ResponseStructure<BusScheduleDto> structure=new ResponseStructure<BusScheduleDto>();
		BusSchedule saveBusSchedule=dao.findById(id);
		if(saveBusSchedule!=null) {
			structure.setData(dao.busScheduleDtoConversiton(dao.deleteBusschedule(id)));
			structure.setMessage("Schedule deleted by given id");
			structure.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<BusScheduleDto>>(structure,HttpStatus.CREATED);
		}
		return null;
	}
	
	public ResponseEntity<ResponseStructure<BusScheduleDto>> updateSchedule(BusSchedule schedule,int id){
		ResponseStructure<BusScheduleDto> structure=new ResponseStructure<BusScheduleDto>();
		BusSchedule saveSchedule=dao.findById(id);
		if(saveSchedule!=null) {
			structure.setData(dao.busScheduleDtoConversiton(dao.updateBusSchedule(saveSchedule, id)));
			structure.setMessage("Schedule updated successfully");
			structure.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<BusScheduleDto>>(structure,HttpStatus.OK);
		}
		return null;
	}
	public ResponseEntity<ResponseStructure<List<BusScheduleDto>>> getAllSchedule(){
		ResponseStructure<List<BusScheduleDto>> structure=new ResponseStructure<List<BusScheduleDto>>();
		structure.setData(dao.busScheduleDtoList(dao.getAllSchedules()));
		structure.setMessage("found Schedules details");
		structure.setStatus(HttpStatus.FOUND.value());

		return new ResponseEntity<ResponseStructure<List<BusScheduleDto>>>(structure,HttpStatus.FOUND);

	}
	
	

	
}
