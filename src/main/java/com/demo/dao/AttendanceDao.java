package com.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.model.Attendance;


@Repository
public class AttendanceDao extends AbstractJpaDao<Attendance>{
	
	public AttendanceDao() {
        setClazz(Attendance.class);
    }

    @SuppressWarnings("unchecked")
    @Transactional
	public Attendance findByCode(String code) {
		List<Attendance> list = super.entityManager
                .createQuery("FROM Attendance WHERE code = :code")
                .setParameter("code", code)
                .getResultList();
        if (list.size() == 0) {
            return new Attendance();
        }
        else {
            return list.get(0);
        }
    }

    @SuppressWarnings("unchecked")
    @Transactional
	public Attendance findByBk(String code, String company) {
		List<Attendance> list = super.entityManager
                .createQuery("FROM Attendance WHERE code = :code AND company.id = :company")
                .setParameter("code", code)
                .setParameter("company", company)
                .getResultList();

        if (list.size() == 0) {
            return new Attendance();
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
