package com.root.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.root.DTO.FeedbackDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.root.exceptions.BusException;
import com.root.exceptions.FeedBackException;
import com.root.exceptions.UserException;
import com.root.models.Bus;
import com.root.models.Feedback;
import com.root.models.User;
import com.root.repository.BusDao;
import com.root.repository.FeedbackDao;
//import org.springframework.transaction.annotation.Transactional;

@Service
public class FeedbackServiceImpl implements FeedbackService {

	@Autowired
	private FeedbackDao feedbackDao;

	@Autowired
	UserService userService;

	@Autowired
	private BusDao busDao;



	@Override
	public Feedback addFeedBack(FeedbackDTO feedbackDTO, Integer busId) throws BusException, UserException {

        User user = userService.currentUser();

		Feedback feedback = new Feedback();

		Optional<Bus> busOptional = busDao.findById(busId);
		if (busOptional.isEmpty()) {

			throw new BusException("Bus is not present with Id: "+ busId);
		}

		feedback.setDriverRating(feedbackDTO.getDriverRating());
		feedback.setServiceRating(feedbackDTO.getServiceRating());
		feedback.setOverallRating(feedbackDTO.getOverallRating());
		feedback.setComments(feedbackDTO.getComments());
		feedback.setBus(busOptional.get());
		feedback.setUser(user);
		feedback.setFeedbackDateTime(LocalDateTime.now());
		Feedback savedFeedback = feedbackDao.save(feedback);

		return savedFeedback;
	}

	@Override
	public Feedback updateFeedBack(Feedback feedback) throws FeedBackException, UserException {

		User user = userService.currentUser();

		Optional<Feedback> opt = feedbackDao.findById(feedback.getFeedbackId());

		if (opt.isPresent()) {

			Feedback old_feedback = opt.get();

			Optional<Bus> busOptional = busDao.findById(old_feedback.getBus().getBusId());

			if(!busOptional.isPresent()) throw new FeedBackException("Invalid bus details!");

			feedback.setBus(busOptional.get());


			feedback.setUser(user);

			feedback.setFeedbackDateTime(LocalDateTime.now());

			return feedbackDao.save(feedback);

		}

		throw new FeedBackException("No feedback found!");
	}

	@Override
	public Feedback viewFeedback(Integer feedbackId) throws FeedBackException {

		String email = userService.currentUser().getEmail();


		Optional<Feedback> fedOptional = feedbackDao.findById(feedbackId);

		if (fedOptional.isPresent()) {

			Feedback feedback = fedOptional.get();

			if(!feedback.getUser().getEmail().equals(email))
				throw new FeedBackException("No feedback found with feedbackId "+feedbackId+" belonging to current loggedIn user!");

			return feedback;
		}

		throw new FeedBackException("No feedback found!");
	}

	@Override
	public List<Feedback> viewFeedbackAll() throws FeedBackException {

		List<Feedback> feedbackList = feedbackDao.findAll();

		if (!feedbackList.isEmpty()) {

			return feedbackList;
		}
		throw new FeedBackException("No feedbacks found!");
	}

	@Override
	@Transactional
	public String deleteFeedBack(Integer feedbackId) throws FeedBackException, UserException {

        String email = userService.currentUser().getEmail();

	    Feedback feedback = feedbackDao.findById(feedbackId).orElseThrow(()->new FeedBackException("No feedback found"));

		if(!feedback.getUser().getEmail().equals(email))
			throw new FeedBackException("No feedback found with feedbackId "+feedbackId+" belonging to current loggedIn user!");

//			feedbackDao.delete(feedback);

		feedbackDao.deleteByFeedbackId(feedbackId);

			return "Feedback with id "+feedbackId+" deleted successfully.";


	}

}
