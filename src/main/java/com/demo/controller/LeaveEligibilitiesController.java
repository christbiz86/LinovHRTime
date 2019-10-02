package com.demo.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.LeaveEligibilities;
import com.demo.service.LeaveEligibilitiesService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@RequestMapping({"/api/v1/time"})
public class LeaveEligibilitiesController {
	@Autowired
	private LeaveEligibilitiesService eventEligibilitiesService;
	
	@GetMapping(value = "/leave-eligibilities")
	@Transactional
	public ResponseEntity<?> getAllLeaveEli() {
		try {
			List<LeaveEligibilities> list = eventEligibilitiesService.findAll();
			return ResponseEntity.ok(list);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping(value = "/leave-eligibility/{id}")
	@Transactional
	public ResponseEntity<?> getLeaveEliById(@PathVariable String id) {
		try {
			LeaveEligibilities location = eventEligibilitiesService.findById(id);
			return ResponseEntity.ok(location);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		
	}
	
	@PostMapping(value = "/leave-eligibility")
	@Transactional
	public ResponseEntity<?> submit(@RequestBody LeaveEligibilities leaveEligibilities) throws Exception {
		try {
			eventEligibilitiesService.save(leaveEligibilities);
			return ResponseEntity.ok("Data Have Successfully Saved");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PutMapping(value = "/leave-eligibility")
	@Transactional
	public ResponseEntity<?> update(@RequestBody LeaveEligibilities leaveEligibilities) throws Exception {
		try {
			eventEligibilitiesService.update(leaveEligibilities);
			return ResponseEntity.ok("Data Have Successfully Updated");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@DeleteMapping(value = "/leave-eligibility/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable String id) throws Exception {
		try {
			eventEligibilitiesService.delete(id);
			return ResponseEntity.ok("Data Have Successfully Deleted");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
}
