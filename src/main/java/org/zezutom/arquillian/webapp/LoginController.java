package org.zezutom.arquillian.webapp;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class LoginController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final String SUCCESS = "Welcome";
	
	public static final String FAILURE = "Incorrect username and password combination";
	
	private User currentUser;
	
	@Inject
	private Credentials credentials;

	public String login() {
		String view = null;
		
		if (isValid(credentials.getUsername()) && isValid(credentials.getPassword())) {
			view = "home.xhtml";
			currentUser = new User("demo");
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(SUCCESS));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, FAILURE, FAILURE));
		}
		
		return view;
	}
	
	public boolean isLoggedIn() {
		return currentUser != null;
	}
	
	@Produces
	public User getCurrentUser() {
		return currentUser;
	}
	
	private boolean isValid(String authentication) {
		return "demo".equals(authentication);
	}
}
