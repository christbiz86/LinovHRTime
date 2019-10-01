package com.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.model.LogSchedulePattern;

@Repository
public class LogSchedulePatternDao extends AbstractJpaDao<LogSchedulePattern>{

	public LogSchedulePatternDao() {
		setClazz(LogSchedulePattern.class);
	}
	
	@SuppressWarnings("unchecked")
    public List<LogSchedulePattern> findAll(Integer offset, Integer limit){
        return super.entityManager.createQuery("FROM LogSchedulePattern")
                .setFirstResult(offset).setMaxResults(limit).getResultList();
    }
		
	@SuppressWarnings("unchecked")
    public LogSchedulePattern findByBk(LogSchedulePattern logSchedulePattern){
		List<LogSchedulePattern>list= super.entityManager.createQuery("FROM LogSchedulePattern WHERE logSchedule.id=:logScheduleId AND sequence=:sequence  ")
                .setParameter("logScheduleId", logSchedulePattern.getLogSchedule().getId())
                .setParameter("sequence", logSchedulePattern.getSequence())
                .getResultList();
        
        if (list.size() == 0) {
			return new LogSchedulePattern();
		}
		else {
			return (LogSchedulePattern)list.get(0);
		}
    }
		
	public boolean isBkExist(LogSchedulePattern logSchedulePattern) {
		
		if(findByBk(logSchedulePattern).getId()==null) {
			return false;
		}else {
			return true;
		}	 
	}
}