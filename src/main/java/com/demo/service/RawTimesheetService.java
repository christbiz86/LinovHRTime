package com.demo.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.RawTimesheetDao;
import com.demo.model.RawTimesheet;


@Service
public class RawTimesheetService {
	@Autowired
	private RawTimesheetDao rawTimesheetDao;
	
	public List<RawTimesheet> findAll(){
		return rawTimesheetDao.findAll();
	}
	
	public RawTimesheet findById(String id) {
		return rawTimesheetDao.findOne(id);
	}
	
	public RawTimesheet findByBk(Date date, Timestamp clockTime, String companyId, String employeeId) {
		return findByBk(date, clockTime, companyId, employeeId);
	}
	
	private void valIdExist(String id)throws Exception{
		if(!rawTimesheetDao.isIdExist(id)) {
			throw new Exception("Data is Not Found");
		}
	}
	
	private void valIdNotNull(RawTimesheet rawTimesheet)throws Exception {
		
		if(rawTimesheet.getId() == null || rawTimesheet.getId().isEmpty()) {
			throw new Exception("Id Cannot be empty");
		}
	}
	
	private void valNonBk(RawTimesheet rawTimesheet) throws Exception {
		if(rawTimesheet.getDate() == null) {
			throw new Exception("Date Raw Timesheet cannot be empty");
		}
		if(rawTimesheet.getIsAnalyzed() == null) {
			throw new Exception("Status Analyzed cannot be empty");
		}
	}
	
	private void valBkNotExist(RawTimesheet rawTimesheet)throws Exception{
		if(rawTimesheetDao.isBkExist(rawTimesheet.getDate(), rawTimesheet.getClockTime() ,rawTimesheet.getCompany().getId(), rawTimesheet.getEmployee().getId())) {
			throw new Exception("Data already exist");
		}
	}
	
	private void valBkNotChange(RawTimesheet rawTimesheet)throws Exception{
		String company = findById(rawTimesheet.getId()).getCompany().getId();
		String employee = findById(rawTimesheet.getId()).getEmployee().getId();
		Date date = findById(rawTimesheet.getId()).getDate();
		Timestamp clockTime = findById(rawTimesheet.getId()).getClockTime();
		
		if(!rawTimesheet.getCompany().getId().equals(company)) {
			throw new Exception("Company Raw Timesheet is cannot be changed");
		}
		if(!rawTimesheet.getEmployee().getId().equals(employee)) {
			throw new Exception("Employee Raw Timesheet is cannot be changed");
		}
		if(!rawTimesheet.getDate().equals(date)) {
			throw new Exception("Date Raw Timesheet is cannot be changed");
		}
		if(!rawTimesheet.getClockTime().equals(clockTime)) {
			throw new Exception("Clock Time Raw Timesheet is cannot be changed");
		}
	}
	
	private void valBkNotNull(RawTimesheet rawTimesheet) throws Exception{
		if(rawTimesheet.getCompany() == null || rawTimesheet.getCompany().getId().isEmpty()) {
			throw new Exception("Company Raw Timesheet cannot be empty");
		}
		
		if(rawTimesheet.getEmployee().getId().isEmpty()) {
			throw new Exception("Employee Raw Timesheet cannot be empty");
		}
		
		if(rawTimesheet.getDate() == null) {
			throw new Exception("Date Raw Timesheet is cannot be empty");
		}
		
		if(rawTimesheet.getClockTime() == null) {
			throw new Exception("Clock Time Raw Timesheet is cannot be empty");
		}
	}
	
	public void save(RawTimesheet rawTimesheet) throws Exception {
		valBkNotNull(rawTimesheet);
		valNonBk(rawTimesheet);
		valBkNotExist(rawTimesheet);
		rawTimesheetDao.create(rawTimesheet);
	}
	
	public void update(RawTimesheet rawTimesheet) throws Exception {
		valIdNotNull(rawTimesheet);
		valIdExist(rawTimesheet.getId());
		valBkNotNull(rawTimesheet);
		valBkNotChange(rawTimesheet);
		valNonBk(rawTimesheet);
		rawTimesheetDao.update(rawTimesheet);
	}
	
	public void delete(String id) throws Exception {
		rawTimesheetDao.deleteById(id);
	}
}
