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

import com.demo.model.EventEligibilities;
import com.demo.service.EventEligibilitiesService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@RequestMapping({"/api/v1/time"})
public class EventEligibilitiesController {
	@Autowired
	private EventEligibilitiesService eventEligibilitiesService;
	
	@GetMapping(value = "/event-eligibilities")
	@Transactional
	public ResponseEntity<?> findAll() {
		try {
			List<EventEligibilities> list = eventEligibilitiesService.findAll();
			return ResponseEntity.ok(list);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping(value = "/event-eligibility/{id}")
	@Transactional
	public ResponseEntity<?> getLocationById(@PathVariable String id) {
		try {
			EventEligibilities eventEligibilities = eventEligibilitiesService.findById(id);
			return ResponseEntity.ok(eventEligibilities);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		
	}
	
	@PostMapping(value = "/event-eligibility")
	@Transactional
	public ResponseEntity<?> submit(@RequestBody EventEligibilities eventEligibilities) throws Exception {
		try {
			eventEligibilitiesService.save(eventEligibilities);
			return ResponseEntity.ok("Data Have Successfully Saved");
		}  catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PutMapping(value = "/event-eligibility")
	@Transactional
	public ResponseEntity<?> update(@RequestBody EventEligibilities eventEligibilities) throws Exception {
		try {
			eventEligibilitiesService.update(eventEligibilities);
			return ResponseEntity.ok("Data Have Successfully Updated");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@DeleteMapping(value = "/event-eligibility/{id}")
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
