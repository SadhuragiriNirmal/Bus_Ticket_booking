package com.jspider.app.Bus_Ticket_booking.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jspider.app.Bus_Ticket_booking.entity.User;
import com.jspider.app.Bus_Ticket_booking.repository.UserRepo;




@Repository
public class UserDao {
	
	@Autowired
	UserRepo repo;
	
	//save user
	
	public User saveUser(User u) {
		
		User user =  repo.save(u);
		if(user != null) {
			
			return user;
		}
		else return null;
	}
	
	
	//get user
	
	public User findByUserId(int userid) {
		
		Optional<User> opt =  repo.findById(userid);
		
		if(opt.isPresent()) {
			
			return opt.get();
		}
		else return null;	
	}
	
	
	//update user
	
	public User updateUser(User u, int userid) {
		
		User existingUser = findByUserId(userid);
		
		if(existingUser != null) {
			
			u.setUserid(existingUser.getUserid());
			return repo.save(u);
		}
		else return null;
	}
	
	
	//delete user
	
    public User deleteUser(int userid) {
    
    	User u = findByUserId(userid);
        if(u!=null) {
        	
        	repo.delete(u);
        	return u;
        }
        else return null;
    }
    
    
    //get all user
    
    public List<User> getAllUser(){
    	
    	List<User> users = repo.findAll();
    	if(users != null) {
    		
    		return users;
    	}
    	else return null;
    	
    }
    
    //find by email
    
    public User findByuemail(String uemail) {
    	
    	if(uemail != null) {
    		
    		return repo.findByuemail(uemail);
    	}
    	else return null;
    }

}
