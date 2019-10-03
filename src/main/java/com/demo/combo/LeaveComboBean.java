package com.demo.combo;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.LeaveDao;
import com.demo.model.Leave;

@Service
public class LeaveComboBean {
	
	@Autowired
	private LeaveDao leaveDao;
	
	List<Leave> list = new ArrayList<Leave>();
	
	@PostConstruct
	private List<Leave> init() {
		return list = leaveDao.findAll();
	}

	public List<Leave> getList() {
		return list;
	}

}
