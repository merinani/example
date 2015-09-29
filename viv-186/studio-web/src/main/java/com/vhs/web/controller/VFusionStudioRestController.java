package com.vhs.web.controller;
 
import java.util.List;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.servlet.ModelAndView;

import com.vhs.web.model.Project;
import com.vhs.web.model.Csrf;
import com.vhs.web.service.ProjectService;

import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.GrantedAuthority;

import com.vhs.web.model.UserData;

@RestController
public class VFusionStudioRestController {
 
    @Autowired
    ProjectService projectService;  //Service which will do all data retrieval/manipulation work

	@Autowired
    HttpServletRequest context;

	Authentication authentication;

	private String currentUsername = null;

	private boolean isLoggedIn = false;

	//Principal userDetails;

    //-------------------Retrieve All Projects--------------------------------------------------------
     
	@RequestMapping(value = "/home**", method = RequestMethod.GET)
	public ModelAndView homePage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("static/home.html");
		return model;
	}

    @RequestMapping(value= "/csrf/", method = RequestMethod.GET)
    public ResponseEntity<Csrf> CsrfInfo() {
		CsrfToken csrfToken = (CsrfToken) context.getAttribute("_csrf");

    	Csrf csrf = new Csrf();
    	csrf.setTokenId(csrfToken.getToken());
    	csrf.setParameterName(csrfToken.getParameterName());
		csrf.setHeaderName(csrfToken.getHeaderName());	

		return new ResponseEntity<Csrf>(csrf, HttpStatus.OK);
    }

	@RequestMapping(value = "/projects/", method = RequestMethod.GET)
    public ResponseEntity<List<Project>> listAllProjects() {
        List<Project> projects = projectService.findAllProjects();
        if(projects.isEmpty()){
            return new ResponseEntity<List<Project>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Project>>(projects, HttpStatus.OK);
    }

	@RequestMapping(value= "/userdata/", method = RequestMethod.GET)
    public ResponseEntity<UserData> UserDataInfo() {
		currentUsername = null;

		isLoggedIn = isLoggedIn();

		if (null == currentUsername) {
			currentUsername = authentication.getName(); //get logged in username
		}

		UserData userData = new UserData();
		userData.setUserName(currentUsername);
		userData.setIsAdmin(determineUserRole());
		userData.setIsLogged(isLoggedIn);

		return new ResponseEntity<UserData>(userData, HttpStatus.OK);
	}

	private boolean isLoggedIn () {
		authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication != null && !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();
	}

	public boolean determineUserRole () {
		boolean isAdmin = false;
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
                isAdmin = true;
				break;
            } else {
				isAdmin = false;
				break;
			}	
        }
		return isAdmin;
    }
}