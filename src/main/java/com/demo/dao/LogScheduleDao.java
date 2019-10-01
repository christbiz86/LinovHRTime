package com.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.model.LogSchedule;

@Repository
public class LogScheduleDao extends AbstractJpaDao<LogSchedule>{

	public LogScheduleDao() {
		setClazz(LogSchedule.class);
	}
			
	@SuppressWarnings("unchecked")
    public LogSchedule findByBk(LogSchedule logSchedule){
		List<LogSchedule>list= super.entityManager.createQuery("FROM LogSchedule WHERE timeGroup.id=:timeGroupId AND dateStart=:dateStart AND dateEnd=:dateEnd ")
                .setParameter("timeGroupId", logSchedule.getTimeGroup().getId())
                .setParameter("dateStart", logSchedule.getDateStart())
                .setParameter("dateEnd", logSchedule.getDateEnd())
                .getResultList();
        
        if (list.size() == 0) {
			return new LogSchedule();
		}
		else {
			return (LogSchedule)list.get(0);
		}
    }
		
	public boolean isBkExist(LogSchedule logSchedule) {
		
		if(findByBk(logSchedule).getId()==null) {
			return false;
		}else {
			return true;
		}	 
	}
}
