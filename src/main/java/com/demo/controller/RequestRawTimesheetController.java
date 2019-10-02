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

import com.demo.model.RequestRawTimesheet;
import com.demo.service.RequestRawTimesheetService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@Transactional
@RequestMapping({ "/api/v1/time" })
public class RequestRawTimesheetController {
	
	@Autowired
	private RequestRawTimesheetService rrtService;
	
	@GetMapping(value = "/request-raw-timesheets")
	@Transactional
	public ResponseEntity<?> findAll() throws Exception {
		try {
			List<RequestRawTimesheet> list = rrtService.findAll();
			return new ResponseEntity<List<RequestRawTimesheet>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@GetMapping(value = "/request-raw-timesheet/{id}")
	@Transactional
	public ResponseEntity<?> findById(@PathVariable String id) throws Exception {
		try {
			RequestRawTimesheet rrt = rrtService.findById(id);
			return new ResponseEntity<RequestRawTimesheet>(rrt, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@PostMapping(value = "/request-raw-timesheet")
	@Transactional
	public ResponseEntity<?> insert(@RequestBody RequestRawTimesheet rrt) throws Exception {
		try {
			rrtService.insert(rrt);
			return ResponseEntity.ok("Insert success!");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PutMapping(value = "/request-raw-timesheet")
	@Transactional
	public ResponseEntity<?> update(@RequestBody RequestRawTimesheet rrt) throws Exception {
		try {
			rrtService.update(rrt);
			return ResponseEntity.ok("Update success with Request Raw Timesheet ID: "+rrt.getId());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@DeleteMapping(value = "/request-raw-timesheet/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable String id) throws Exception {
		try {
			rrtService.delete(id);
			return ResponseEntity.ok("Delete success with Request Raw Timesheet ID: "+id);
		} catch (Exception e) {
		    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

}
