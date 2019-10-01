package com.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.model.TimeGroupSchedule;

@Repository
public class TimeGroupScheduleDao extends AbstractJpaDao<TimeGroupSchedule> {

	public TimeGroupScheduleDao() {
		setClazz(TimeGroupSchedule.class);
	}
	
	@SuppressWarnings("unchecked")
	public TimeGroupSchedule findByBk(String tGroupId,String code) {
		List<TimeGroupSchedule> list= super.entityManager.createQuery("FROM TimeGroupSchedule WHERE timeGroup.id=:tGroupId AND code=:code ")
				.setParameter("tGroupId", tGroupId)
				.setParameter("code", code)
				.getResultList();
		if (list.size() == 0) {
			return new TimeGroupSchedule();
		}
		else {
			return (TimeGroupSchedule)list.get(0);
		}
	}
	
	public boolean isBkExist(String tGroupId,String code) {
		if(findByBk(tGroupId, code) == null) {
			return false;
		} else {
			return true;
		}
	}
}
