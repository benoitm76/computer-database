package com.excilys.projet.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
@RequestMapping("/error")
public class ErrorController {
	@RequestMapping("/404")
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ModelAndView error404(Exception e) {
		ModelAndView mav = new ModelAndView("404");
		return mav;
	}

	@RequestMapping("/500")
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ModelAndView error500(Exception e) {
		ModelAndView mav = new ModelAndView("exception");
		mav.addObject("name", e.getClass().getSimpleName());
		mav.addObject("message", e.getMessage());
		return mav;
	}

	@RequestMapping("/exception")
	@ExceptionHandler(Exception.class)
	public ModelAndView errorException(Exception e) {

		ModelAndView mav = new ModelAndView("exception");
		mav.addObject("name", e.getClass().getSimpleName());
		mav.addObject("message", e.getMessage());

		return mav;
	}

}
