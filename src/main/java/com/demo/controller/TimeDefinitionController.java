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

import com.demo.model.TimeDefinition;
import com.demo.service.TimeDefinitionService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@RequestMapping(value = "/api/v1/time")
public class TimeDefinitionController {
	
	@Autowired
	private TimeDefinitionService tdService;
	
	@GetMapping(value = "/time-definitions")
	@Transactional
	public ResponseEntity<?> findAll() throws Exception {
		try {
			List<TimeDefinition> list = tdService.findAll();
			return new ResponseEntity<List<TimeDefinition>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@GetMapping(value = "/time-definition/{id}")
	@Transactional
	public ResponseEntity<?> findById(@PathVariable String id) throws Exception {
		try {
			TimeDefinition td = tdService.findById(id);
			return new ResponseEntity<TimeDefinition>(td, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@GetMapping(value = "/time-definition/code/{code}")
	@Transactional
	public ResponseEntity<?> findByCode(@PathVariable String code) throws Exception {
		try {
			TimeDefinition td = tdService.findByCode(code);
			return new ResponseEntity<TimeDefinition>(td, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@PostMapping(value = "/time-definition")
	@Transactional
	public ResponseEntity<?> insert(@RequestBody TimeDefinition td) throws Exception {
		try {
			tdService.insert(td);
			return ResponseEntity.ok("Insert success with Time Definition name: "+td.getName());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PutMapping(value = "/time-definition")
	@Transactional
	public ResponseEntity<?> update(@RequestBody TimeDefinition td) throws Exception {
		try {
			tdService.update(td);
			return ResponseEntity.ok("Update success with Time Definition ID: "+td.getId());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@DeleteMapping(value = "/time-definition/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable String id) throws Exception {
		try {
			tdService.delete(id);
			return ResponseEntity.ok("Delete success with Time Definition ID: "+id);
		} catch (Exception e) {
		    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

}
