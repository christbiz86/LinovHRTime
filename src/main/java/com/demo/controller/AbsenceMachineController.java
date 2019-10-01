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

import com.demo.model.AbsenceMachine;
import com.demo.service.AbsenceMachineService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Controller
@RequestMapping({"/api/v1/time"})
public class AbsenceMachineController {

	@Autowired
	private AbsenceMachineService absenceMachineService;
	
	@Transactional
	@GetMapping(value = "/absence-machines")
    public ResponseEntity<?> getAllAbsenceMachine()
	{
		try{
				List<AbsenceMachine> listAbsenceMachine = absenceMachineService.findAll();

				return ResponseEntity.ok(listAbsenceMachine);
		}
		catch(Exception e){
			 
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
	
	@Transactional
	@GetMapping(value = "/absence-machine/{id}")
    public ResponseEntity<?> getAbsenceMachine(@PathVariable String id)
	{
		try{
			AbsenceMachine absenceMachine = absenceMachineService.findById(id);

			return ResponseEntity.ok(absenceMachine);
		}
		catch(Exception e){
			 
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
	
	@Transactional
	@PostMapping("/absence-machine")
    public ResponseEntity<?> postAbsenceMachine(@RequestBody AbsenceMachine absenceMachine)
	{
		try{	
			absenceMachineService.save(absenceMachine);	
			return ResponseEntity.ok("Save Success");
		}
		catch (Exception e) {

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
    }
	
	@Transactional
	@PutMapping("/absence-machine")
    public ResponseEntity<?> putAbsenceMachine(@RequestBody AbsenceMachine absenceMachine)
	{
		try{	
			absenceMachineService.update(absenceMachine);	
			return ResponseEntity.ok("Put Success");
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

    }
	
	@Transactional
	@DeleteMapping("/absence-machine/{id}")
    public ResponseEntity<?> deleteAbsenceMachine(@PathVariable String id)
	{
		try{	
			absenceMachineService.delete(id);	
			return ResponseEntity.ok("Delete Success");
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

    }
}
