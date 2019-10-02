package com.demo.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.ScheduleExceptionDao;
import com.demo.model.ScheduleException;

@Service
public class ScheduleExceptionService {

	@Autowired
	private ScheduleExceptionDao scheduleExceptionDao;

	public void valIdExist(String id) throws Exception {	
		if (!scheduleExceptionDao.isIdExist(id)) {
			throw new Exception("Data does not exist");
		}
	}

	public void valIdNotNull(String id) throws Exception {
		if (id.isEmpty()) {
			throw new Exception("Id cannot be emptied");
		}
	}

	public void valNonBk(ScheduleException scheduleException) throws Exception {
		if (scheduleException.getDate() == null) {
			throw new Exception("date cannot be emptied");
		}
		if (scheduleException.getReason().trim().isEmpty()) {
			throw new Exception("reason cannot be emptied");
		}
	}
	
	public void valBkNotExist(ScheduleException scheduleException) throws Exception {
		if (scheduleExceptionDao.isBkExist(scheduleException.getLeave().getId(), scheduleException.getEmployee().getId(), scheduleException.getDate())) {
			throw new Exception("Data already exist");
		}
	}

	public void valBkNotChange(ScheduleException scheduleException) throws Exception {
		String leave = findById(scheduleException.getId()).getLeave().getId();
		String employee = findById(scheduleException.getId()).getEmployee().getId();
		Date date = findById(scheduleException.getId()).getDate();

		if (!scheduleException.getLeave().getId().equals(leave) || !scheduleException.getEmployee().getId().equals(employee) || !(scheduleException.getDate() == date)) {
			throw new Exception("leave or employee or date cannot be changed");
		}
	}

	public void valBkNotNull(ScheduleException scheduleException) throws Exception {
		if (scheduleException.getLeave() == null) {
			if (scheduleException.getLeave().getId().isEmpty()) {
				throw new Exception("leave cannot be emptied");
			}
		}
		if (scheduleException.getEmployee() == null) {
			if (scheduleException.getEmployee().getId().isEmpty()) {
				throw new Exception("employee cannot be emptied");
			}
		}
		if (scheduleException.getDate() == null) {
			throw new Exception("date cannot be emptied");
		}
	}

	public List<ScheduleException> findAll() {
		return scheduleExceptionDao.findAll();
	}

	public ScheduleException findById(String id) {
		return scheduleExceptionDao.findOne(id);
	}

	public void save(ScheduleException scheduleException) throws Exception {
		scheduleException.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		valNonBk(scheduleException);

		scheduleExceptionDao.create(scheduleException);
	}

	public void update(ScheduleException scheduleException) throws Exception {
		valIdNotNull(scheduleException.getId());
		valIdExist(scheduleException.getId());
		scheduleException.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
		valNonBk(scheduleException);

		scheduleExceptionDao.update(scheduleException);
	}

	public void delete(String id) throws Exception {
		valIdExist(id);
		
		scheduleExceptionDao.deleteById(id);
	}
}
