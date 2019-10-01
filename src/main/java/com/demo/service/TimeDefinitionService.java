package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.TimeDefinitionDao;
import com.demo.model.TimeDefinition;

@Service
public class TimeDefinitionService {
	
	@Autowired
	private TimeDefinitionDao tdDao;
	
	public void valIdExist(String id) throws Exception {
		if(!tdDao.isIdExist(id)) {
			throw new Exception("Time Definition not found!");
		}
	}
	
	public void valIdNotNull(TimeDefinition td) throws Exception {
		if(td.getId().isEmpty()) {
			throw new Exception("Time Definition ID can't empty!");
		}
	}
	
	public List<TimeDefinition> findAll() {
		return tdDao.findAll();
	}
	
	public TimeDefinition findById(String id) {
		return tdDao.findOne(id);
	}
	
	public TimeDefinition findByCode(String code) {
		return tdDao.findByCode(code);
	}
	
	public void valBkNotNull(TimeDefinition td) throws Exception {
		if(td.getCompany().getId().isEmpty()) {
			throw new Exception("Company can't empty!");
		}
		if(td.getCode().isEmpty()) {
			throw new Exception("Code can't empty!");
		}
	}
	
	public void valBkNotExist(TimeDefinition td) throws Exception {
		if(tdDao.isBkExist(td.getCode(), td.getCompany().getId())) {
			throw new Exception("Time Definition already exists!");
		}
	}
	
	public void valBkNotChange(TimeDefinition td) throws Exception {
		String code = findById(td.getId()).getCode();
		String company = findById(td.getId()).getCompany().getId();
		
		if(!(td.getCode().equals(code) && td.getCompany().getId().equals(company))) {
			throw new Exception("BK can't be changed!");
		}
	}
	
	public void valNonBk(TimeDefinition td) throws Exception {
		if(td.getName().isEmpty()) {
			throw new Exception("Time Definition name can't empty!");
		}
		if(td.getMeasurement() == null) {
			throw new Exception("Measurement can't empty!");
		}
		if(td.getLovTdevty() == null) {
			throw new Exception("LOV Time Definition Value Type can't empty!");
		}
		if(td.getIsFlexy() == null) {
			throw new Exception("is_flexy can't empty!");
		}
		if(td.getIsWorkday() == null) {
			throw new Exception("is_working can't empty!");
		}
		if(td.getIsValue1() == null) {
			throw new Exception("is_value1 can't empty!");
		}
		if(td.getIsValue2() == null) {
			throw new Exception("is_value2 can't empty!");
		}
	}
	
	public void insert(TimeDefinition td) throws Exception {
		valBkNotNull(td);
		valBkNotExist(td);
		valNonBk(td);
		tdDao.create(td);
	}
	
	public void update(TimeDefinition td) throws Exception {
		valIdNotNull(td);
		valIdExist(td.getId());
		valBkNotNull(td);
		valBkNotChange(td);
		valNonBk(td);
		tdDao.update(td);
	}
	
	public void delete(String id) throws Exception {
		valIdExist(id);
		tdDao.deleteById(id);
	}

}
