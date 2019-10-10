package com.demo.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.model.TimeGroup;

@Repository
public class TimeGroupDao extends AbstractJpaDao<TimeGroup> {
	
	public TimeGroupDao() {
		setClazz(TimeGroup.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<TimeGroup> findAll(String companyId) {
		List<TimeGroup> list= super.entityManager.createQuery("FROM TimeGroup WHERE company.id=:companyId")
				.setParameter("companyId", companyId)
				.getResultList();
		if (list.size() == 0) {
			return new ArrayList<TimeGroup>();
		}
		else {
			return list;
		}
	}

	@SuppressWarnings("unchecked")
	public TimeGroup findByBk(String companyId,String code) {
		List<TimeGroup> list= super.entityManager.createQuery("FROM TimeGroup WHERE company.id=:companyId AND code=:code ")
				.setParameter("companyId", companyId)
				.setParameter("code", code)
				.getResultList();
		if (list.size() == 0) {
			return new TimeGroup();
		}
		else {
			return (TimeGroup)list.get(0);
		}
	}
	
	public boolean isBkExist(String companyId,String code) {
		if(findByBk(companyId, code) == null) {
			return false;
		} else {
			return true;
		}
	}
}
