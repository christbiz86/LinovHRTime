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

import com.demo.model.Worksheet;
import com.demo.service.WorksheetService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@RequestMapping({"/api/v1/time"})
public class WorksheetController {

	@Autowired
	private WorksheetService worksheetService;
	
	@Transactional
	@GetMapping(value = "/worksheets")
    public ResponseEntity<?> getAllWorksheet()
	{
		try{
				List<Worksheet> listWorksheet = worksheetService.findAll();

				return ResponseEntity.ok(listWorksheet);
		}
		catch(Exception e){
			 
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
	
	@Transactional
	@GetMapping(value = "/worksheet/{id}")
    public ResponseEntity<?> getWorksheet(@PathVariable String id)
	{
		try{
			Worksheet worksheet = worksheetService.findById(id);

			return ResponseEntity.ok(worksheet);
		}
		catch(Exception e){
			 
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
	
	@Transactional
	@PostMapping("/worksheet")
    public ResponseEntity<?> postWorksheet(@RequestBody Worksheet worksheet)
	{
		try{	
			worksheetService.save(worksheet);	
			return ResponseEntity.ok("Save Success");
		}
		catch (Exception e) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
	
	@Transactional
	@PutMapping("/worksheet")
    public ResponseEntity<?> putWorksheet(@RequestBody Worksheet worksheet)
	{
		try{	
			worksheetService.update(worksheet);	
			return ResponseEntity.ok("Put Success");
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

    }
	
	@Transactional
	@DeleteMapping("/worksheet/{id}")
    public ResponseEntity<?> deleteWorksheet(@PathVariable String id)
	{
		try{	
			worksheetService.delete(id);	
			return ResponseEntity.ok("Delete Success");
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

    }
}
