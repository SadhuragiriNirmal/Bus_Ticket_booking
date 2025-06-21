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

import com.jspider.app.Bus_Ticket_booking.dto.BusDto;
import com.jspider.app.Bus_Ticket_booking.entity.Bus;
import com.jspider.app.Bus_Ticket_booking.service.BusService;
import com.jspider.app.Bus_Ticket_booking.util.ResponseStructure;

@RestController
@RequestMapping("bus")
public class BusController {
	

	@Autowired
	BusService service;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<BusDto>> saveBus(@RequestBody Bus bus, @RequestParam int scheduleid){
		
		return service.saveBus(bus, scheduleid);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<BusDto>> findByBusId(@RequestParam int busid){
		
		return service.findByBusId(busid);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<BusDto>> updateBus(@RequestBody Bus bus, @RequestParam int busid){
		
		return service.updateBus(bus, busid);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<BusDto>> deleteBus(@RequestParam int busid){
		
		return service.deleteBus(busid);
	}
	
	@GetMapping("buses")
	public ResponseEntity<ResponseStructure<List<BusDto>>> getAllBus(){
		
		return service.getAllBus();
	}


}
