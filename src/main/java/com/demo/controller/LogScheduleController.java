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

import com.demo.model.LogSchedule;
import com.demo.service.LogScheduleService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@RequestMapping({"/api/v1/time"})
public class LogScheduleController {

	@Autowired
	private LogScheduleService logScheduleService;
	
	@Transactional
	@GetMapping(value = "/log-schedules")
    public ResponseEntity<?> getAllLogSchedule()
	{
		try{
				List<LogSchedule> listLogSchedule = logScheduleService.findAll();

				return ResponseEntity.ok(listLogSchedule);
		}
		catch(Exception e){
			 
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
	
	@Transactional
	@GetMapping(value = "/log-schedule/{id}")
    public ResponseEntity<?> getLogSchedule(@PathVariable String id)
	{
		try{
			LogSchedule logSchedule = logScheduleService.findById(id);

			return ResponseEntity.ok(logSchedule);
		}
		catch(Exception e){
			 
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
	
	@Transactional
	@PostMapping("/log-schedule")
    public ResponseEntity<?> postLogSchedule(@RequestBody LogSchedule logSchedule)
	{
		try{	
			logScheduleService.save(logSchedule);	
			return ResponseEntity.ok("Save Success");
		}
		catch (Exception e) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
	
	@Transactional
	@PutMapping("/log-schedule")
    public ResponseEntity<?> putLogSchedule(@RequestBody LogSchedule logSchedule)
	{
		try{	
			logScheduleService.update(logSchedule);	
			return ResponseEntity.ok("Put Success");
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

    }
	
	@Transactional
	@DeleteMapping("/log-schedule/{id}")
    public ResponseEntity<?> deletelogScheduleService(@PathVariable String id)
	{
		try{	
			logScheduleService.delete(id);	
			return ResponseEntity.ok("Delete Success");
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

    }
}
