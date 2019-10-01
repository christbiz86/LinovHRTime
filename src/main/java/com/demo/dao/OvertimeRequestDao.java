package com.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.model.OvertimeRequest;

@Repository
public class OvertimeRequestDao extends AbstractJpaDao<OvertimeRequest>{

	public OvertimeRequestDao() {
		setClazz(OvertimeRequest.class);
	}
		
	@SuppressWarnings("unchecked")
    public OvertimeRequest findByBk(OvertimeRequest overtimeRequest){
		List<OvertimeRequest>list= super.entityManager.createQuery("FROM OvertimeRequest WHERE company.id=:companyId AND scheduleDate=:scheduleDate ")
                .setParameter("companyId", overtimeRequest.getCompany().getId())
                .setParameter("scheduleDate", overtimeRequest.getScheduleDate())
                .getResultList();
        
        if (list.size() == 0) {
			return new OvertimeRequest();
		}
		else {
			return (OvertimeRequest)list.get(0);
		}
    }
		
	public boolean isBkExist(OvertimeRequest overtimeRequest) {
		
		if(findByBk(overtimeRequest).getId()==null) {
			return false;
		}else {
			return true;
		}	 
	}	
}