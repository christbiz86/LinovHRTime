package com.demo.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.model.RequestRawTimesheet;

@Repository
public class RequestRawTimesheetDao extends AbstractJpaDao<RequestRawTimesheet> {
	
	public RequestRawTimesheetDao() {
        setClazz(RequestRawTimesheet.class);
    }
	
	@SuppressWarnings("unchecked")
	public RequestRawTimesheet findByBk(String company, String employee, Date date) {
		List<RequestRawTimesheet> list = super.entityManager
                .createQuery("FROM RequestRawTimesheet "
                		+ "WHERE company.id = :company AND employee.id = :employee AND date = :date")
                .setParameter("company", company)
                .setParameter("employee", employee)
                .setParameter("date", date)
                .getResultList();

        if (list.size() == 0) {
            return new RequestRawTimesheet();
        }
        else {
            return list.get(0);
        }
    }

    public boolean isBkExist(String company, String employee, Date date) {
        if(findByBk(company, employee, date).getId() == null) {
            return false;
        }else {
            return true;
        }
    }

}
