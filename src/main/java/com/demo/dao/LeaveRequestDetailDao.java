package com.demo.dao;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.demo.model.LeaveRequestDetail;

@Repository
public class LeaveRequestDetailDao extends AbstractJpaDao<LeaveRequestDetail> {

	public LeaveRequestDetailDao() {
		super.setClazz(LeaveRequestDetail.class);
	}
	
	@SuppressWarnings("unchecked")
	public LeaveRequestDetail findByBk(String leaveReqId, Date date) {
		List<LeaveRequestDetail> list = super.entityManager
				.createQuery("from LeaveRequestDetail where leaveRequest.id=:leaveReqId and date=:date ")
				.setParameter("leaveReqId", leaveReqId).setParameter("date", date).getResultList();

		if (list.size() == 0) {
			return new LeaveRequestDetail();
		} else {
			return (LeaveRequestDetail) list.get(0);
		}
	}

	public boolean isBkExist(String leaveReqId, Date date) {
		if (findByBk(leaveReqId, date).getId() == null) {
			return false;
		} else {
			return true;
		}
	}
}
