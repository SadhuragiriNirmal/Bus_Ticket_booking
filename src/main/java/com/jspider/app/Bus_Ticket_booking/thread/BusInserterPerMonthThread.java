package com.jspider.app.Bus_Ticket_booking.thread;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jspider.app.Bus_Ticket_booking.dao.BusDao;
import com.jspider.app.Bus_Ticket_booking.entity.Bus;
import com.jspider.app.Bus_Ticket_booking.service.BusService;

import jakarta.annotation.PostConstruct;

@Component
public class BusInserterPerMonthThread{
	
	@Autowired
	BusService busService;
	
	@Autowired
	BusDao busDao;
	
	@PostConstruct
	public void startNotificationService() {
		new Thread(() -> {
			int flag = 1;
			while (true) {
				try {
					
					LocalDate localDate = LocalDate.now();
					int day = localDate.getDayOfMonth();
					
					if(day == 1 && flag == 1) {
						
						List<Bus> buses = busService.getDistinctBusByBusNo();
						for(Bus bus:buses) {
							
							busService.insertBusToEveryDate(bus, bus.getBusSchedules().getBusScheduleid(), false);

						}
						flag = 0;
					}
					else if(day != 1) {
						
						flag = 1;
					}
					Thread.sleep(10000); // Sleep for 10 seconds
					
				} catch (InterruptedException e) {
	                    Thread.currentThread().interrupt();
				}
			}
		}).start();
	}
	
}
