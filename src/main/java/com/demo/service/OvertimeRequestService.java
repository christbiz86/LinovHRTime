package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.OvertimeRequestDao;
import com.demo.model.OvertimeRequest;

@Service
public class OvertimeRequestService {

	@Autowired
	private OvertimeRequestDao overtimeRequestDao;
	
	public List<OvertimeRequest> findAll(){
        return overtimeRequestDao.findAll();
    }
	
	
	public OvertimeRequest findById(String id){
        return overtimeRequestDao.findOne(id);
    }
		
	public void save(OvertimeRequest overtimeRequest) throws Exception {
    	valBkNotNull(overtimeRequest);
		valBkNotExist(overtimeRequest);
		valNonBk(overtimeRequest);
		valStartNotBiggerEnd(overtimeRequest);
		overtimeRequestDao.create(overtimeRequest);
	}
	
	public void update(OvertimeRequest overtimeRequest) throws Exception {
		valIdNotNull(overtimeRequest);
		valIdExist(overtimeRequest.getId());
		valBkNotNull(overtimeRequest);
		valBkNotChange(overtimeRequest);
		valNonBk(overtimeRequest);
		valStartNotBiggerEnd(overtimeRequest);
		overtimeRequestDao.update(overtimeRequest);
	}
	
	public void delete(String id) throws Exception {
		valIdExist(id);
		overtimeRequestDao.deleteById(id);
	}
	
	private void valIdExist(String id)throws Exception{
		if(!overtimeRequestDao.isIdExist(id)) {
			throw new Exception("Data tidak ada");
		}
	}
	
	private void valIdNotNull(OvertimeRequest overtimeRequest)throws Exception {
		if(overtimeRequest.getId().isEmpty()) {
			throw new Exception("Id tidak boleh kosong");
		}
	}
	
	private void valNonBk(OvertimeRequest overtimeRequest)throws Exception{
		if(overtimeRequest.getStatus().isEmpty()) {
			throw new Exception("Status cannot be empty");
		}
		if(overtimeRequest.getTimeStart()==null) {
			throw new Exception("Time Start cannot be empty !");
		}				
	}
	
	private void valBkNotExist(OvertimeRequest overtimeRequest)throws Exception{
		if(overtimeRequestDao.isBkExist(overtimeRequest)) {
			throw new Exception("Data sudah ada");
		}
	}	
	
	private void valBkNotChange(OvertimeRequest overtimeRequest)throws Exception{
		OvertimeRequest tempOvertimeRequest=findById(overtimeRequest.getId());

		if(!tempOvertimeRequest.getCompany().getId().equals(overtimeRequest.getCompany().getId()) || tempOvertimeRequest.getScheduleDate().compareTo(overtimeRequest.getScheduleDate())!=0) {
			throw new Exception("BK tidak boleh berubah");
		}
	}
	
	private void valBkNotNull(OvertimeRequest overtimeRequest) throws Exception{
		if(overtimeRequest.getCompany().getId().isEmpty() || overtimeRequest.getScheduleDate()==null) {
			throw new Exception("Bk tidak boleh kosong");
		}
	}
	
	private void valStartNotBiggerEnd(OvertimeRequest overtimeRequest) throws Exception {
		if(overtimeRequest.getTimeStart().compareTo(overtimeRequest.getTimeEnd())>0 ) {
			throw new Exception("Time start lebih besar dari time end");
		}
	}
}
