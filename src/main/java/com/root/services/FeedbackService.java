package com.root.services;

import java.util.List;

import com.root.DTO.FeedbackDTO;
import com.root.exceptions.BusException;
import com.root.exceptions.FeedBackException;
import com.root.exceptions.UserException;
import com.root.models.Feedback;


public interface FeedbackService {


	public Feedback addFeedBack(FeedbackDTO feedbackDTO, Integer busId) throws BusException, UserException;

	public Feedback updateFeedBack(Feedback feedback) throws FeedBackException, UserException;

	public String  deleteFeedBack(Integer feedbackId)throws FeedBackException, UserException;

	public Feedback viewFeedback(Integer id) throws FeedBackException;

	public List<Feedback> viewFeedbackAll() throws FeedBackException;

}
