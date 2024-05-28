package com.jspider.app.Bus_Ticket_booking.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jspider.app.Bus_Ticket_booking.dao.BusDao;
import com.jspider.app.Bus_Ticket_booking.dto.BusDto;
import com.jspider.app.Bus_Ticket_booking.entity.Bus;
import com.jspider.app.Bus_Ticket_booking.util.ResponseStructure;

@Service
public class BusService {
	@Autowired
	BusDao dao;
	@Autowired
	BusDto dto;

	public ResponseEntity<ResponseStructure<BusDto>> saveBus(Bus bus) {
		ResponseStructure<BusDto> structure = new ResponseStructure<BusDto>();
		Bus savebus = dao.saveBus(bus);
		if (savebus != null) {
			structure.setData(dao.busDtoConverstion(savebus));
			structure.setMessage("Bus saved success");
			structure.setStatus(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<BusDto>>(structure, HttpStatus.CREATED);
		}
		return null;
	}

	public ResponseEntity<ResponseStructure<BusDto>> findBus(int id) {
		ResponseStructure<BusDto> structure = new ResponseStructure<BusDto>();
		Bus savebus = dao.findById(id);
		if (savebus != null) {
			structure.setData(dao.busDtoConverstion(savebus));
			structure.setMessage("Bus found with the given id");
			structure.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<BusDto>>(structure, HttpStatus.FOUND);
		}
		return null;
	}

	public ResponseEntity<ResponseStructure<BusDto>> updateBus(Bus bus, int id) {
		ResponseStructure<BusDto> structure = new ResponseStructure<BusDto>();
		Bus existbus = dao.findById(id);
		if (existbus != null) {
			structure.setData(dao.busDtoConverstion(dao.updateBus(existbus, id)));
			structure.setMessage("Bus updated successfully");
			structure.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<BusDto>>(structure, HttpStatus.OK);
		}
		return null;
	}

	public ResponseEntity<ResponseStructure<BusDto>> deleteBus(int id) {
		ResponseStructure<BusDto> structure = new ResponseStructure<BusDto>();
		Bus savebus = dao.findById(id);
		if (savebus != null) {
			structure.setData(dao.busDtoConverstion(savebus));
			structure.setMessage("Bus deleted successfully");
			structure.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<BusDto>>(structure, HttpStatus.OK);
		}
		return null;
	}

	public ResponseEntity<ResponseStructure<List<BusDto>>> getAllBus() {
		ResponseStructure<List<BusDto>> structure = new ResponseStructure<List<BusDto>>();
		List<BusDto> buslist = new ArrayList<BusDto>();
		for (Bus bus : dao.getAllBus()) {
			buslist.add(dao.busDtoConverstion(bus));
		}

		structure.setData(buslist);
		structure.setMessage("found adhar details");
		structure.setStatus(HttpStatus.FOUND.value());

		return new ResponseEntity<ResponseStructure<List<BusDto>>>(structure, HttpStatus.FOUND);

	}

}
