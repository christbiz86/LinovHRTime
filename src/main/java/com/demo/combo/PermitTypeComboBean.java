package com.demo.combo;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dao.LovDao;
import com.demo.model.Lov;

@Service
public class PermitTypeComboBean {
	
	@Autowired
	private LovDao lovDao;
	
	List<Lov> list = new ArrayList<Lov>();
	
	@PostConstruct
	private List<Lov> init() {
		return list = lovDao.findByType("PERMIT");
	}

	public List<Lov> getList() {
		return list;
	}

}
