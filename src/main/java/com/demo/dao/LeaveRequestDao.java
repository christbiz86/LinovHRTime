package com.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.model.LeaveRequest;

@Repository
public class LeaveRequestDao extends AbstractJpaDao<LeaveRequest> {

	public LeaveRequestDao() {
		super.setClazz(LeaveRequest.class);
	}
	
	@SuppressWarnings("unchecked")
	public LeaveRequest findByBk(String comId, String empId, String leaveId) {
		List<LeaveRequest> ratingScale = super.entityManager
				.createQuery("from LeaveRequest where company.id=:comId and employee.id=:empId and leave.id=:leaveId ")
				.setParameter("comId", comId)
				.setParameter("empId", empId)
				.setParameter("leaveId", leaveId).getResultList();

		if (ratingScale.size() == 0) {
			return new LeaveRequest();
		} else {
			return (LeaveRequest) ratingScale.get(0);
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
