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

import com.demo.combo.TimeDefinitionComboBean;
import com.demo.model.TimeDefinition;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@Transactional
@RequestMapping({ "/api/v1/lov" })
public class LovController {
	
	@Autowired
	private TimeDefinitionComboBean tdComboBean;
	
	@GetMapping(value = "/time-definitions")
    @Transactional
    public ResponseEntity<?> getAllTimeDefinitions(){
        try {
        	List<TimeDefinition> tdList = tdComboBean.getList();
            return new ResponseEntity<List<TimeDefinition>>(tdList,HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

}
