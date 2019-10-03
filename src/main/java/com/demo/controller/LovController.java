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

import com.demo.combo.EventTypeComboBean;
import com.demo.combo.LeaveComboBean;
import com.demo.combo.LookUpComboBean;
import com.demo.combo.PermitTypeComboBean;
import com.demo.combo.TimeDefinitionComboBean;
import com.demo.model.Leave;
import com.demo.model.Lov;
import com.demo.model.TimeDefinition;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@Transactional
@RequestMapping({ "/api/v1/lov" })
public class LovController {
	
	@Autowired
	private TimeDefinitionComboBean tdComboBean;
	
	@Autowired
	private EventTypeComboBean etComboBean;
	
	@Autowired
	private LookUpComboBean luComboBean;

    @Autowired
    private PermitTypeComboBean ptComboBean;
    
    @Autowired
    private LeaveComboBean leaveComboBean;
	
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
	
	@GetMapping(value = "/event-types")
    @Transactional
    public ResponseEntity<?> getAllEventTypes(){
        try {
        	List<Lov> etList = etComboBean.getList();
            return new ResponseEntity<List<Lov>>(etList,HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@GetMapping(value = "/look-ups")
    @Transactional
    public ResponseEntity<?> getAllLookUps(){
        try {
        	List<Lov> luList = luComboBean.getList();
            return new ResponseEntity<List<Lov>>(luList,HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@GetMapping(value = "/permit-types")
    @Transactional
    public ResponseEntity<?> getAllPermitTypes(){
        try {
        	List<Lov> ptList = ptComboBean.getList();
            return new ResponseEntity<List<Lov>>(ptList,HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@GetMapping(value = "/leaves")
    @Transactional
    public ResponseEntity<?> getAllLeaves(){
        try {
        	List<Leave> leaveList = leaveComboBean.getList();
            return new ResponseEntity<List<Leave>>(leaveList,HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

}
