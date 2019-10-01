package com.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.model.AbsenceMachine;

@Repository
public class AbsenceMachineDao extends AbstractJpaDao<AbsenceMachine>{

	public AbsenceMachineDao() {
		setClazz(AbsenceMachine.class);
	}
	
	@SuppressWarnings("unchecked")
    public List<AbsenceMachine> findAll(Integer offset, Integer limit){
        return super.entityManager.createQuery("FROM AbsenceMachine")
                .setFirstResult(offset).setMaxResults(limit).getResultList();
    }
		
	@SuppressWarnings("unchecked")
    public AbsenceMachine findByBk(AbsenceMachine absenceMachine){
		List<AbsenceMachine>list= super.entityManager.createQuery("FROM AbsenceMachine WHERE company.id=:companyId AND machineId=:machineId ")
                .setParameter("companyId", absenceMachine.getCompany().getId())
                .setParameter("machineId", absenceMachine.getMachineId())
                .getResultList();
        
        if (list.size() == 0) {
			return new AbsenceMachine();
		}
		else {
			return (AbsenceMachine)list.get(0);
		}
    }
		
	public boolean isBkExist(AbsenceMachine absenceMachine) {
		
		if(findByBk(absenceMachine).getId()==null) {
			return false;
		}else {
			return true;
		}	 
	}
}
