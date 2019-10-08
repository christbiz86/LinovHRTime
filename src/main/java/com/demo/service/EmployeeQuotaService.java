package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.EmployeeQuotaDao;
import com.demo.model.EmployeeQuota;

@Service
public class EmployeeQuotaService {
	
	@Autowired
	private EmployeeQuotaDao eqDao;
	
	public void valIdExist(String id) throws Exception {
		if(!eqDao.isIdExist(id)) {
			throw new Exception("Employee Quota not found!");
		}
	}
	
	public void valIdNotNull(EmployeeQuota eq) throws Exception {
		if(eq.getId().isEmpty()) {
			throw new Exception("Employee Quota ID can't empty!");
		}
	}
	
	public List<EmployeeQuota> findAll() {
		return eqDao.findAll();
	}
	
	public EmployeeQuota findById(String id) {
		return eqDao.findOne(id);
	}
	
	public void valBkNotNull(EmployeeQuota eq) throws Exception {
		if(eq.getEmployee().getId().isEmpty()) {
			throw new Exception("Employee can't empty!");
		}
		if(eq.getLeave().getId().isEmpty()) {
			throw new Exception("Leave can't empty!");
		}
	}
	
	public void valBkNotExist(EmployeeQuota eq) throws Exception {
		if(eqDao.isBkExist(eq.getEmployee().getId(), eq.getLeave().getId())) {
			throw new Exception("Employee Quota already exists!");
		}
	}
	
	public void valBkNotChange(EmployeeQuota eq) throws Exception {
		String employee = findById(eq.getId()).getEmployee().getId();
		String leave = findById(eq.getId()).getLeave().getId();
		
		if(!(eq.getEmployee().getId().equals(employee) && eq.getLeave().getId().equals(leave))) {
			throw new Exception("BK can't be changed!");
		}
	}
	
	public void valNonBk(EmployeeQuota eq) throws Exception {
		if(eq.getCompany().getId().isEmpty()) {
			throw new Exception("Company can't empty!");
		}
		if(eq.getMaxQuota() == null) {
			throw new Exception("Max quota can't empty!");
		}
		if(eq.getCarriedQuota() == null) {
			throw new Exception("Carry quota can't empty!");
		}
		if(eq.getIsActive() == null) {
			throw new Exception("isActive can't empty!");
		}
		if(eq.getEffBegin() == null) {
			throw new Exception("Effective Begin can't empty!");
		}
		if(eq.getEffEnd() == null) {
			throw new Exception("Effective End can't empty!");
		}
	}
	
	public void insert(EmployeeQuota eq) throws Exception {
		valBkNotNull(eq);
		valBkNotExist(eq);
		valNonBk(eq);
		eqDao.create(eq);
	}
	
	public void update(EmployeeQuota eq) throws Exception {
		valIdNotNull(eq);
		valIdExist(eq.getId());
		valBkNotNull(eq);
		valBkNotChange(eq);
		valNonBk(eq);
		eqDao.update(eq);
	}
	
	public void delete(String id) throws Exception {
		valIdExist(id);
		eqDao.deleteById(id);
	}

}
