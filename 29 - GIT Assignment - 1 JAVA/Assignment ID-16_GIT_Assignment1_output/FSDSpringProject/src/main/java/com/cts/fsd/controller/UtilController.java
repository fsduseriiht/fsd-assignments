package com.cts.fsd.controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@CrossOrigin("*")
public class UtilController {
 
	/************************* API METHODS START *************************/
	@RequestMapping(value={"", "/", "/welcome", "/home"})
	public ModelAndView showHomePage() {
		System.out.println("ApplicationController :: inside showHomePage()");
		ModelAndView modelViewObject = new ModelAndView("home");
		return modelViewObject;
	}
	
	@RequestMapping(value={"/error", "/notfound"})
	public ModelAndView showNotFoundPage() {
		System.out.println("ApplicationController :: inside showNotFoundPage()");
		ModelAndView modelViewObject = new ModelAndView("error");
		return modelViewObject;
	}
	
	@RequestMapping(value={"/close"})
	public ModelAndView showClosePage() {
		System.out.println("ApplicationController :: inside showClosePage()");
		ModelAndView modelViewObject = new ModelAndView("close");
		return modelViewObject;
	}
	
}