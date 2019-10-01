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

import com.demo.model.LogSchedulePattern;
import com.demo.service.LogSchedulePatternService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@RequestMapping({"/api/v1/time"})
public class LogSchedulePatternController {

	@Autowired
	private LogSchedulePatternService logSchedulePatternService;

	@Transactional
	@GetMapping(value = "/log-schedule-patterns")
    public ResponseEntity<?> getAllLogScehdulePattern()
	{
		try{
				List<LogSchedulePattern>listLogSchedulePattern = logSchedulePatternService.findAll();

				return ResponseEntity.ok(listLogSchedulePattern);
		}
		catch(Exception e){
			 
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
	
	@Transactional
	@GetMapping(value = "/log-schedule-pattern/{id}")
    public ResponseEntity<?> getLogSchedulePattern(@PathVariable String id)
	{
		try{
			LogSchedulePattern logSchedulePattern = logSchedulePatternService.findById(id);

			return ResponseEntity.ok(logSchedulePattern);
		}
		catch(Exception e){
			 
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
	
	@Transactional
	@PostMapping("/log-schedule-pattern")
    public ResponseEntity<?> postLogSchedulePattern(@RequestBody LogSchedulePattern logSchedulePattern)
	{
		try{	
			logSchedulePatternService.save(logSchedulePattern);	
			return ResponseEntity.ok("Save Success");
		}
		catch (Exception e) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
	
	@Transactional
	@PutMapping("/log-schedule-pattern")
    public ResponseEntity<?> putLogSchedulePattern(@RequestBody LogSchedulePattern logSchedulePattern)
	{
		try{	
			logSchedulePatternService.update(logSchedulePattern);	
			return ResponseEntity.ok("Put Success");
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

    }
	
	@Transactional
	@DeleteMapping("/log-schedule-pattern/{id}")
    public ResponseEntity<?> deleteLogSchedulePattern(@PathVariable String id)
	{
		try{	
			logSchedulePatternService.delete(id);	
			return ResponseEntity.ok("Delete Success");
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

    }
}