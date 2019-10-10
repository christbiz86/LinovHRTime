package com.demo.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.model.LeaveRequest;

@Repository
public class LeaveRequestDao extends AbstractJpaDao<LeaveRequest> {

	public LeaveRequestDao() {
		super.setClazz(LeaveRequest.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<LeaveRequest> findAll(String comId) {
		List<LeaveRequest> list = super.entityManager
				.createQuery("from LeaveRequest where company.id=:comId")
				.setParameter("comId", comId)
				.getResultList();

		if (list.size() == 0) {
			return new ArrayList<LeaveRequest>();
		} else {
			return list;
		}
	}
	
	@SuppressWarnings("unchecked")
	public LeaveRequest findByBk(String comId, String empId, String leaveId) {
		List<LeaveRequest> list = super.entityManager
				.createQuery("from LeaveRequest where company.id=:comId and employee.id=:empId and leave.id=:leaveId ")
				.setParameter("comId", comId)
				.setParameter("empId", empId)
				.setParameter("leaveId", leaveId).getResultList();

		if (list.size() == 0) {
			return new LeaveRequest();
		} else {
			return (LeaveRequest) list.get(0);
		}
	}

	public boolean isBkExist(String comId, String empId, String leaveId) {
		if (findByBk(comId, empId, leaveId).getId() == null) {
			return false;
		} else {
			return true;
		}
	}
}
