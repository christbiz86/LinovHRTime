package com.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.model.TimeDefinition;

@Repository
public class TimeDefinitionDao extends AbstractJpaDao<TimeDefinition>{
	
	public TimeDefinitionDao() {
        setClazz(TimeDefinition.class);
    }

    @SuppressWarnings("unchecked")
    @Transactional
	public TimeDefinition findByCode(String code) {
		List<TimeDefinition> list = super.entityManager
                .createQuery("FROM TimeDefinition WHERE code = :code")
                .setParameter("code", code)
                .getResultList();
        if (list.size() == 0) {
            return new TimeDefinition();
        }
        else {
            return list.get(0);
        }
    }

    @SuppressWarnings("unchecked")
    @Transactional
	public TimeDefinition findByBk(String code, String company) {
		List<TimeDefinition> list = super.entityManager
                .createQuery("FROM TimeDefinition WHERE code = :code AND company.id = :company")
                .setParameter("code", code)
                .setParameter("company", company)
                .getResultList();

        if (list.size() == 0) {
            return new TimeDefinition();
        }
        else {
            return list.get(0);
        }
    }

    public boolean isCodeExist(String code) {
        if(findByCode(code).getId() == null) {
            return false;
        }else {
            return true;
        }
    }

    public boolean isBkExist(String code, String company) {
        if(findByBk(code, company).getId() == null) {
            return false;
        }else {
            return true;
        }
    }

}
