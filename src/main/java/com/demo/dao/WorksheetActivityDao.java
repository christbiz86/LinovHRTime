package com.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.model.WorksheetActivity;

@Repository
public class WorksheetActivityDao extends AbstractJpaDao<WorksheetActivity>{

	public WorksheetActivityDao() {
		setClazz(WorksheetActivity.class);
	}
	
	@SuppressWarnings("unchecked")
    public List<WorksheetActivity> findAll(Integer offset, Integer limit){
        return super.entityManager.createQuery("FROM WorksheetActivity")
                .setFirstResult(offset).setMaxResults(limit).getResultList();
    }
		
	@SuppressWarnings("unchecked")
    public WorksheetActivity findByBk(WorksheetActivity worksheetActivity){
		List<WorksheetActivity>list= super.entityManager.createQuery("FROM WorksheetActivity WHERE company.id=:companyId AND code=:code ")
                .setParameter("companyId", worksheetActivity.getCompany().getId())
                .setParameter("code", worksheetActivity.getCode())
                .getResultList();
        
        if (list.size() == 0) {
			return new WorksheetActivity();
		}
		else {
			return (WorksheetActivity)list.get(0);
		}
    }
		
	public boolean isBkExist(WorksheetActivity worksheetActivity) {
		
		if(findByBk(worksheetActivity).getId()==null) {
			return false;
		}else {
			return true;
		}	 
	}
}