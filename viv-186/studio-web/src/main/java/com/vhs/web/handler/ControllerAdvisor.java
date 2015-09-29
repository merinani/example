package com.vhs.web.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;;

@ControllerAdvice
@Controller
public class ControllerAdvisor {

    @ExceptionHandler({NoHandlerFoundException.class})
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	public ModelAndView handleNotFoundException(HttpServletRequest req){
        return new ModelAndView("404");
	}
	
	@RequestMapping(value="/404")
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	public ModelAndView handleNotFound(HttpServletRequest req){
        return new ModelAndView("404");
	}
	
	/**
	 * Exception thrown when the Method is not supported by our definitions.
	 * Normally, it's a HEAD request.
	 * 
	 * @param req
	 * @return
	 */
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseStatus(value=HttpStatus.METHOD_NOT_ALLOWED)
	public ModelAndView handleHttpRequestMethodNotSupported(HttpServletRequest req){
        return new ModelAndView("404");
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	public ModelAndView handleError(HttpServletRequest req, Exception exception){
        return new ModelAndView("404");
	}
}