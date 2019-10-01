package com.demo.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.TimeAttributeDao;
import com.demo.model.TimeAttribute;

@Service
public class TimeAttributeService {

	@Autowired
	private TimeAttributeDao timeAttributeDao;

	public void valIdExist(String id) throws Exception {	
		if (!timeAttributeDao.isIdExist(id)) {
			throw new Exception("Data does not exist");
		}
	}

	public void valIdNotNull(String id) throws Exception {
		if (id.isEmpty()) {
			throw new Exception("Id cannot be emptied");
		}
	}

	public void valNonBk(TimeAttribute timeAttribute) throws Exception {

	}
	
	public void valBkNotExist(TimeAttribute timeAttribute) throws Exception {
		if (timeAttributeDao.isBkExist(timeAttribute.getTimeGroup().getId(), timeAttribute.getCode())) {
			throw new Exception("Data already exist");
		}
	}

	public void valBkNotChange(TimeAttribute timeAttribute) throws Exception {
		String tGroup = findById(timeAttribute.getId()).getTimeGroup().getId();
		String code = findById(timeAttribute.getId()).getCode();

		if (!timeAttribute.getTimeGroup().getId().equals(tGroup) || !timeAttribute.getCode().equals(code)) {
			throw new Exception("time group or code cannot be changed");
		}
	}

	public void valBkNotNull(TimeAttribute timeAttribute) throws Exception {
		if (timeAttribute.getTimeGroup() == null) {
			if (timeAttribute.getTimeGroup().getId().isEmpty()) {
				throw new Exception("time group cannot be emptied");
			}
		}
		if (timeAttribute.getCode() == null || timeAttribute.getCode().trim().isEmpty()) {
			throw new Exception("code cannot be emptied");
		}
	}

	public List<TimeAttribute> findAll() {
		return timeAttributeDao.findAll();
	}

	public TimeAttribute findById(String id) {
		return timeAttributeDao.findOne(id);
	}

	public void save(TimeAttribute timeAttribute) throws Exception {
		timeAttribute.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		valNonBk(timeAttribute);

		timeAttributeDao.create(timeAttribute);
	}

	public void update(TimeAttribute timeAttribute) throws Exception {
		valIdNotNull(timeAttribute.getId());
		valIdExist(timeAttribute.getId());
		timeAttribute.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
		valNonBk(timeAttribute);

		timeAttributeDao.update(timeAttribute);
	}

	public void delete(String id) throws Exception {
		valIdExist(id);
		
		timeAttributeDao.deleteById(id);
	}
}
