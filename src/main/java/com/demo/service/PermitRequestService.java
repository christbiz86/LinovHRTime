package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.PermitRequestDao;
import com.demo.model.PermitRequest;

@Service
public class PermitRequestService {
	
	@Autowired
	private PermitRequestDao prDao;
	
	public void valIdExist(String id) throws Exception {
		if(!prDao.isIdExist(id)) {
			throw new Exception("Permit Request not found!");
		}
	}
	
	public void valIdNotNull(PermitRequest pr) throws Exception {
		if(pr.getId().isEmpty()) {
			throw new Exception("Permit Request ID can't empty!");
		}
	}
	
	public List<PermitRequest> findAll() {
		return prDao.findAll();
	}
	
	public PermitRequest findById(String id) {
		return prDao.findOne(id);
	}
	
	public void valBkNotNull(PermitRequest pr) throws Exception {
		if(pr.getCompany().getId().isEmpty()) {
			throw new Exception("Company can't empty!");
		}
		if(pr.getLovPermit().getId().isEmpty()) {
			throw new Exception("LOV Permit can't empty!");
		}
	}
	
	public void valBkNotExist(PermitRequest pr) throws Exception {
		if(prDao.isBkExist(pr.getCompany().getId(), pr.getLovPermit().getId())) {
			throw new Exception("Permit Request already exists!");
		}
	}
	
	public void valBkNotChange(PermitRequest pr) throws Exception {
		String company = findById(pr.getId()).getCompany().getId();
		String lov_permit = findById(pr.getId()).getLovPermit().getId();
		
		if(!(pr.getCompany().getId().equals(company) && pr.getLovPermit().getId().equals(lov_permit))) {
			throw new Exception("BK can't be changed!");
		}
	}
	
	public void valNonBk(PermitRequest pr) throws Exception {
		if(pr.getReason().isEmpty()) {
			throw new Exception("Permit Request reason can't empty!");
		}
		if(pr.getDate() == null) {
			throw new Exception("Permit Request date can't empty!");
		}
		if(pr.getStatus().isEmpty()) {
			throw new Exception("Permit Request status can't empty!");
		}
	}
	
	public void insert(PermitRequest pr) throws Exception {
		valBkNotNull(pr);
		valBkNotExist(pr);
		valNonBk(pr);
		prDao.create(pr);
	}
	
	public void update(PermitRequest pr) throws Exception {
		valIdNotNull(pr);
		valIdExist(pr.getId());
		valBkNotNull(pr);
		valBkNotChange(pr);
		valNonBk(pr);
		prDao.update(pr);
	}
	
	public void delete(String id) throws Exception {
		valIdExist(id);
		prDao.deleteById(id);
	}

}
