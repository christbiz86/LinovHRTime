package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.AbsenceMachineDao;
import com.demo.model.AbsenceMachine;

@Service
public class AbsenceMachineService {

	@Autowired
	private AbsenceMachineDao absenceMachineDao;
	
	public List<AbsenceMachine> findAll(){
        return absenceMachineDao.findAll();
    }
	
	public List<AbsenceMachine> findAll(Integer offset,Integer limit){
        return absenceMachineDao.findAll(offset, limit);
    }
	
	public AbsenceMachine findById(String id){
        return absenceMachineDao.findOne(id);
    }
		
	public void save(AbsenceMachine absenceMachine) throws Exception {
    	valBkNotNull(absenceMachine);
		valBkNotExist(absenceMachine);
		valNonBk(absenceMachine);
		absenceMachineDao.create(absenceMachine);
	}
	
	public void update(AbsenceMachine absenceMachine) throws Exception {
		valIdNotNull(absenceMachine);
		valIdExist(absenceMachine.getId());
		valBkNotNull(absenceMachine);
		valBkNotChange(absenceMachine);
		valNonBk(absenceMachine);
		absenceMachineDao.update(absenceMachine);
	}
	
	public void delete(String id) throws Exception {
		valIdExist(id);
		absenceMachineDao.deleteById(id);
	}
	
	private void valIdExist(String id)throws Exception{
		if(!absenceMachineDao.isIdExist(id)) {
			throw new Exception("Data tidak ada");
		}
	}
	
	private void valIdNotNull(AbsenceMachine absenceMachine)throws Exception {
		if(absenceMachine.getId().isEmpty()) {
			throw new Exception("Id tidak boleh kosong");
		}
	}
	
	private void valNonBk(AbsenceMachine absenceMachine)throws Exception{
		if(absenceMachine.getMachineName().isEmpty()) {
			throw new Exception("Machine Name cannot be empty");
		}
	}
	
	private void valBkNotExist(AbsenceMachine absenceMachine)throws Exception{
		if(absenceMachineDao.isBkExist(absenceMachine)) {
			throw new Exception("Data sudah ada");
		}
	}	
	
	private void valBkNotChange(AbsenceMachine absenceMachine)throws Exception{
		AbsenceMachine tempAbsenceMachine=findById(absenceMachine.getId());

		if(!tempAbsenceMachine.getCompany().getId().equals(absenceMachine.getCompany().getId())
				|| !tempAbsenceMachine.getMachineId().equals(absenceMachine.getMachineId())) {
			throw new Exception("BK tidak boleh berubah");
		}
	}
	
	private void valBkNotNull(AbsenceMachine absenceMachine) throws Exception{
		if(absenceMachine.getCompany().getId().isEmpty() 
				|| absenceMachine.getMachineId().isEmpty()) {
			throw new Exception("Bk tidak boleh kosong");
		}
	}
}
