package com.demo.combo;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.TimeGroupDao;
import com.demo.model.TimeGroup;

@Service
public class TimeGroupComboBean {

	@Autowired
	private TimeGroupDao timeGroupDao;
	
	List<TimeGroup> list = new ArrayList<TimeGroup>();
	
	@PostConstruct
	private List<TimeGroup> init() {
		return list = timeGroupDao.findAll();
	}

	public List<TimeGroup> getList() {
		return list;
	}
}
