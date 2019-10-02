package com.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.model.EventEligibilities;

@Repository
public class EventEligibilitiesDao extends AbstractJpaDao<EventEligibilities> {
	public EventEligibilitiesDao() {
		setClazz(EventEligibilities.class);
	}
	
	@SuppressWarnings("unchecked")
	public EventEligibilities findByBk(String companyId, String eventId) {
		List<EventEligibilities> list = super.entityManager.createQuery("FROM EventEligibilities WHERE company.id=:companyId"
				+ " AND event.id=:eventId").setParameter("companyId", companyId)
				.setParameter("eventId", eventId).getResultList();
		
		if(list.size() == 0) {
			return new EventEligibilities();
		} else {
			return list.get(0);
		}
	}
	
	public boolean isBkExist(String companyId, String eventId) {
		if(findByBk(companyId, eventId).getId() == null) {
			return false;
		} else {
			return true;
		}
	}
}
