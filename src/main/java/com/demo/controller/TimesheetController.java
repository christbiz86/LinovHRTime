package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.Timesheet;
import com.demo.service.TimesheetService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@RequestMapping({ "/api/v1/time" })
public class TimesheetController {

	@Autowired
	private TimesheetService timesheetService;

	@GetMapping(value = "/timesheets")
	@Transactional
	public ResponseEntity<?> getTimesheets() {
		List<Timesheet> timesheet = timesheetService.findAll();
		return ResponseEntity.ok(timesheet);
	}

	@GetMapping(value = "/timesheet/{id}")
	@Transactional
	public ResponseEntity<?> getTimesheetById(@PathVariable String id) {
		Timesheet timesheet = timesheetService.findById(id);
		return ResponseEntity.ok(timesheet);
	}

	@PostMapping(value = "/timesheet")
	@Transactional
	public ResponseEntity<?> submit(@RequestBody Timesheet timesheet) throws Exception {
		try {
			timesheetService.save(timesheet);
			return ResponseEntity.ok(HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} 
	}
	
	@PutMapping(value = "/timesheet")
	@Transactional
	public ResponseEntity<?> update(@RequestBody Timesheet timesheet) throws Exception {
		try {
			timesheetService.update(timesheet);
			return ResponseEntity.ok(HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Update Failed!");
		}
	}
	
	@DeleteMapping(value = "/timesheet/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable String id) throws Exception {
		try {
			timesheetService.delete(id);
			return ResponseEntity.ok(HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Delete Failed!");
		}
	}
}
