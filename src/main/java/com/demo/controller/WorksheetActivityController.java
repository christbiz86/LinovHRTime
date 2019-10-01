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

import com.demo.model.WorksheetActivity;
import com.demo.service.WorksheetActivityService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@RequestMapping({"/api/v1/time"})
public class WorksheetActivityController {

	@Autowired
	private WorksheetActivityService worksheetActivityService;
	
	@Transactional
	@GetMapping(value = "/worksheet-activities")
    public ResponseEntity<?> getAllWorksheetActivity()
	{
		try{
				List<WorksheetActivity> listWorksheetActivity = worksheetActivityService.findAll();

				return ResponseEntity.ok(listWorksheetActivity);
		}
		catch(Exception e){
			 
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
	
	@Transactional
	@GetMapping(value = "/worksheet-activity/{id}")
    public ResponseEntity<?> getWorksheetActivity(@PathVariable String id)
	{
		try{
			WorksheetActivity worksheetActivity = worksheetActivityService.findById(id);

			return ResponseEntity.ok(worksheetActivity);
		}
		catch(Exception e){
			 
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
	
	@Transactional
	@PostMapping("/worksheet-activity")
    public ResponseEntity<?> postWorksheetActivity(@RequestBody WorksheetActivity worksheetActivity)
	{
		try{	
			worksheetActivityService.save(worksheetActivity);	
			return ResponseEntity.ok("Save Success");
		}
		catch (Exception e) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
	
	@Transactional
	@PutMapping("/worksheet-activity")
    public ResponseEntity<?> putWorksheetActivity(@RequestBody WorksheetActivity worksheetActivity)
	{
		try{	
			worksheetActivityService.update(worksheetActivity);	
			return ResponseEntity.ok("Put Success");
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

    }
	
	@Transactional
	@DeleteMapping("/worksheet-activity/{id}")
    public ResponseEntity<?> deleteWorksheetActivity(@PathVariable String id)
	{
		try{	
			worksheetActivityService.delete(id);	
			return ResponseEntity.ok("Delete Success");
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

    }
}
