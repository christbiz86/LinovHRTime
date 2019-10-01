package com.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.model.Leave;

@Repository
public class LeaveDao extends AbstractJpaDao<Leave>{
	public LeaveDao() {
		setClazz(Leave.class);
	}
	
	@SuppressWarnings("unchecked")
	public Leave findByBk(String code, String companyId) {
		List<Leave> list = super.entityManager.createQuery("FROM Leave WHERE code=:code AND company.id=:companyId")
				.setParameter("code", code)
				.setParameter("companyId", companyId).getResultList();
		
		if(list.size() == 0) {
			return new Leave();
		} else {
			return list.get(0);
		}
	}
	
	public boolean isBkExist(String code, String companyId) {
		if(findByBk(code, companyId).getId() == null) {
			return false;
		} else {
			return true;
		}
	}
}
