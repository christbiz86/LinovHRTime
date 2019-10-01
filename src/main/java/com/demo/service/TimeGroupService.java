package com.demo.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.TimeGroupDao;
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
		return timeGroupDao.findAll();
	}

	public TimeGroup findById(String id) {
		return timeGroupDao.findOne(id);
	}

	public void save(TimeGroup timeGroup) throws Exception {
		timeGroup.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		valNonBk(timeGroup);

		timeGroupDao.create(timeGroup);
	}

	public void update(TimeGroup timeGroup) throws Exception {
		valIdNotNull(timeGroup.getId());
		valIdExist(timeGroup.getId());
		timeGroup.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
		valNonBk(timeGroup);

		timeGroupDao.update(timeGroup);
	}

	public void delete(String id) throws Exception {
		valIdExist(id);
		
		timeGroupDao.deleteById(id);
	}
}
