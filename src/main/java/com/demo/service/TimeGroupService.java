package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.demo.dao.TimeGroupDao;
import com.demo.model.LoginSession;
import com.demo.model.TimeGroup;

@Service
public class TimeGroupService {

	@Autowired
	private TimeGroupDao timeGroupDao;

	public void valIdExist(String id) throws Exception {	
		if (!timeGroupDao.isIdExist(id)) {
			throw new Exception("Data does not exist");
		}
	}

	public void valIdNotNull(String id) throws Exception {
		if (id.isEmpty()) {
			throw new Exception("Id cannot be emptied");
		}
	}

	public void valNonBk(TimeGroup timeGroup) throws Exception {
		if (timeGroup.getName().isEmpty()) {
			throw new Exception("name cannot be emptied");
		}
		if (timeGroup.getCode().isEmpty()) {
			throw new Exception("code cannot be emptied");
		}
		if (timeGroup.getIsIgnoreHoliday() == null) {
			throw new Exception("is ignore holiday cannot be emptied");
		}
		if (timeGroup.getIsFlexyHour() == null) {
			throw new Exception("is flexy hour cannot be emptied");
		}
		if (timeGroup.getIsAllowOvertime() == null) {
			throw new Exception("is allow overtime cannot be emptied");
		}
		if (timeGroup.getIsFlexyHolidayOvertime() == null) {
			throw new Exception("is flexy holiday overtime cannot be emptied");
		}
		if (timeGroup.getIsDefault() == null) {
			throw new Exception("is default cannot be emptied");
		}
		if (timeGroup.getIsAbsenceAsAnnLeave() == null) {
			throw new Exception("is absence as annual leave cannot be emptied");
		}
		if (timeGroup.getOvtRounding() == null) {
			throw new Exception("ovt rounding cannot be emptied");
		}
		if (timeGroup.getLateTolerance() == null) {
			throw new Exception("late tolerance cannot be emptied");
		}
		if (timeGroup.getEffBegin() == null) {
			throw new Exception("eff begin cannot be emptied");
		}
		if (timeGroup.getEffEnd() == null) {
			throw new Exception("eff end cannot be emptied");
		}
		if (timeGroup.getIsActive() == null) {
			throw new Exception("is active cannot be emptied");
		}
	}
	
	public void valBkNotExist(TimeGroup timeGroup) throws Exception {
		if (timeGroupDao.isBkExist(timeGroup.getCompany().getId(), timeGroup.getCode())) {
			throw new Exception("Data already exist");
		}
	}

	public void valBkNotChange(TimeGroup timeGroup) throws Exception {
		String company = findById(timeGroup.getId()).getCompany().getId();
		String code = findById(timeGroup.getId()).getCode();

		if (!timeGroup.getCompany().getId().equals(company) || !timeGroup.getCode().equals(code)) {
			throw new Exception("company or code cannot be changed");
		}
	}

	public void valBkNotNull(TimeGroup timeGroup) throws Exception {
		if (timeGroup.getCompany() == null) {
			if (timeGroup.getCompany().getId().isEmpty()) {
				throw new Exception("company cannot be emptied");
			}
		}
		if (timeGroup.getCode() == null || timeGroup.getCode().trim().isEmpty()) {
			throw new Exception("code cannot be emptied");
		}
	}

	public List<TimeGroup> findAll() {
		return timeGroupDao.findAll(((LoginSession)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getSelectedCompanyId());
	}

	public TimeGroup findById(String id) {
		return timeGroupDao.findOne(id);
	}

	public void save(TimeGroup timeGroup) throws Exception {
		valBkNotNull(timeGroup);
		valBkNotExist(timeGroup);
		valNonBk(timeGroup);

		timeGroupDao.create(timeGroup);
	}

	public void update(TimeGroup timeGroup) throws Exception {
		valIdNotNull(timeGroup.getId());
		valIdExist(timeGroup.getId());
		valBkNotNull(timeGroup);
		valBkNotChange(timeGroup);
		valNonBk(timeGroup);

		timeGroupDao.update(timeGroup);
	}

	public void delete(String id) throws Exception {
		valIdExist(id);
		
		timeGroupDao.deleteById(id);
	}
}