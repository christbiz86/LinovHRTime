package com.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.model.EmployeeQuota;

@Repository
public class EmployeeQuotaDao extends AbstractJpaDao<EmployeeQuota>{
	
	public EmployeeQuotaDao() {
        setClazz(EmployeeQuota.class);
    }
	
	@SuppressWarnings("unchecked")
	public EmployeeQuota findByBk(String employee, String leave) {
		List<EmployeeQuota> list = super.entityManager
                .createQuery("FROM EmployeeQuota WHERE employee.id = :employee AND leave.id = :leave")
                .setParameter("employee", employee)
                .setParameter("leave", leave)
                .getResultList();

        if (list.size() == 0) {
            return new EmployeeQuota();
        }
        else {
            return list.get(0);
        }
    }

    public boolean isBkExist(String employee, String leave) {
        if(findByBk(employee, leave).getId() == null) {
            return false;
        }else {
            return true;
        }
    }

}
