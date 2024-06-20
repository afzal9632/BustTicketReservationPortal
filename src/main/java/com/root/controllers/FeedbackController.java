package com.root.controllers;

import java.util.List;

import com.root.DTO.FeedbackDTO;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.root.exceptions.BusException;
import com.root.exceptions.FeedBackException;
import com.root.exceptions.UserException;
import com.root.models.Feedback;
import com.root.services.FeedbackService;


@RestController
public class FeedbackController {
	
	@Autowired
	private FeedbackService feedbackService;
	
	
	
	@PostMapping("/user/adddFeedback/{busid}")
	public ResponseEntity<Feedback> addFeedback(@Valid @RequestBody FeedbackDTO feedbackDTO,
												@PathVariable("busid") Integer busId) throws UserException,BusException{
		
		Feedback feedback = feedbackService.addFeedBack(feedbackDTO,busId);
		
		return new ResponseEntity<>(feedback,HttpStatus.ACCEPTED);
		
	}
	
	
	
	@PutMapping("/user/updateFeedback")
	public ResponseEntity<Feedback> updateFeedback(@Valid @RequestBody Feedback feedback) throws FeedBackException, UserException {
		
		Feedback feedback2 = feedbackService.updateFeedBack(feedback);
		
		return new ResponseEntity<>(feedback2,HttpStatus.ACCEPTED);
		
	}
	
	@DeleteMapping("/user/deleteFeedback/{id}")
	public ResponseEntity<String> deleteFeedback(@PathVariable("id") Integer feedbackId) throws FeedBackException, UserException {
		
		String response = feedbackService.deleteFeedBack(feedbackId);
		
		return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
		
	}
	
	

	@GetMapping("/user/getFeedback/{id}")
	public ResponseEntity<Feedback> viewFeedback(@PathVariable("id") Integer ID) throws FeedBackException {
		
		Feedback feedback2 = feedbackService.viewFeedback(ID);
		
		return new ResponseEntity<>(feedback2,HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/admin/allFeedback")
	public ResponseEntity<List<Feedback>> viewFeedbackAll() throws FeedBackException {
		
		List<Feedback> feedback2 =  feedbackService.viewFeedbackAll();
		
		return new ResponseEntity<>(feedback2,HttpStatus.ACCEPTED);
		
	}
	
}
