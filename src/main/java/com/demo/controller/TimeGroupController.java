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

import com.demo.model.TimeGroup;
import com.demo.service.TimeGroupService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@RequestMapping({ "/api/v1/time" })
public class TimeGroupController {

	@Autowired
	private TimeGroupService timeGroupService;

	@GetMapping(value = "/time-groups")
	@Transactional
	public ResponseEntity<?> getTimeGroups() {
		List<TimeGroup> timeGroup = timeGroupService.findAll();
		return ResponseEntity.ok(timeGroup);
	}

	@GetMapping(value = "/time-group/{id}")
	@Transactional
	public ResponseEntity<?> getTimeGroupById(@PathVariable String id) {
		TimeGroup timeGroup = timeGroupService.findById(id);
		return ResponseEntity.ok(timeGroup);
	}

	@PostMapping(value = "/time-group")
	@Transactional
	public ResponseEntity<?> submit(@RequestBody TimeGroup timeGroup) throws Exception {
		try {
			timeGroupService.save(timeGroup);
			return ResponseEntity.ok(HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} 
	}
	
	@PutMapping(value = "/time-group")
	@Transactional
	public ResponseEntity<?> update(@RequestBody TimeGroup timeGroup) throws Exception {
		try {
			timeGroupService.update(timeGroup);
			return ResponseEntity.ok(HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Update Failed!");
		}
	}
	
	@DeleteMapping(value = "/time-group/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable String id) throws Exception {
		try {
			timeGroupService.delete(id);
			return ResponseEntity.ok(HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Delete Failed!");
		}
	}
}
