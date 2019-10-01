package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.LogScheduleDao;
import com.demo.model.LogSchedule;

@Service
public class LogScheduleService {

	@Autowired
	private LogScheduleDao logScheduleDao;
	
	public List<LogSchedule> findAll(){
        return logScheduleDao.findAll();
    }
		
	public LogSchedule findById(String id){
        return logScheduleDao.findOne(id);
    }
		
	public void save(LogSchedule logSchedule) throws Exception {
    	valBkNotNull(logSchedule);
		valBkNotExist(logSchedule);
		valStartNotBiggerEnd(logSchedule);
		valNonBk(logSchedule);
		logScheduleDao.create(logSchedule);
	}
	
	public void update(LogSchedule logSchedule) throws Exception {
		valIdNotNull(logSchedule);
		valIdExist(logSchedule.getId());
		valBkNotNull(logSchedule);
		valBkNotChange(logSchedule);
		valNonBk(logSchedule);
		logScheduleDao.update(logSchedule);
	}
	
	public void delete(String id) throws Exception {
		valIdExist(id);
		logScheduleDao.deleteById(id);
	}
	
	private void valIdExist(String id)throws Exception{
		if(!logScheduleDao.isIdExist(id)) {
			throw new Exception("Data tidak ada");
		}
	}
	
	private void valIdNotNull(LogSchedule logSchedule)throws Exception {
		if(logSchedule.getId().isEmpty()) {
			throw new Exception("Id tidak boleh kosong");
		}
	}
	
	private void valNonBk(LogSchedule logSchedule)throws Exception{
	}
	
	private void valBkNotExist(LogSchedule logSchedule)throws Exception{
		if(logScheduleDao.isBkExist(logSchedule)) {
			throw new Exception("Data sudah ada");
		}
	}	
	
	private void valBkNotChange(LogSchedule logSchedule)throws Exception{
		LogSchedule tempLogSchedule=findById(logSchedule.getId());

		if(!tempLogSchedule.getTimeGroup().getId().equals(logSchedule.getTimeGroup().getId())
				|| tempLogSchedule.getDateStart().compareTo(logSchedule.getDateStart())!=0
				|| tempLogSchedule.getDateEnd().compareTo(logSchedule.getDateEnd())!=0) {
			throw new Exception("BK tidak boleh berubah");
		}
	}
	
	private void valBkNotNull(LogSchedule logSchedule) throws Exception{
		if(logSchedule.getTimeGroup().getId().isEmpty() 
				|| logSchedule.getDateStart()==null 
				|| logSchedule.getDateEnd()==null) {
			throw new Exception("Bk tidak boleh kosong");
		}
	}
	
	private void valStartNotBiggerEnd(LogSchedule logSchedule) throws Exception {
		if(logSchedule.getDateStart().compareTo(logSchedule.getDateEnd())>0 ) {
			throw new Exception("Time start lebih besar dari time end");
		}
	}
}