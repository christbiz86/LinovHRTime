package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.RequestRawTimesheetDao;
import com.demo.model.RequestRawTimesheet;

@Service
public class RequestRawTimesheetService {
	
	@Autowired
	private RequestRawTimesheetDao rrtDao;
	
	public void valIdExist(String id) throws Exception {
		if(!rrtDao.isIdExist(id)) {
			throw new Exception("Permit Request not found!");
		}
	}
	
	public void valIdNotNull(RequestRawTimesheet rrt) throws Exception {
		if(rrt.getId().isEmpty()) {
			throw new Exception("Request Raw Timesheet ID can't empty!");
		}
	}
	
	public List<RequestRawTimesheet> findAll() {
		return rrtDao.findAll();
	}
	
	public RequestRawTimesheet findById(String id) {
		return rrtDao.findOne(id);
	}
	
	public void valBkNotNull(RequestRawTimesheet rrt) throws Exception {
		if(rrt.getCompany().getId().isEmpty()) {
			throw new Exception("Company can't empty!");
		}
		if(rrt.getEmployee().getId().isEmpty()) {
			throw new Exception("Employee can't empty!");
		}
		if(rrt.getDate() == null) {
			throw new Exception("Date can't empty!");
		}
	}
	
	public void valBkNotExist(RequestRawTimesheet rrt) throws Exception {
		if(rrtDao.isBkExist(rrt.getCompany().getId(), rrt.getEmployee().getId(), rrt.getDate())) {
			throw new Exception("Request Raw Timesheet already exists!");
		}
	}
	
	public void valBkNotChange(RequestRawTimesheet rrt) throws Exception {
		String company = findById(rrt.getId()).getCompany().getId();
		String employee = findById(rrt.getId()).getEmployee().getId();
		String date = findById(rrt.getId()).getDate().toString();
		
		if(!(rrt.getCompany().getId().equals(company) && rrt.getEmployee().getId().equals(employee) && rrt.getDate().toString().equals(date))) {
			throw new Exception("BK can't be changed!");
		}
	}
	
	public void valNonBk(RequestRawTimesheet rrt) throws Exception {
		if(rrt.getStatus().isEmpty()) {
			throw new Exception("Status can't empty!");
		}
		if(rrt.getTimeInLat() == null) {
			throw new Exception("Time In Latitude can't empty!");
		}
		if(rrt.getTimeInLong() == null) {
			throw new Exception("Time In Longitude can't empty!");
		}
		if(rrt.getTimeOutLat() == null) {
			throw new Exception("Time Out Latitude can't empty!");
		}
		if(rrt.getTimeOutLong() == null) {
			throw new Exception("Time Out Longitude can't empty!");
		}
	}
	
	public void insert(RequestRawTimesheet rrt) throws Exception {
		valBkNotNull(rrt);
		valBkNotExist(rrt);
		valNonBk(rrt);
		rrtDao.create(rrt);
	}
	
	public void update(RequestRawTimesheet rrt) throws Exception {
		valIdNotNull(rrt);
		valIdExist(rrt.getId());
		valBkNotNull(rrt);
		valBkNotChange(rrt);
		valNonBk(rrt);
		rrtDao.update(rrt);
	}
	
	public void delete(String id) throws Exception {
		valIdExist(id);
		rrtDao.deleteById(id);
	}

}
