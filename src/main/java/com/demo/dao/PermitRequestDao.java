package com.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.model.PermitRequest;

@Repository
public class PermitRequestDao extends AbstractJpaDao<PermitRequest>{
	
	public PermitRequestDao() {
        setClazz(PermitRequest.class);
    }

    @SuppressWarnings("unchecked")
	public PermitRequest findByBk(String company, String lov_permit) {
		List<PermitRequest> list = super.entityManager
                .createQuery("FROM PermitRequest WHERE company.id = :company AND lovPermit.id = :lov_permit")
                .setParameter("company", company)
                .setParameter("lov_permit", lov_permit)
                .getResultList();

        if (list.size() == 0) {
            return new PermitRequest();
        }
        else {
            return list.get(0);
        }
    }

    public boolean isBkExist(String company, String lov_permit) {
        if(findByBk(company, lov_permit).getId() == null) {
            return false;
        }else {
            return true;
        }
    }

}
