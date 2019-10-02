package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.LeaveEligibilitiesDao;
import com.demo.model.LeaveEligibilities;

@Service
public class LeaveEligibilitiesService {
	@Autowired
	private LeaveEligibilitiesDao leaveEligibilitiesDao;
	
	public List<LeaveEligibilities> findAll() {
		return leaveEligibilitiesDao.findAll();
	}
	
	public LeaveEligibilities findById(String id) {
		return leaveEligibilitiesDao.findOne(id);
	}
	
	public LeaveEligibilities findByBk(String companyId, String leaveId) {
		return leaveEligibilitiesDao.findByBk(companyId, leaveId);
	}
	
	private void valIdExist(String id)throws Exception{
		if(!leaveEligibilitiesDao.isIdExist(id)) {
			throw new Exception("Data is Not Found");
		}
	}
	
	private void valIdNotNull(LeaveEligibilities leaveEligibilities)throws Exception {
		if(leaveEligibilities.getId() == null || leaveEligibilities.getId().isEmpty()) {
			throw new Exception("Id Cannot be empty");
		}
	}
	
	private void valNonBk(LeaveEligibilities leaveEligibilities) throws Exception {
		if(leaveEligibilities.getValue().isEmpty()) {
			throw new Exception("Value Leave Eligibilities cannot be empty");
		}
	}
	
	private void valBkNotExist(LeaveEligibilities leaveEligibilities)throws Exception{
		if(leaveEligibilitiesDao.isBkExist(leaveEligibilities.getCompany().getId(), leaveEligibilities.getLeave().getId())) {
			throw new Exception("Data already exist");
		}
	}
	
	private void valBkNotChange(LeaveEligibilities leaveEligibilities)throws Exception{
		String company = findById(leaveEligibilities.getId()).getCompany().getId();
		String leave = findById(leaveEligibilities.getId()).getLeave().getId();
		if(!leaveEligibilities.getCompany().getId().equals(company)) {
			throw new Exception("Company Leave Eligibilities is cannot be changed");
		}
		if(!leaveEligibilities.getLeave().getId().equals(leave)) {
			throw new Exception("Leave is cannot be changed");
		}
	}
	
	private void valBkNotNull(LeaveEligibilities leaveEligibilities) throws Exception{
		
		if(leaveEligibilities.getCompany() == null || leaveEligibilities.getCompany().getId().isEmpty()) {
			throw new Exception("Leave Eligibilities Company cannot be empty");
		}
		
		if(leaveEligibilities.getLeave() == null || leaveEligibilities.getLeave().getId().isEmpty()) {
			throw new Exception("Leave cannot be empty");
		}
	}
	
	public void save(LeaveEligibilities leaveEligibilities) throws Exception {
		valBkNotNull(leaveEligibilities);
		valNonBk(leaveEligibilities);
		valBkNotExist(leaveEligibilities);
		
		leaveEligibilitiesDao.create(leaveEligibilities);
	}
	
	public void update(LeaveEligibilities leaveEligibilities) throws Exception {
		valIdNotNull(leaveEligibilities);
		valIdExist(leaveEligibilities.getId());
		valBkNotNull(leaveEligibilities);
		valBkNotChange(leaveEligibilities);
		valNonBk(leaveEligibilities);
		
		leaveEligibilitiesDao.update(leaveEligibilities);
	}
	
	public void delete(String id) throws Exception {
		leaveEligibilitiesDao.deleteById(id);
	}
}
