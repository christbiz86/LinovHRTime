package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.EventEligibilitiesDao;
import com.demo.model.EventEligibilities;

@Service
public class EventEligibilitiesService {
	@Autowired
	private EventEligibilitiesDao eventEligibilitiesDao;
	
	public List<EventEligibilities> findAll() {
		return eventEligibilitiesDao.findAll();
	}
	
	public EventEligibilities findById(String id) {
		return eventEligibilitiesDao.findOne(id);
	}
	
	public EventEligibilities findByBk(String companyId, String eventId) {
		return eventEligibilitiesDao.findByBk(companyId, eventId);
	}
	
	private void valIdExist(String id)throws Exception{
		if(!eventEligibilitiesDao.isIdExist(id)) {
			throw new Exception("Data is Not Found");
		}
	}
	
	private void valIdNotNull(EventEligibilities eventEligibilities)throws Exception {
		
		if(eventEligibilities.getId() == null || eventEligibilities.getId().isEmpty()) {
			throw new Exception("Id Cannot be empty");
		}
	}
	
	private void valNonBk(EventEligibilities eventEligibilities) throws Exception {
		if(eventEligibilities.getValue().isEmpty()) {
			throw new Exception("Event Value cannot be empty");
		}
		if(eventEligibilities.getPrivilege().isEmpty()) {
			throw new Exception("Privilege Event cannot be empty");
		}
	}
	
	private void valBkNotExist(EventEligibilities eventEligibilities)throws Exception{
		if(eventEligibilitiesDao.isBkExist(eventEligibilities.getCompany().getId(), eventEligibilities.getEvent().getId())) {
			throw new Exception("Data already exist");
		}
	}
	
	private void valBkNotChange(EventEligibilities eventEligibilities)throws Exception{
		String company = findById(eventEligibilities.getId()).getCompany().getId();
		String event = findById(eventEligibilities.getId()).getEvent().getId();
		if(!eventEligibilities.getCompany().getId().equals(company)) {
			throw new Exception("Event Company is cannot be changed");
		}
		if(!eventEligibilities.getEvent().getId().equals(event)) {
			throw new Exception("Event is cannot be changed");
		}
	}
	
	private void valBkNotNull(EventEligibilities eventEligibilities) throws Exception{
		
		if(eventEligibilities.getCompany() == null || eventEligibilities.getCompany().getId().isEmpty()) {
			throw new Exception("Event Company cannot be empty");
		}
		
		if(eventEligibilities.getEvent().getId().isEmpty()) {
			throw new Exception("Event cannot be empty");
		}
	}
	
	public void save(EventEligibilities eventEligibilities) throws Exception {
		valBkNotNull(eventEligibilities);
		valNonBk(eventEligibilities);
		valBkNotExist(eventEligibilities);
		eventEligibilitiesDao.create(eventEligibilities);
	}
	
	public void update(EventEligibilities eventEligibilities) throws Exception {
		valIdNotNull(eventEligibilities);
		valIdExist(eventEligibilities.getId());
		valBkNotNull(eventEligibilities);
		valBkNotChange(eventEligibilities);
		valNonBk(eventEligibilities);
		eventEligibilitiesDao.update(eventEligibilities);
	}
	
	public void delete(String id) throws Exception {
		eventEligibilitiesDao.deleteById(id);
	}
}
