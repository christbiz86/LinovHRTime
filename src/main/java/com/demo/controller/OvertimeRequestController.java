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

import com.demo.model.OvertimeRequest;
import com.demo.service.OvertimeRequestService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@RequestMapping({"/api/v1/time"})
public class OvertimeRequestController {

	@Autowired
	private OvertimeRequestService overtimeRequestService;
	
	@Transactional
	@GetMapping(value = "/overtime-requests")
    public ResponseEntity<?> getAllOvertimeRequest()
	{
		try{
				List<OvertimeRequest> listOvertimeRequest = overtimeRequestService.findAll();

				return ResponseEntity.ok(listOvertimeRequest);
		}
		catch(Exception e){
			 
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
	
	@Transactional
	@GetMapping(value = "/overtime-request/{id}")
    public ResponseEntity<?> getOvertimeRequest(@PathVariable String id)
	{
		try{
			OvertimeRequest overtimeRequest = overtimeRequestService.findById(id);

			return ResponseEntity.ok(overtimeRequest);
		}
		catch(Exception e){
			 
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
	
	@Transactional
	@PostMapping("/overtime-request")
    public ResponseEntity<?> postOvertimeRequest(@RequestBody OvertimeRequest overtimeRequest)
	{
		try{	
			overtimeRequestService.save(overtimeRequest);	
			return ResponseEntity.ok("Save Success");
		}
		catch (Exception e) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
	
	@Transactional
	@PutMapping("/overtime-request")
    public ResponseEntity<?> putOvertimeRequest(@RequestBody OvertimeRequest overtimeRequest)
	{
		try{	
			overtimeRequestService.update(overtimeRequest);	
			return ResponseEntity.ok("Put Success");
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

    }
	
	@Transactional
	@DeleteMapping("/overtime-request/{id}")
    public ResponseEntity<?> deleteOvertimeRequest(@PathVariable String id)
	{
		try{	
			overtimeRequestService.delete(id);	
			return ResponseEntity.ok("Delete Success");
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

    }
}
