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
@RequestMapping("busschedule")
public class BusScheduleCotroller {
	
	@Autowired
	BusScheduleService service;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<BusScheduleDto>> saveBus(@RequestBody BusSchedule schedule) {
		return service.saveBus(schedule);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<BusScheduleDto>> findBus(@RequestParam int id) {
		return service.findSchedule(id);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<BusScheduleDto>> deleteBus(@RequestParam int id) {
		return service.deleteSchedule(id);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<BusScheduleDto>> updateBus(@RequestBody BusSchedule schedule,@RequestParam int id) {
		return service.updateSchedule(schedule, id);
	}
	
	@GetMapping("getall")
	public ResponseEntity<ResponseStructure<List<BusScheduleDto>>> getallBus(){
		return service.getAllSchedule();
	}
}
