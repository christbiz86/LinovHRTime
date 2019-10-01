package com.demo.dao;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.model.ScheduleException;

@Repository
public class ScheduleExceptionDao extends AbstractJpaDao<ScheduleException> {

	public ScheduleExceptionDao() {
		setClazz(ScheduleException.class);
	}
	
	@SuppressWarnings("unchecked")
	public ScheduleException findByBk(String leaveId, String empId, Date date) {
		List<ScheduleException> list= super.entityManager.createQuery("FROM ScheduleException WHERE leave.id=:leaveId AND employee.id=:empId date=:date ")
				.setParameter("leaveId", leaveId)
				.setParameter("empId", empId)
				.setParameter("date", date)
				.getResultList();
		if (list.size() == 0) {
			return new ScheduleException();
		}
		else {
			return (ScheduleException)list.get(0);
		}
	}
	
	public boolean isBkExist(String leaveId, String empId, Date date) {
		if(findByBk(leaveId, empId, date) == null) {
			return false;
		} else {
			return true;
		}
	}
}
