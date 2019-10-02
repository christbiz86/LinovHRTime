package com.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.model.LeaveEligibilities;

@Repository
public class LeaveEligibilitiesDao extends AbstractJpaDao<LeaveEligibilities>{
	public LeaveEligibilitiesDao() {
		setClazz(LeaveEligibilities.class);
	}
	
	@SuppressWarnings("unchecked")
	public LeaveEligibilities findByBk(String companyId, String leaveId) {
		List<LeaveEligibilities> list = super.entityManager.createQuery("FROM EventEligibilities WHERE company.id=:companyId"
				+ " AND leave.id=:leaveId").setParameter("companyId", companyId)
				.setParameter("leaveId", leaveId).getResultList();
		
		if(list.size() == 0) {
			return new LeaveEligibilities();
		} else {
			return list.get(0);
		}
	}
	
	public boolean isBkExist(String companyId, String leaveId) {
		if(findByBk(companyId, leaveId).getId() == null) {
			return false;
		} else {
			return true;
		}
	}
}
