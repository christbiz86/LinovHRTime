package com.demo.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.TimeGroupScheduleDao;
import com.demo.model.TimeGroupSchedule;

@Service
public class TimeGroupScheduleService {

	@Autowired
	private TimeGroupScheduleDao timeGroupScheduleDao;

	public void valIdExist(String id) throws Exception {	
		if (!timeGroupScheduleDao.isIdExist(id)) {
			throw new Exception("Data does not exist");
		}
	}

	public void valIdNotNull(String id) throws Exception {
		if (id.isEmpty()) {
			throw new Exception("Id cannot be emptied");
		}
	}

	public void valNonBk(TimeGroupSchedule timeGroupSchedule) throws Exception {
		if (timeGroupSchedule.getDate() == null) {
			throw new Exception("date cannot be emptied");
		}
		
	}
	
	public void valBkNotExist(TimeGroupSchedule timeGroupSchedule) throws Exception {
		if (timeGroupScheduleDao.isBkExist(timeGroupSchedule.getTimeGroup().getId(), timeGroupSchedule.getCode())) {
			throw new Exception("Data already exist");
		}
	}

	public void valBkNotChange(TimeGroupSchedule timeGroupSchedule) throws Exception {
		String timeGroup = findById(timeGroupSchedule.getId()).getTimeGroup().getId();
		String code = findById(timeGroupSchedule.getId()).getCode();

		if (!timeGroupSchedule.getTimeGroup().getId().equals(timeGroup) || !timeGroupSchedule.getCode().equals(code)) {
			throw new Exception("time group or code cannot be changed");
		}
	}

	public void valBkNotNull(TimeGroupSchedule timeGroupSchedule) throws Exception {
		if (timeGroupSchedule.getTimeGroup() == null) {
			if (timeGroupSchedule.getTimeGroup().getId().isEmpty()) {
				throw new Exception("time group cannot be emptied");
			}
		}
		if (timeGroupSchedule.getCode() == null || timeGroupSchedule.getCode().trim().isEmpty()) {
			throw new Exception("code cannot be emptied");
		}
	}

	public List<TimeGroupSchedule> findAll() {
		return timeGroupScheduleDao.findAll();
	}

	public TimeGroupSchedule findById(String id) {
		return timeGroupScheduleDao.findOne(id);
	}

	public void save(TimeGroupSchedule timeGroupSchedule) throws Exception {
		timeGroupSchedule.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		valNonBk(timeGroupSchedule);

		timeGroupScheduleDao.create(timeGroupSchedule);
	}

	public void update(TimeGroupSchedule timeGroupSchedule) throws Exception {
		valIdNotNull(timeGroupSchedule.getId());
		valIdExist(timeGroupSchedule.getId());
		timeGroupSchedule.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
		valNonBk(timeGroupSchedule);

		timeGroupScheduleDao.update(timeGroupSchedule);
	}

	public void delete(String id) throws Exception {
		valIdExist(id);
		
		timeGroupScheduleDao.deleteById(id);
	}
}
