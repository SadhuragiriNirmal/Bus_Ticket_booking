package com.jspider.app.Bus_Ticket_booking.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jspider.app.Bus_Ticket_booking.dto.BusScheduleDto;
import com.jspider.app.Bus_Ticket_booking.entity.BusSchedule;
import com.jspider.app.Bus_Ticket_booking.service.BusScheduleService;
import com.jspider.app.Bus_Ticket_booking.util.ResponseStructure;

@RestController
@RequestMapping("schedule")
public class BusScheduleController {
	
	@Autowired
	BusScheduleService service;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<BusScheduleDto>> saveBusSchedule(@RequestBody BusSchedule schedule){
		
		return service.saveBusSchedule(schedule);
		
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<BusScheduleDto>> findBusSchedule(@RequestParam int scheduleid){
		
		return service.findByBusScheduleId(scheduleid);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<BusScheduleDto>> updateBusSchedule(@RequestBody BusSchedule schedule, @RequestParam int scheduleid){
		
		return service.updateBusSchedule(schedule, scheduleid);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<BusScheduleDto>> deleteBusSchedule(@RequestParam int scheduleid){
		
		return service.deleteBusSchedule(scheduleid);
	}
	
	@GetMapping("schedules")
	public ResponseEntity<ResponseStructure<List<BusScheduleDto>>> getAllBusSchedule(){
		
		return service.getAllBusSchedule();
	}

}
