package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.AttendanceDao;
import com.demo.model.Attendance;

@Service
public class AttendanceService {
	
	@Autowired
	private AttendanceDao attendanceDao;
	
	public void valIdExist(String id) throws Exception {
		if(!attendanceDao.isIdExist(id)) {
			throw new Exception("Attendance not found!");
		}
	}
	
	public void valIdNotNull(Attendance attendance) throws Exception {
		if(attendance.getId().isEmpty()) {
			throw new Exception("Attendance ID can't empty!");
		}
	}
	
	public List<Attendance> findAll() {
		return attendanceDao.findAll();
	}
	
	public Attendance findById(String id) {
		return attendanceDao.findOne(id);
	}
	
	public Attendance findByCode(String code) {
		return attendanceDao.findByCode(code);
	}
	
	public void valBkNotNull(Attendance attendance) throws Exception {
		if(attendance.getCompany().getId().isEmpty()) {
			throw new Exception("Company can't empty!");
		}
		if(attendance.getCode().isEmpty()) {
			throw new Exception("Code can't empty!");
		}
	}
	
	public void valBkNotExist(Attendance attendance) throws Exception {
		if(attendanceDao.isBkExist(attendance.getCode(), attendance.getCompany().getId())) {
			throw new Exception("Time Definition already exists!");
		}
	}
	
	public void valBkNotChange(Attendance attendance) throws Exception {
		String code = findById(attendance.getId()).getCode();
		String company = findById(attendance.getId()).getCompany().getId();
		
		if(!(attendance.getCode().equals(code) && attendance.getCompany().getId().equals(company))) {
			throw new Exception("BK can't be changed!");
		}
	}
	
	public void valNonBk(Attendance attendance) throws Exception {
		if(attendance.getName().isEmpty()) {
			throw new Exception("Time Definition name can't empty!");
		}
	}
	
	public void insert(Attendance attendance) throws Exception {
		valBkNotNull(attendance);
		valBkNotExist(attendance);
		valNonBk(attendance);
		attendanceDao.create(attendance);
	}
	
	public void update(Attendance attendance) throws Exception {
		valIdNotNull(attendance);
		valIdExist(attendance.getId());
		valBkNotNull(attendance);
		valBkNotChange(attendance);
		valNonBk(attendance);
		attendanceDao.update(attendance);
	}
	
	public void delete(String id) throws Exception {
		valIdExist(id);
		attendanceDao.deleteById(id);
	}

}
