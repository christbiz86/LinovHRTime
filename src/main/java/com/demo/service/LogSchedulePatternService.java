package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.LogSchedulePatternDao;
import com.demo.model.LogSchedulePattern;

@Service
public class LogSchedulePatternService {

	@Autowired
	private LogSchedulePatternDao logSchedulePatternDao;
	
	public List<LogSchedulePattern> findAll(){
        return logSchedulePatternDao.findAll();
    }
	
	public List<LogSchedulePattern> findAll(Integer offset,Integer limit){
        return logSchedulePatternDao.findAll(offset, limit);
    }
	
	public LogSchedulePattern findById(String id){
        return logSchedulePatternDao.findOne(id);
    }
		
	public void save(LogSchedulePattern logSchedulePattern) throws Exception {
    	valBkNotNull(logSchedulePattern);
		valBkNotExist(logSchedulePattern);
		valNonBk(logSchedulePattern);
		logSchedulePatternDao.create(logSchedulePattern);
	}
	
	public void update(LogSchedulePattern logSchedulePattern) throws Exception {
		valIdNotNull(logSchedulePattern);
		valIdExist(logSchedulePattern.getId());
		valBkNotNull(logSchedulePattern);
		valBkNotChange(logSchedulePattern);
		valNonBk(logSchedulePattern);
		logSchedulePatternDao.update(logSchedulePattern);
	}
	
	public void delete(String id) throws Exception {
		valIdExist(id);
		logSchedulePatternDao.deleteById(id);
	}
	
	private void valIdExist(String id)throws Exception{
		if(!logSchedulePatternDao.isIdExist(id)) {
			throw new Exception("Data tidak ada");
		}
	}
	
	private void valIdNotNull(LogSchedulePattern logSchedulePattern)throws Exception {
		if(logSchedulePattern.getId().isEmpty()) {
			throw new Exception("Id tidak boleh kosong");
		}
	}
	
	private void valNonBk(LogSchedulePattern logSchedulePattern)throws Exception{
		
	}
	
	private void valBkNotExist(LogSchedulePattern logSchedulePattern)throws Exception{
		if(logSchedulePatternDao.isBkExist(logSchedulePattern)) {
			throw new Exception("Data sudah ada");
		}
	}	
	
	private void valBkNotChange(LogSchedulePattern logSchedulePattern)throws Exception{
		LogSchedulePattern tempLogSchedulePattern=findById(logSchedulePattern.getId());

		if(!tempLogSchedulePattern.getLogSchedule().getId().equals(logSchedulePattern.getLogSchedule().getId())
				|| !tempLogSchedulePattern.getSequence().equals(logSchedulePattern.getSequence())) {
			throw new Exception("BK tidak boleh berubah");
		}
	}
	
	private void valBkNotNull(LogSchedulePattern logSchedulePattern) throws Exception{
		if(logSchedulePattern.getLogSchedule().getId().isEmpty() 
				|| logSchedulePattern.getSequence()==null) {
			throw new Exception("Bk tidak boleh kosong");
		}
	}
}
