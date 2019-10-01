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

import com.demo.model.PermitRequest;
import com.demo.service.PermitRequestService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@Transactional
@RequestMapping({ "/api/v1/time" })
public class PermitRequestController {
	
	@Autowired
	private PermitRequestService prService;
	
	@GetMapping(value = "/permit-requests")
	@Transactional
	public ResponseEntity<?> findAll() throws Exception {
		try {
			List<PermitRequest> list = prService.findAll();
			return new ResponseEntity<List<PermitRequest>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@GetMapping(value = "/permit-request/{id}")
	@Transactional
	public ResponseEntity<?> findById(@PathVariable String id) throws Exception {
		try {
			PermitRequest pr = prService.findById(id);
			return new ResponseEntity<PermitRequest>(pr, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@PostMapping(value = "/permit-request")
	@Transactional
	public ResponseEntity<?> insert(@RequestBody PermitRequest pr) throws Exception {
		try {
			prService.insert(pr);
			return ResponseEntity.ok("Insert success!");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PutMapping(value = "/permit-request")
	@Transactional
	public ResponseEntity<?> update(@RequestBody PermitRequest pr) throws Exception {
		try {
			prService.update(pr);
			return ResponseEntity.ok("Update success with Permit Request ID: "+pr.getId());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@DeleteMapping(value = "/permit-request/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable String id) throws Exception {
		try {
			prService.delete(id);
			return ResponseEntity.ok("Delete success with Permit Request ID: "+id);
		} catch (Exception e) {
		    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

}
