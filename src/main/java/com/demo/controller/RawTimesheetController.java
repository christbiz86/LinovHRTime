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

import com.demo.model.RawTimesheet;
import com.demo.service.RawTimesheetService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@RequestMapping({"/api/v1/time"})
public class RawTimesheetController {
	@Autowired
	private RawTimesheetService rawTimesheetService;
	
	@GetMapping(value = "/raw-timesheets")
	@Transactional
	public ResponseEntity<?> getAllRawTimes() {
		try {
			List<RawTimesheet> list = rawTimesheetService.findAll();
			return ResponseEntity.ok(list);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping(value = "/raw-timesheet/{id}")
	@Transactional
	public ResponseEntity<?> getLocationById(@PathVariable String id) {
		try {
			RawTimesheet rawTimesheet = rawTimesheetService.findById(id);
			return ResponseEntity.ok(rawTimesheet);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		
	}
	
	@PostMapping(value = "/raw-timesheet")
	@Transactional
	public ResponseEntity<?> submit(@RequestBody RawTimesheet rawTimesheet) throws Exception {
		try {
			rawTimesheetService.save(rawTimesheet);
			return ResponseEntity.ok("Data Have Successfully Saved");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PutMapping(value = "/raw-timesheet")
	@Transactional
	public ResponseEntity<?> update(@RequestBody RawTimesheet rawTimesheet) throws Exception {
		try {
			rawTimesheetService.update(rawTimesheet);
			return ResponseEntity.ok("Data Have Successfully Updated");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@DeleteMapping(value = "/raw-timesheet/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable String id) throws Exception {
		try {
			rawTimesheetService.delete(id);
			return ResponseEntity.ok("Data Have Successfully Deleted");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
}
