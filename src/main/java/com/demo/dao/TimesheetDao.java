package com.demo.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.model.Timesheet;

@Repository
public class TimesheetDao extends AbstractJpaDao<Timesheet> {

	public TimesheetDao() {
		setClazz(Timesheet.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Timesheet> findAll(String comId) {
		List<Timesheet> list= super.entityManager.createQuery("FROM Timesheet WHERE company.id=:comId")
				.setParameter("comId", comId)
				.getResultList();
		if (list.size() == 0) {
			return new ArrayList<Timesheet>();
		}
		else {
			return list;
		}
	}
	
	@SuppressWarnings("unchecked")
	public Timesheet findByBk(String comId, String empId, Date date) {
		List<Timesheet> list= super.entityManager.createQuery("FROM Timesheet WHERE company.id=:comId AND employee.id=:empId date=:date ")
				.setParameter("comId", comId)
				.setParameter("empId", empId)
				.setParameter("date", date)
				.getResultList();
		if (list.size() == 0) {
			return new Timesheet();
		}
		else {
			return (Timesheet)list.get(0);
		}
	}
	
	public boolean isBkExist(String comId, String empId, Date date) {
		if(findByBk(comId, empId, date) == null) {
			return false;
		} else {
			return true;
		}
	}
}
