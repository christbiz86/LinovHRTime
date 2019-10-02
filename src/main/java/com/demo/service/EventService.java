package com.demo.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.EventDao;
import com.demo.model.Event;

@Service
public class EventService {
	@Autowired
	private EventDao eventDao;
	
	public List<Event> findAll(){
		return eventDao.findAll();
	}
	
	public Event findById(String id) {
		return eventDao.findOne(id);
	}
	
	public Event findByBk(String name, String companyId, Timestamp eventStart) {
		return eventDao.findByBk(name, companyId, eventStart);
	}
	
	private void valIdExist(String id)throws Exception{
		if(!eventDao.isIdExist(id)) {
			throw new Exception("Data is Not Found");
		}
	}
	
	private void valIdNotNull(Event event)throws Exception {
		
		if(event.getId() == null || event.getId().isEmpty()) {
			throw new Exception("Id Cannot be empty");
		}
	}
	
	private void valNonBk(Event event) throws Exception {
		if(event.getCompany() == null) {
			if(event.getCompany().getId().isEmpty()) {
				throw new Exception("Event From Company cannot be empty");
			}
		}
		if(event.getName().isEmpty()) {
			throw new Exception("Location Name cannot be empty");
		}
		if(event.getColor().isEmpty()) {
			throw new Exception("Color Event cannot be empty");
		}
		if(event.getIsFullDay() == null) {
			throw new Exception("Status Full Day cannot be empty");
		}
	}
	
	private void valBkNotExist(Event event)throws Exception{
		if(eventDao.isBkExist(event.getName(), event.getCompany().getId(), event.getEventStart())) {
			throw new Exception("Data already exist");
		}
	}
	
	private void valBkNotChange(Event event)throws Exception{
		String company = findById(event.getId()).getCompany().getId();
		String name = findById(event.getId()).getName();
		Timestamp eventStart = findById(event.getId()).getEventStart();
		if(!event.getCompany().getId().equals(company)) {
			throw new Exception("Company is cannot be changed");
		}
		if(!event.getName().equals(name)) {
			throw new Exception("Event Name is cannot be changed");
		}
		if(event.getEventStart() == eventStart) {
			throw new Exception("Event Start is cannot be changed");
		}
	}
	
	private void valBkNotNull(Event event) throws Exception{
		if(event.getCompany() == null || event.getCompany().getId().isEmpty()) {
			throw new Exception("Location Company cannot be empty");
		}
		
		if(event.getName().isEmpty()) {
			throw new Exception("Event Name cannot be empty");
		}
		
		if(event.getEventStart() == null) {
			throw new Exception("Event Start cannot be empty");
		}
	}
	
	public void save(Event event) throws Exception {
		valBkNotNull(event);
		valNonBk(event);
		valBkNotExist(event);
		eventDao.create(event);
	}
	
	public void update(Event event) throws Exception {
		valIdNotNull(event);
		valIdExist(event.getId());
		valBkNotNull(event);
		valBkNotChange(event);
		valNonBk(event);
		eventDao.update(event);
	}
	
	public void delete(String id) throws Exception {
		eventDao.deleteById(id);
	}
}
