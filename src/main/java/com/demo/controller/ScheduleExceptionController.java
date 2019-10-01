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

import com.demo.model.ScheduleException;
import com.demo.service.ScheduleExceptionService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@RequestMapping({ "/api/v1" })
public class ScheduleExceptionController {

	@Autowired
	private ScheduleExceptionService scheduleExceptionService;

	@GetMapping(value = "/schedule-exceptions")
	@Transactional
	public ResponseEntity<?> getTimeGroupSchedules() {
		List<ScheduleException> scheduleException = scheduleExceptionService.findAll();
		return ResponseEntity.ok(scheduleException);
	}

	@GetMapping(value = "/schedule-exception/{id}")
	@Transactional
	public ResponseEntity<?> getTimeGroupScheduleById(@PathVariable String id) {
		ScheduleException scheduleException = scheduleExceptionService.findById(id);
		return ResponseEntity.ok(scheduleException);
	}

	@PostMapping(value = "/schedule-exception")
	@Transactional
	public ResponseEntity<?> submit(@RequestBody ScheduleException scheduleException) throws Exception {
		try {
			scheduleExceptionService.save(scheduleException);
			return ResponseEntity.ok(HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} 
	}
	
	@PutMapping(value = "/schedule-exception")
	@Transactional
	public ResponseEntity<?> update(@RequestBody ScheduleException scheduleException) throws Exception {
		try {
			scheduleExceptionService.update(scheduleException);
			return ResponseEntity.ok(HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Update Failed!");
		}
	}
	
	@DeleteMapping(value = "/schedule-exception/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable String id) throws Exception {
		try {
			scheduleExceptionService.delete(id);
			return ResponseEntity.ok(HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Delete Failed!");
		}
	}
}
