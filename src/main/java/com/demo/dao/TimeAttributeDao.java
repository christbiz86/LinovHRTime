package com.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.model.TimeAttribute;

@Repository
public class TimeAttributeDao extends AbstractJpaDao<TimeAttribute> {
	
	public TimeAttributeDao() {
		setClazz(TimeAttribute.class);
	}
	
	@SuppressWarnings("unchecked")
	public TimeAttribute findByBk(String tGroupId,String empId) {
		List<TimeAttribute> list= super.entityManager.createQuery("FROM TimeAttribute WHERE timeGroup.id=:tGroupId AND employee.id=:empId ")
				.setParameter("tGroupId", tGroupId)
				.setParameter("empId", empId)
				.getResultList();
		if (list.size() == 0) {
			return new TimeAttribute();
		}
		else {
			return (TimeAttribute)list.get(0);
		}
	}
	
	public boolean isBkExist(String tGroupId,String empId) {
		if(findByBk(tGroupId, empId) == null) {
			return false;
		} else {
			return true;
		}
	}
}
