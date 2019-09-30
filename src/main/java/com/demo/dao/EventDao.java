package com.demo.dao;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.model.Event;

@Repository
public class EventDao extends AbstractJpaDao<Event>{
	public EventDao() {
		setClazz(Event.class);
	}
	
	@SuppressWarnings("unchecked")
	public Event findByBk(String name, String companyId, Timestamp eventStart) {
		List<Event> list = super.entityManager.createQuery("FROM Event WHERE name=:name AND company.id=:companyId "
				+ "AND eventStart=:eventStart").setParameter("name", name).setParameter("companyId", companyId)
				.setParameter("eventStart", eventStart).getResultList();
		
		if(list.size() == 0) {
			return new Event();
		} else {
			return list.get(0);
		}
	}
	
	public boolean isBkExist(String name, String companyId, Timestamp eventStart) {
		if(findByBk(name, companyId, eventStart).getId() == null) {
			return false;
		} else {
			return true;
		}
	}
}
