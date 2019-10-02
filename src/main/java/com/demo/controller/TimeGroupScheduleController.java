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

import com.demo.model.TimeGroupSchedule;
import com.demo.service.TimeGroupScheduleService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@RequestMapping({ "/api/v1/time" })
public class TimeGroupScheduleController {

	@Autowired
	private TimeGroupScheduleService timeGroupScheduleService;

	@GetMapping(value = "/time-group/schedules")
	@Transactional
	public ResponseEntity<?> getTimeGroupSchedules() {
		List<TimeGroupSchedule> timeGroupSchedule = timeGroupScheduleService.findAll();
		return ResponseEntity.ok(timeGroupSchedule);
	}

	@GetMapping(value = "/time-group/schedule/{id}")
	@Transactional
	public ResponseEntity<?> getTimeGroupScheduleById(@PathVariable String id) {
		TimeGroupSchedule timeGroupSchedule = timeGroupScheduleService.findById(id);
		return ResponseEntity.ok(timeGroupSchedule);
	}

	@PostMapping(value = "/time-group/schedule")
	@Transactional
	public ResponseEntity<?> submit(@RequestBody TimeGroupSchedule timeGroupSchedule) throws Exception {
		try {
			timeGroupScheduleService.save(timeGroupSchedule);
			return ResponseEntity.ok(HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} 
	}
	
	@PutMapping(value = "/time-group/schedule")
	@Transactional
	public ResponseEntity<?> update(@RequestBody TimeGroupSchedule timeGroupSchedule) throws Exception {
		try {
			timeGroupScheduleService.update(timeGroupSchedule);
			return ResponseEntity.ok(HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Update Failed!");
		}
	}
	
	@DeleteMapping(value = "/time-group/schedule/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable String id) throws Exception {
		try {
			timeGroupScheduleService.delete(id);
			return ResponseEntity.ok(HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Delete Failed!");
		}
	}
}
