package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.WorksheetDao;
import com.demo.model.Worksheet;

@Service
public class WorksheetService {

	@Autowired
	private WorksheetDao worksheetDao;
	
	public List<Worksheet> findAll(){
        return worksheetDao.findAll();
    }
	
	public List<Worksheet> findAll(Integer offset,Integer limit){
        return worksheetDao.findAll(offset, limit);
    }
	
	public Worksheet findById(String id){
        return worksheetDao.findOne(id);
    }
		
	public void save(Worksheet worksheet) throws Exception {
    	valBkNotNull(worksheet);
		valBkNotExist(worksheet);
		valNonBk(worksheet);
		valStartNotBiggerEnd(worksheet);
		worksheetDao.create(worksheet);
	}
	
	public void update(Worksheet worksheet) throws Exception {
		valIdNotNull(worksheet);
		valIdExist(worksheet.getId());
		valBkNotNull(worksheet);
		valBkNotChange(worksheet);
		valNonBk(worksheet);
		worksheetDao.update(worksheet);
	}
	
	public void delete(String id) throws Exception {
		valIdExist(id);
		worksheetDao.deleteById(id);
	}
	
	private void valIdExist(String id)throws Exception{
		if(!worksheetDao.isIdExist(id)) {
			throw new Exception("Data tidak ada");
		}
	}
	
	private void valIdNotNull(Worksheet worksheet)throws Exception {
		if(worksheet.getId().isEmpty()) {
			throw new Exception("Id tidak boleh kosong");
		}
	}
	
	private void valNonBk(Worksheet worksheet)throws Exception{
		if(worksheet.getDescription().isEmpty()) {
			throw new Exception("Description1 cannot be empty");
		}
	}
	
	private void valBkNotExist(Worksheet worksheet)throws Exception{
		if(worksheetDao.isBkExist(worksheet)) {
			throw new Exception("Data sudah ada");
		}
	}	
	
	private void valBkNotChange(Worksheet worksheet)throws Exception{
		Worksheet tempWorksheet=findById(worksheet.getId());

		if(!tempWorksheet.getCompany().getId().equals(worksheet.getCompany().getId())
				|| !tempWorksheet.getEmployee().getId().equals(worksheet.getEmployee().getId())
				|| tempWorksheet.getDate().compareTo(worksheet.getDate())!=0
				|| !tempWorksheet.getTimeStart().equals(worksheet.getTimeStart())
				|| !tempWorksheet.getTimeEnd().equals(worksheet.getTimeEnd())) {
			throw new Exception("BK tidak boleh berubah");
		}
	}
	
	private void valBkNotNull(Worksheet worksheet) throws Exception{
		if(worksheet.getCompany().getId().isEmpty() 
				|| worksheet.getEmployee().getId().isEmpty() 
				|| worksheet.getDate()==null 
				|| worksheet.getTimeStart()==null 
				|| worksheet.getTimeEnd()==null) {
			throw new Exception("Bk tidak boleh kosong");
		}
	}
	
	private void valStartNotBiggerEnd(Worksheet worksheet) throws Exception {
		if(worksheet.getTimeStart().compareTo(worksheet.getTimeEnd())>0 ) {
			throw new Exception("Time start lebih besar dari time end");
		}
	}
}
