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

import com.demo.model.EmployeeQuota;
import com.demo.service.EmployeeQuotaService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@Transactional
@RequestMapping({ "/api/v1/time" })
public class EmployeeQuotaController {
	
	@Autowired
	private EmployeeQuotaService eqService;
	
	@GetMapping(value = "/employee-quotas")
	@Transactional
	public ResponseEntity<?> findAll() throws Exception {
		try {
			List<EmployeeQuota> list = eqService.findAll();
			return new ResponseEntity<List<EmployeeQuota>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@GetMapping(value = "/employee-quota/{id}")
	@Transactional
	public ResponseEntity<?> findById(@PathVariable String id) throws Exception {
		try {
			EmployeeQuota eq = eqService.findById(id);
			return new ResponseEntity<EmployeeQuota>(eq, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@PostMapping(value = "/employee-quota")
	@Transactional
	public ResponseEntity<?> insert(@RequestBody EmployeeQuota eq) throws Exception {
		try {
			eqService.insert(eq);
			return ResponseEntity.ok("Insert success!");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PutMapping(value = "/employee-quota")
	@Transactional
	public ResponseEntity<?> update(@RequestBody EmployeeQuota eq) throws Exception {
		try {
			eqService.update(eq);
			return ResponseEntity.ok("Update success with Employee Quota ID: "+eq.getId());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@DeleteMapping(value = "/employee-quota/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable String id) throws Exception {
		try {
			eqService.delete(id);
			return ResponseEntity.ok("Delete success with Employee Quota ID: "+id);
		} catch (Exception e) {
		    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

}
