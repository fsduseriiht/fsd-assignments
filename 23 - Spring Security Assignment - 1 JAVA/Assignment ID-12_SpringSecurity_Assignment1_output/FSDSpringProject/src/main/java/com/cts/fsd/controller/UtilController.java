package com.cts.fsd.controller;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cts.fsd.entity.User;
import com.cts.fsd.login.service.UserService;

@RestController
@CrossOrigin("*")
public class UtilController {
	
	
	@Autowired
    private UserService userService;
	
	
	@Autowired(required=false)
	private HttpServletRequest httpServletRequest;
 
	/************************* API METHODS START *************************/
	@RequestMapping(value={"", "/"})
	public ModelAndView showHomePage() {
		System.out.println("ApplicationController :: inside showHomePage()");
		ModelAndView modelViewObject = new ModelAndView("loginpage");
		return modelViewObject;
	}
	
	@RequestMapping(value={ "/home"})
	public ModelAndView showMenuPage() {
		System.out.println("ApplicationController :: inside showHomePage()");
		ModelAndView modelViewObject = new ModelAndView("home");
		
		{
        	System.out.println("Application Authorization Details /home = " + SecurityContextHolder.getContext().getAuthentication().getDetails().toString());
            String sessionId = "";
            String [] token = SecurityContextHolder.getContext().getAuthentication().getDetails().toString().split(";");
            List<String> tokenList = Arrays.asList(token);
            for(String tempToken : tokenList) {
            	if (tempToken.trim().startsWith("SessionId")) {
            		String [] tempSplitter = tempToken.split(":");
            		
            		sessionId = tempSplitter[1].trim();
    			}
            }
            System.out.println("Session ID /home =============   " + sessionId);
            if (!sessionId.isEmpty()) {
            	modelViewObject.addObject("sessionIdToken" , sessionId);
    		}
        }
		
		User userExists = (User) httpServletRequest.getSession().getAttribute("userdetails");
		if(userExists != null) {
			modelViewObject.addObject("userdetails" , userExists);
        }
		
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
	
	

    @RequestMapping(value={"/login"}, method = RequestMethod.GET)
    public ModelAndView loginGET(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("loginpage");
        return modelAndView;
    }

    @RequestMapping(value={"/welcome"}, method = RequestMethod.POST)
    public ModelAndView loginPOST(
							@RequestParam(value = "email", required = true)
							String email,
							
							@RequestParam(value = "password", required = true)
							String password
							){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        
        User userExists = userService.findUserByEmail(email);
        
        if(userExists != null) {
        	modelAndView.addObject("userdetails" , userExists);
        }
        
        {
        	System.out.println("Application Authorization Details = " + SecurityContextHolder.getContext().getAuthentication().getDetails().toString());
            String sessionId = "";
            String [] token = SecurityContextHolder.getContext().getAuthentication().getDetails().toString().split(";");
            List<String> tokenList = Arrays.asList(token);
            for(String tempToken : tokenList) {
            	if (tempToken.trim().startsWith("SessionId")) {
            		String [] tempSplitter = tempToken.split(":");
            		
            		sessionId = tempSplitter[1].trim();
    			}
            }
            System.out.println("Session ID =============   " + sessionId);
            if (!sessionId.isEmpty()) {
            	modelAndView.addObject("sessionIdToken" , sessionId);
    		}
            
            httpServletRequest.getSession().setAttribute("userdetails" , userExists);
            
        }
        
        
        return modelAndView;
    }

    @RequestMapping(value="/registration", method = RequestMethod.GET)
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }
    
    
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(
								@RequestParam(value = "firstName", required = true)
								String firstName,
								
								@RequestParam(value = "type", required = true , defaultValue = "USER")
								String type,
								
								@RequestParam(value = "lastName", required = true)
								String lastName,
								
								@RequestParam(value = "email", required = true)
								String email,
								
								@RequestParam(value = "password", required = true, defaultValue = "all")
								String password
								) {
    	
    	User user = User.builder().build();
    	user.setName(firstName);
    	user.setType(type);
    	user.setLastName(lastName);
    	user.setEmail(email);
    	user.setPassword(password);
    	
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            System.out.println("User Exists...!!");
            modelAndView.addObject("successMessage", "User Exists...!!");
            modelAndView.setViewName("registration");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("registration");

        }
        
        return modelAndView;
    }

    @RequestMapping(value="/admin/home", method = RequestMethod.GET)
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
        modelAndView.setViewName("admin/home");
        return modelAndView;
    }

	
}