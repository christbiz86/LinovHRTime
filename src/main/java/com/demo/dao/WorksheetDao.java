package com.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.model.Worksheet;

@Repository
public class WorksheetDao extends AbstractJpaDao<Worksheet>{
	
	public WorksheetDao() {
		setClazz(Worksheet.class);
	}
	
	@SuppressWarnings("unchecked")
    public List<Worksheet> findAll(Integer offset, Integer limit){
        return super.entityManager.createQuery("FROM Worksheet")
                .setFirstResult(offset).setMaxResults(limit).getResultList();
    }
		
	@SuppressWarnings("unchecked")
    public Worksheet findByBk(Worksheet worksheet){
		List<Worksheet>list= super.entityManager.createQuery("FROM Worksheet WHERE company.id=:companyId AND employee.id=:employeeId AND date=:date AND timeStart=:timeStart AND timeEnd=:timeEnd ")
                .setParameter("companyId", worksheet.getCompany().getId())
                .setParameter("employeeId", worksheet.getEmployee().getId())
                .setParameter("date", worksheet.getDate())
                .setParameter("timeStart", worksheet.getTimeStart())
                .setParameter("timeEnd", worksheet.getTimeEnd())
                .getResultList();
        
        if (list.size() == 0) {
			return new Worksheet();
		}
		else {
			return (Worksheet)list.get(0);
		}
    }
		
	public boolean isBkExist(Worksheet worksheet) {
		
		if(findByBk(worksheet).getId()==null) {
			return false;
		}else {
			return true;
		}	 
	}
}
