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

import com.demo.model.Attendance;
import com.demo.service.AttendanceService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@Transactional
@RequestMapping({ "/api/v1/lov" })
public class AttendanceController {
	
	@Autowired
	private AttendanceService attendanceService;
	
	@GetMapping(value = "/attendances")
	@Transactional
	public ResponseEntity<?> findAll() throws Exception {
		try {
			List<Attendance> list = attendanceService.findAll();
			return new ResponseEntity<List<Attendance>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Retrieve failed!");
		}
	}
	
	@GetMapping(value = "/attendance/{id}")
	@Transactional
	public ResponseEntity<?> findById(@PathVariable String id) throws Exception {
		try {
			Attendance attendance = attendanceService.findById(id);
			return new ResponseEntity<Attendance>(attendance, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Retrieve failed!");
		}
	}
	
	@GetMapping(value = "/attendance/code/{code}")
	@Transactional
	public ResponseEntity<?> findByCode(@PathVariable String code) throws Exception {
		try {
			Attendance attendance = attendanceService.findByCode(code);
			return new ResponseEntity<Attendance>(attendance, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Retrieve failed!");
		}
	}
	
	@PostMapping(value = "/attendance")
	@Transactional
	public ResponseEntity<?> insert(@RequestBody Attendance attendance) throws Exception {
		try {
			attendanceService.insert(attendance);
			return ResponseEntity.ok("Insert success with Attendance name: "+attendance.getName());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PutMapping(value = "/attendance")
	@Transactional
	public ResponseEntity<?> update(@RequestBody Attendance attendance) throws Exception {
		try {
			attendanceService.update(attendance);
			return ResponseEntity.ok("Update success with Attendance ID: "+attendance.getId());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@DeleteMapping(value = "/attendance/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable String id) throws Exception {
		try {
			attendanceService.delete(id);
			return ResponseEntity.ok("Delete success with Attendance ID: "+id);
		} catch (Exception e) {
		    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Delete failed!");
		}
	}

}
