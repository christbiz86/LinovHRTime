package com.demo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.demo.dao.LeaveRequestDao;
import com.demo.dao.LeaveRequestDetailDao;
import com.demo.model.LeaveRequest;
import com.demo.model.LeaveRequestDetail;
import com.demo.model.LoginSession;

@Service
public class LeaveRequestService {

	@Autowired
	private LeaveRequestDao leaveRequestDao;

	@Autowired
	private LeaveRequestDetailDao leaveRequestDetailDao;

	public void valIdExist(String id) throws Exception {
		if (!leaveRequestDao.isIdExist(id)) {
			throw new Exception("Data does not exist");
		}
	}

	public void valIdNotNull(String id) throws Exception {
		if (id.isEmpty()) {
			throw new Exception("Id cannot be emptied");
		}
	}

	public void valNonBk(LeaveRequest leaveRequest) throws Exception {
		if (leaveRequest.getStatus().trim().isEmpty()) {
			throw new Exception("status cannot be emptied");
		}
	}

	public void valBkNotExist(LeaveRequest leaveRequest) throws Exception {
		if (leaveRequestDao.isBkExist(leaveRequest.getCompany().getId(), leaveRequest.getEmployee().getId(), leaveRequest.getLeave().getId())) {
			throw new Exception("Data already exist");
		}
	}

	public void valBkNotChange(LeaveRequest leaveRequest) throws Exception {
		String company = findById(leaveRequest.getId()).getCompany().getId();
		String employee = findById(leaveRequest.getId()).getEmployee().getId();
		String leave = findById(leaveRequest.getId()).getLeave().getId();

		if (!leaveRequest.getCompany().getId().equals(company) || !leaveRequest.getEmployee().getId().equals(employee) || !leaveRequest.getLeave().getId().equals(leave)) {
			throw new Exception("company or employee or leave cannot be changed");
		}
	}

	public void valBkNotNull(LeaveRequest leaveRequest) throws Exception {
		if (leaveRequest.getCompany() == null) {
			if (leaveRequest.getCompany().getId().trim().isEmpty()) {
				throw new Exception("company cannot be emptied");
			}
		}
		if (leaveRequest.getEmployee() == null) {
			if (leaveRequest.getEmployee().getId().trim().isEmpty()) {
				throw new Exception("employee cannot be emptied");
			}
		}
		if (leaveRequest.getLeave() == null) {
			if (leaveRequest.getLeave().getId().trim().isEmpty()) {
				throw new Exception("leave cannot be emptied");
			}
		}
	}

	public List<LeaveRequest> findAll() {
		return leaveRequestDao.findAll(((LoginSession)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getSelectedCompanyId());
	}

	public LeaveRequest findById(String id) {
		return leaveRequestDao.findOne(id);
	}

	public void saveHeader(LeaveRequest leaveRequest) throws Exception {
		valBkNotNull(leaveRequest);
		valBkNotExist(leaveRequest);
		valNonBk(leaveRequest);

		leaveRequestDao.create(leaveRequest);
	}

	public void update(LeaveRequest leaveRequest) throws Exception {
		valIdNotNull(leaveRequest.getId());
		valIdExist(leaveRequest.getId());
		valBkNotNull(leaveRequest);
		valBkNotChange(leaveRequest);
		valNonBk(leaveRequest);

		leaveRequestDao.update(leaveRequest);
	}

	public void delete(String id) throws Exception {
		valIdExist(id);
		leaveRequestDao.deleteById(id);
	}

	public void valDetIdExist(String id) throws Exception {
		if (!leaveRequestDetailDao.isIdExist(id)) {
			throw new Exception("Data does not exist");
		}
	}

	public void valDetIdNotNull(String id) throws Exception {
		if (id.isEmpty()) {
			throw new Exception("Id cannot be emptied");
		}
	}

	public void valDetNonBk(LeaveRequestDetail leaveRequestDetail) throws Exception {
		if (leaveRequestDetail.getWeight() == null) {
			throw new Exception("weight cannot be emptied");
		}
	}

	public void valDetBkNotExist(LeaveRequestDetail leaveRequestDetail) throws Exception {
		if (leaveRequestDetailDao.isBkExist(leaveRequestDetail.getLeaveRequest().getId(), leaveRequestDetail.getDate())) {
			throw new Exception("Data already exist");
		}
	}

	public void valDetBkNotChange(LeaveRequestDetail leaveRequestDetail) throws Exception {
		String leaveRequest = findDetById(leaveRequestDetail.getId()).getLeaveRequest().getId();
		Date date = findDetById(leaveRequestDetail.getId()).getDate();

		if (!leaveRequestDetail.getLeaveRequest().getId().equals(leaveRequest)
				|| !leaveRequestDetail.getDate().equals(date)) {
			throw new Exception("leave request or date cannot be changed");
		}
	}

	public void valDetBkNotNull(LeaveRequestDetail leaveRequestDetail) throws Exception {
		if (leaveRequestDetail.getLeaveRequest() == null) {
			if (leaveRequestDetail.getLeaveRequest().getId().isEmpty()) {
				throw new Exception("leave request cannot be emptied");
			}
		}
		if (leaveRequestDetail.getDate() == null) {
			throw new Exception("date cannot be emptied");
		}
	}

	public List<LeaveRequestDetail> findAllDet() {
		return leaveRequestDetailDao.findAll();
	}

	public LeaveRequestDetail findDetById(String id) {
		return leaveRequestDetailDao.findOne(id);
	}

	public void saveDet(LeaveRequestDetail leaveRequestDetail) throws Exception {
		valDetBkNotNull(leaveRequestDetail);
		valDetBkNotExist(leaveRequestDetail);
		valDetNonBk(leaveRequestDetail);

		leaveRequestDetailDao.create(leaveRequestDetail);
	}

	public void updateDet(LeaveRequestDetail leaveRequestDetail) throws Exception {
		valDetIdNotNull(leaveRequestDetail.getId());
		valDetIdExist(leaveRequestDetail.getId());
		valDetBkNotNull(leaveRequestDetail);
		valDetBkNotChange(leaveRequestDetail);
		valDetNonBk(leaveRequestDetail);

		leaveRequestDetailDao.update(leaveRequestDetail);
	}

	public void deleteDet(String id) throws Exception {
		valDetIdExist(id);
		leaveRequestDetailDao.deleteById(id);
	}

	public void saveHeaderDetail(LeaveRequest leaveRequest) throws Exception {
		valBkNotNull(leaveRequest);
		valBkNotExist(leaveRequest);
		valNonBk(leaveRequest);
		leaveRequestDao.create(leaveRequest);

		LeaveRequest leaveReqDB = leaveRequestDao.findByBk(leaveRequest.getCompany().getId(), leaveRequest.getEmployee().getId(), leaveRequest.getLeave().getId());
		List<LeaveRequestDetail> leaveReqDet = leaveRequest.getLeaveReqDet();

		for (LeaveRequestDetail leaveRequestDetail : leaveReqDet) {
			leaveRequestDetail.setLeaveRequest(leaveReqDB);
			saveDet(leaveRequestDetail);
		}
	}
}
