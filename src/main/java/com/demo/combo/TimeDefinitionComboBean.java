package com.demo.combo;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.TimeDefinitionDao;
import com.demo.model.TimeDefinition;

@Service
public class TimeDefinitionComboBean {
	
	@Autowired
	private TimeDefinitionDao tdDao;
	
	List<TimeDefinition> list = new ArrayList<TimeDefinition>(); 
	
	@PostConstruct
	private List<TimeDefinition> init() {
		return list = tdDao.findAll();
	}

	public List<TimeDefinition> getList() {
		return list;
	}

}
