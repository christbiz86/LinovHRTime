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

import com.demo.model.LeaveRequest;
import com.demo.model.LeaveRequestDetail;
import com.demo.service.LeaveRequestService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@RequestMapping({ "/api/v1/time" })
public class LeaveRequestController {
	
	@Autowired
	private LeaveRequestService leaveRequestService;

	@GetMapping(value = "/leave-requests")
	@Transactional
	public ResponseEntity<?> getLeaveRequests() {
		List<LeaveRequest> leaveRequest = leaveRequestService.findAll();
		return ResponseEntity.ok(leaveRequest);
	}
	
	@GetMapping(value = "/leave-request/details")
	@Transactional
	public ResponseEntity<?> getLeaveRequestDetails() {
		List<LeaveRequestDetail> ratingScDet = leaveRequestService.findAllDet();
		return ResponseEntity.ok(ratingScDet);
	}

	@GetMapping(value = "/leave-request/{id}")
	@Transactional
	public ResponseEntity<?> getLeaveRequestById(@PathVariable String id) {
		LeaveRequest leaveRequest = leaveRequestService.findById(id);
		return ResponseEntity.ok(leaveRequest);
	}
	
	@GetMapping(value = "/leave-request/detail/{id}")
	@Transactional
	public ResponseEntity<?> getLeaveRequestDetailById(@PathVariable String id) {
		LeaveRequestDetail leaveRequest = leaveRequestService.findDetById(id);
		return ResponseEntity.ok(leaveRequest);
	}

	@PostMapping(value = "/leave-request")
	@Transactional
	public ResponseEntity<?> submitHeaderDetail(@RequestBody LeaveRequest leaveRequest) throws Exception {
		try {
			leaveRequestService.saveHeaderDetail(leaveRequest);
			return ResponseEntity.ok(HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} 
	}
	
	@PostMapping(value = "/leave-request/header")
	@Transactional
	public ResponseEntity<?> submitHeader(@RequestBody LeaveRequest leaveRequest) throws Exception {
		try {
			leaveRequestService.saveHeader(leaveRequest);
			return ResponseEntity.ok(HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} 
	}
	
	@PostMapping(value = "/leave-request/detail")
	@Transactional
	public ResponseEntity<?> submitDetail(@RequestBody LeaveRequestDetail leaveReqDet) throws Exception {
		try {
			leaveRequestService.saveDet(leaveReqDet);
			return ResponseEntity.ok(HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} 
	}
	
	@PutMapping(value = "/leave-request")
	@Transactional
	public ResponseEntity<?> updateHeader(@RequestBody LeaveRequest leaveRequest) throws Exception {
		try {
			leaveRequestService.update(leaveRequest);
			return ResponseEntity.ok(HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Update Failed!");
		}
	}
	
	@PutMapping(value = "/leave-request/detail")
	@Transactional
	public ResponseEntity<?> updateDetail(@RequestBody LeaveRequestDetail leaveReqDet) throws Exception {
		try {
			leaveRequestService.updateDet(leaveReqDet);
			return ResponseEntity.ok(HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Update Failed!");
		}
	}
	
	@DeleteMapping(value = "/leave-request/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable String id) throws Exception {
		try {
			leaveRequestService.delete(id);
			return ResponseEntity.ok(HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Delete Failed!");
		}
	}

	@DeleteMapping(value = "/leave-request/detail/{id}")
	@Transactional
	public ResponseEntity<?> deleteDetail(@PathVariable String id) throws Exception {
		try {
			leaveRequestService.deleteDet(id);
			return ResponseEntity.ok(HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Delete Failed!");
		}
	}
}
