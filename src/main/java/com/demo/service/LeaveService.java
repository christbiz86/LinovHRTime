package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.LeaveDao;
import com.demo.model.Leave;

@Service
public class LeaveService {
	@Autowired
	private LeaveDao leaveDao;
	
	public List<Leave> findAll() {
		return leaveDao.findAll();
	}
	
	public Leave findById(String id) {
		return leaveDao.findOne(id);
	}
	
	public Leave findByBk(String code, String companyId) {
		return leaveDao.findByBk(code, companyId);
	}
	
	private void valIdExist(String id)throws Exception{
		if(!leaveDao.isIdExist(id)) {
			throw new Exception("Data is Not Found");
		}
	}
	
	private void valIdNotNull(Leave leave)throws Exception {
		
		if(leave.getId() == null || leave.getId().isEmpty()) {
			throw new Exception("Id Cannot be empty");
		}
	}
	
	private void valNonBk(Leave leave) throws Exception {
		if(leave.getName().isEmpty()) {
			throw new Exception("Leave Name cannot be empty");
		}
		if(leave.getType().isEmpty()) {
			throw new Exception("Leave Type cannot be empty");
		}
		if(leave.getIsAnnualLeave() == null) {
			throw new Exception("Type Of Leave cannot be empty");
		}
		if(leave.getIsAllowHalfDay() == null) {
			throw new Exception("Half Day of Leave cannot be empty");
		}
		if(leave.getIsAnnualLeaveDeductor() == null) {
			throw new Exception("Annual Leave Deductor cannot be empty");
		}
		if(leave.getIsRequestAble() == null) {
			throw new Exception("Request Able Annual Leave cannot be empty");
		}
		if(leave.getIsQuotaBased() == null) {
			throw new Exception("Quota Based Annual Leave cannot be empty");
		}
	}
	
	private void valBkNotExist(Leave leave)throws Exception{
		if(leaveDao.isBkExist(leave.getCode(), leave.getCompany().getId())) {
			throw new Exception("Data already exist");
		}
	}
	
	private void valBkNotChange(Leave leave)throws Exception{
		String company = findById(leave.getId()).getCompany().getId();
		String code = findById(leave.getId()).getCode();
		if(!leave.getCompany().getId().equals(company)) {
			throw new Exception("Company is cannot be changed");
		}
		if(!leave.getCode().equals(code)) {
			throw new Exception("Leave Code is cannot be changed");
		}
	}
	
	private void valBkNotNull(Leave leave) throws Exception{
		if(leave.getCompany() == null || leave.getCompany().getId().isEmpty()) {
			throw new Exception("Leave Company cannot be empty");
		}
		
		if(leave.getCode().isEmpty()) {
			throw new Exception("Leave Code cannot be empty");
		}
	}
	
	public void save(Leave leave) throws Exception {
		valBkNotNull(leave);
		valNonBk(leave);
		valBkNotExist(leave);
		
		leaveDao.create(leave);
	}
	
	public void update(Leave leave) throws Exception {
		valIdNotNull(leave);
		valIdExist(leave.getId());
		valBkNotNull(leave);
		valBkNotChange(leave);
		valNonBk(leave);
	}
	
	public void delete(String id) throws Exception {
		leaveDao.deleteById(id);
	}
}
