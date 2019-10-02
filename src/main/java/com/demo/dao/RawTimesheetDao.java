package com.demo.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.model.RawTimesheet;

@Repository
public class RawTimesheetDao extends AbstractJpaDao<RawTimesheet> {
	public RawTimesheetDao() {
		setClazz(RawTimesheet.class);
	}
	
	@SuppressWarnings("unchecked")
	public RawTimesheet findByBk(Date date, Timestamp clockTime, String companyId, String employeeId) {
		List<RawTimesheet> list = super.entityManager.createQuery("FROM RawTimesheet WHERE date=:date AND company.id =: companyId"
				+ " AND clockTime=:clockTime AND employee.id=:employeeId")
				.setParameter("date", date).setParameter("clockTime", clockTime)
				.setParameter("companyId", companyId).setParameter("employeeId", employeeId)
				.getResultList();
		
		if(list.size() == 0) {
			return new RawTimesheet();
		} else {
			return list.get(0);
		}
	}
	
	public boolean isBkExist(Date date, Timestamp clockTime, String companyId, String employeeId) {
		if(findByBk(date, clockTime, companyId, employeeId).getId() == null) {
			return false;
		} else {
			return true;
		}
	}
}
