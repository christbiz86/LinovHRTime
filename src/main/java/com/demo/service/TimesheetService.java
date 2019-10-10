package com.demo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.demo.dao.TimesheetDao;
import com.demo.model.LoginSession;
import com.demo.model.Timesheet;

@Service
public class TimesheetService {

	@Autowired
	private TimesheetDao timesheetDao;

	public void valIdExist(String id) throws Exception {	
		if (!timesheetDao.isIdExist(id)) {
			throw new Exception("Data does not exist");
		}
	}

	public void valIdNotNull(String id) throws Exception {
		if (id.isEmpty()) {
			throw new Exception("Id cannot be emptied");
		}
	}

	public void valNonBk(Timesheet timesheet) throws Exception {
		if (timesheet.getIsWorkDay() == null) {
			throw new Exception("is work day cannot be emptied");
		}
		if (timesheet.getIsFlexyHour() == null) {
			throw new Exception("is flexy hour cannot be emptied");
		}
	}
	
	public void valBkNotExist(Timesheet timesheet) throws Exception {
		if (timesheetDao.isBkExist(timesheet.getCompany().getId(), timesheet.getEmployee().getId(), timesheet.getDate())) {
			throw new Exception("Data already exist");
		}
	}

	public void valBkNotChange(Timesheet timesheet) throws Exception {
		String company = findById(timesheet.getId()).getCompany().getId();
		String employee = findById(timesheet.getId()).getEmployee().getId();
		Date date = findById(timesheet.getId()).getDate();

		if (!timesheet.getCompany().getId().equals(company) || !timesheet.getEmployee().getId().equals(employee) || !(timesheet.getDate() == date)) {
			throw new Exception("company or employee or date cannot be changed");
		}
	}

	public void valBkNotNull(Timesheet timesheet) throws Exception {
		if (timesheet.getCompany() == null) {
			if (timesheet.getCompany().getId().isEmpty()) {
				throw new Exception("company cannot be emptied");
			}
		}
		if (timesheet.getEmployee() == null) {
			if (timesheet.getEmployee().getId().isEmpty()) {
				throw new Exception("employee cannot be emptied");
			}
		}
		if (timesheet.getDate() == null) {
			throw new Exception("date cannot be emptied");
		}
	}

	public List<Timesheet> findAll() {
		return timesheetDao.findAll(((LoginSession)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getSelectedCompanyId());
	}

	public Timesheet findById(String id) {
		return timesheetDao.findOne(id);
	}

	public void save(Timesheet timesheet) throws Exception {
		valBkNotNull(timesheet);
		valBkNotExist(timesheet);
		valNonBk(timesheet);

		timesheetDao.create(timesheet);
	}

	public void update(Timesheet timesheet) throws Exception {
		valIdNotNull(timesheet.getId());
		valIdExist(timesheet.getId());
		valBkNotNull(timesheet);
		valBkNotChange(timesheet);
		valNonBk(timesheet);

		timesheetDao.update(timesheet);
	}

	public void delete(String id) throws Exception {
		valIdExist(id);
		
		timesheetDao.deleteById(id);
	}
}
