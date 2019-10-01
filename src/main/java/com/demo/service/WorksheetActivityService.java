package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.WorksheetActivityDao;
import com.demo.model.WorksheetActivity;

@Service
public class WorksheetActivityService {

	@Autowired
	private WorksheetActivityDao worksheetActivityDao;
	
	public List<WorksheetActivity> findAll(){
        return worksheetActivityDao.findAll();
    }
	
	public List<WorksheetActivity> findAll(Integer offset,Integer limit){
        return worksheetActivityDao.findAll(offset, limit);
    }
	
	public WorksheetActivity findById(String id){
        return worksheetActivityDao.findOne(id);
    }
	
	public void save(WorksheetActivity worksheetActivity) throws Exception {
    	valBkNotNull(worksheetActivity);
		valBkNotExist(worksheetActivity);
		valNonBk(worksheetActivity);
		worksheetActivityDao.create(worksheetActivity);
	}
	
	public void update(WorksheetActivity worksheetActivity) throws Exception {
		valIdNotNull(worksheetActivity);
		valIdExist(worksheetActivity.getId());
		valBkNotNull(worksheetActivity);
		valBkNotChange(worksheetActivity);
		valNonBk(worksheetActivity);
		worksheetActivityDao.update(worksheetActivity);
	}
	
	public void delete(String id) throws Exception {
		valIdExist(id);
		worksheetActivityDao.deleteById(id);
	}
	
	private void valIdExist(String id)throws Exception{
		if(!worksheetActivityDao.isIdExist(id)) {
			throw new Exception("Data tidak ada");
		}
	}
	
	private void valIdNotNull(WorksheetActivity worksheetActivity)throws Exception {
		if(worksheetActivity.getId().isEmpty()) {
			throw new Exception("Id tidak boleh kosong");
		}
	}
	
	private void valNonBk(WorksheetActivity worksheetActivity)throws Exception{
		if(worksheetActivity.getName().isEmpty()) {
			throw new Exception("Name cannot be empty");
		}			
	}
	
	private void valBkNotExist(WorksheetActivity worksheetActivity)throws Exception{
		if(worksheetActivityDao.isBkExist(worksheetActivity)) {
			throw new Exception("Data sudah ada");
		}
	}	
	
	private void valBkNotChange(WorksheetActivity worksheetActivity)throws Exception{
		WorksheetActivity tempWorksheetActivity=findById(worksheetActivity.getId());

		if(!tempWorksheetActivity.getCompany().getId().equals(worksheetActivity.getCompany().getId()) || !tempWorksheetActivity.getCode().equals(worksheetActivity.getCode())) {
			throw new Exception("BK tidak boleh berubah");
		}
	}
	
	private void valBkNotNull(WorksheetActivity worksheetActivity) throws Exception{
		if(worksheetActivity.getCompany().getId().isEmpty() || worksheetActivity.getCode().isEmpty()) {
			throw new Exception("Bk tidak boleh kosong");
		}
	}
}