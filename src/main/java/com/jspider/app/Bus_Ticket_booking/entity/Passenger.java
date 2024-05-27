package com.jspider.app.Bus_Ticket_booking.entity;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "passenger")
public class Passenger {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int passengerid;
	private String pname;
	private String pemail;
	private long pmobileno;
	private int pAge;
	private String pAgeCategory;
	private String paddress;
	@OneToOne(cascade = CascadeType.ALL)
	private Ticket ticket;
	
	public int getPassengerid() {
		return passengerid;
	}
	public void setPassengerid(int passengerid) {
		this.passengerid = passengerid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getPemail() {
		return pemail;
	}
	public void setPemail(String pemail) {
		this.pemail = pemail;
	}
	public long getPmobileno() {
		return pmobileno;
	}
	public void setPmobileno(long pmobileno) {
		this.pmobileno = pmobileno;
	}
	public int getpAge() {
		return pAge;
	}
	public void setpAge(int pAge) {
		this.pAge = pAge;
	}
	public String getpAgeCategory() {
		return pAgeCategory;
	}
	public void setpAgeCategory(String pAgeCategory) {
		this.pAgeCategory = pAgeCategory;
	}
	public String getPaddress() {
		return paddress;
	}
	public void setPaddress(String paddress) {
		this.paddress = paddress;
	}
	public Ticket getTicket() {
		return ticket;
	}
	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}	
	
}
