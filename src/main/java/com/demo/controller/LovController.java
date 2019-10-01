package com.demo.controller;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.combo.TimeGroupComboBean;
import com.demo.model.TimeGroup;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@Transactional
@RequestMapping({"/api/v1/time/lov"})
public class LovController {
    
    @Autowired
    private TimeGroupComboBean timeGroupComboBean;
    
    @GetMapping(value = "/time-groups")
    @Transactional
    public ResponseEntity<?> getAllTimeGroups() {
    	try {
    		List<TimeGroup> tGroupList = timeGroupComboBean.getList();
        	return new ResponseEntity<List<TimeGroup>>(tGroupList, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}	
    }
}
