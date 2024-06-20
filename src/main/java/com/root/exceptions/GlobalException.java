package com.root.exceptions;

import java.sql.SQLException;
import java.time.LocalDateTime;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.web.csrf.InvalidCsrfTokenException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
@Slf4j
public class GlobalException {

	@ExceptionHandler(UserException.class)
	public ResponseEntity<Error> userException(UserException userEx,WebRequest web){

		Error error = new Error(LocalDateTime.now(),userEx.getMessage(), web.getDescription(false));

		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}



	@ExceptionHandler(BusException.class)
	public ResponseEntity<Error> busException(BusException busEx,WebRequest web){

		Error error = new Error(LocalDateTime.now(),busEx.getMessage(), web.getDescription(false));

		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(RouteException.class)
	public ResponseEntity<Error> routeException(RouteException routeEx,WebRequest web){

		Error error = new Error(LocalDateTime.now(),routeEx.getMessage(), web.getDescription(false));

		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(FeedBackException.class)
	public ResponseEntity<Error> feedbackException(FeedBackException feedbackEx,WebRequest web){

		Error error = new Error(LocalDateTime.now(),feedbackEx.getMessage(), web.getDescription(false));

		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}


	@ExceptionHandler(LoginException.class)
	public ResponseEntity<Error> loginException(LoginException loginEx,WebRequest web){

		Error error = new Error(LocalDateTime.now(),loginEx.getMessage(), web.getDescription(false));

		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ReservationException.class)
	public ResponseEntity<Error> reservationException(ReservationException reservationEx,WebRequest web){

		Error error = new Error(LocalDateTime.now(),reservationEx.getMessage(), web.getDescription(false));

		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Error> validatedException(MethodArgumentNotValidException validEx){

		Error error = new Error(LocalDateTime.now(),"Validation Error!",validEx.getBindingResult().getFieldError().getDefaultMessage());

		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<Error> noHandler(NoHandlerFoundException nohandler,WebRequest web){

		Error error = new Error(LocalDateTime.now(),nohandler.getMessage(), web.getDescription(false));

		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}


	@ExceptionHandler(SQLException.class)
	public ResponseEntity<Error> sqlException(SQLException e,WebRequest web){

		Error error = new Error(LocalDateTime.now(),e.getMessage(), web.getDescription(false));

		return new ResponseEntity<>(error,HttpStatus.EXPECTATION_FAILED);
	}


	@ExceptionHandler(Exception.class)
	public ProblemDetail handleSecurityException(Exception ex){

		ProblemDetail errorDetail = null;

		if(ex instanceof  BadCredentialsException){

			log.info("BadCredentialsException: Please provide valid credentials!");

			errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode
					.valueOf(401),ex.getMessage());
			errorDetail.setProperty("Access_denied_reason","Authentication Failure");

		}

		if(ex instanceof AccessDeniedException){

			log.info("AccessDeniedException: !");

			errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode
					.valueOf(403),ex.getMessage());
			errorDetail.setProperty("Access_denied_reason","Not Authorized");

		}

		if(ex instanceof SignatureException){

			log.info("SignatureException: !");

			errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode
					.valueOf(403),ex.getMessage());
			errorDetail.setProperty("Access_denied_reason","Invalid token");

		}

		if(ex instanceof ExpiredJwtException){

			log.info("ExpiredJwtException : !");

			errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode
					.valueOf(401),ex.getMessage());
			errorDetail.setProperty("Access_denied_reason","Token Expired");

		}



		if (errorDetail==null){
			log.info(ex.getMessage());
			errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(400),ex.getMessage());
		}

		return errorDetail;
	}



}

