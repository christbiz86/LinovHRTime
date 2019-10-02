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

import com.demo.model.TimeAttribute;
import com.demo.service.TimeAttributeService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@RequestMapping({ "/api/v1/time" })
public class TimeAttributeController {

	@Autowired
	private TimeAttributeService timeAttributeService;

	@GetMapping(value = "/time-attributes")
	@Transactional
	public ResponseEntity<?> getTimeGroupSchedules() {
		List<TimeAttribute> timeAttribute = timeAttributeService.findAll();
		return ResponseEntity.ok(timeAttribute);
	}

	@GetMapping(value = "/time-attribute/{id}")
	@Transactional
	public ResponseEntity<?> getTimeGroupScheduleById(@PathVariable String id) {
		TimeAttribute timeAttribute = timeAttributeService.findById(id);
		return ResponseEntity.ok(timeAttribute);
	}

	@PostMapping(value = "/time-attribute")
	@Transactional
	public ResponseEntity<?> submit(@RequestBody TimeAttribute timeAttribute) throws Exception {
		try {
			timeAttributeService.save(timeAttribute);
			return ResponseEntity.ok(HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} 
	}
	
	@PutMapping(value = "/time-attribute")
	@Transactional
	public ResponseEntity<?> update(@RequestBody TimeAttribute timeAttribute) throws Exception {
		try {
			timeAttributeService.update(timeAttribute);
			return ResponseEntity.ok(HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Update Failed!");
		}
	}
	
	@DeleteMapping(value = "/time-attribute/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable String id) throws Exception {
		try {
			timeAttributeService.delete(id);
			return ResponseEntity.ok(HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Delete Failed!");
		}
	}
}
