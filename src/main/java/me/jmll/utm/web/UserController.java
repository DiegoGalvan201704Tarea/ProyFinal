package me.jmll.utm.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import me.jmll.utm.form.LoginForm;
import me.jmll.utm.form.UserForm;
import me.jmll.utm.model.User;
import me.jmll.utm.service.UserService;

@Controller

public class UserController {
	private static final Logger logger = LogManager.getLogger();
	  @Autowired
	  UserService userService;
	  
	  
	  private ModelAndView getUserRedirect() {
	        return new ModelAndView(new RedirectView("/user/userlist", true, false));
	    }
	  
	  @RequestMapping(value="user", method=RequestMethod.GET)
	  public ModelAndView user(){
		  logger.info("Redireccionando");
		  return this.getUserRedirect();
	  }

	  
	  @RequestMapping(value = "user/userlist", 
				method = RequestMethod.GET)
public ModelAndView list(Map<String, Object> model, HttpSession session) {
		  logger.info("Obteniendo usuarios");
  model.put("users",  userService.getUsers());
  model.put("loginForm", new LoginForm());
  
  return new ModelAndView("user/userlist");
  
}
	  
@RequestMapping(value = "user/apis", method = RequestMethod.GET)
public ModelAndView apis(Map<String, Object> model, HttpSession session) {
		  logger.info("apis");

model.put("user", new User());
return new ModelAndView("user/apis");

}
@RequestMapping(value = "user/apis", method = RequestMethod.POST)
public ModelAndView createuserapis(Map<String, Object> model, 
			HttpSession session, HttpServletRequest request,
			UserForm form) throws IOException {

	  String error = "";
	  boolean berror = false;
	  
	  String fullname = form.getFullName();
	  String username = form.getUsername();
	  String password = form.getPassword();
			  
	  if((fullname == "")||(username == "")||(password == ""))
	  {
		  error = "Datos incompletos no se puede generar usuario";
		  berror = true;
		  logger.info(error);
	  }
	  else
	  {
		  User usr = userService.createUser(username, password, fullname);
		  error = "usuario creado  correctamente: "+ usr.getFullName();
		  logger.info(error);
	  }
	  model.put("isError", berror);
	  model.put("error", error);
	  model.put("user", new User());
	  
	  
return new ModelAndView("user/apis");

}

@RequestMapping(value = "user/deleteuser", method = RequestMethod.GET)
public ModelAndView deleteuserGet(Map<String, Object> model, HttpSession session) {
		  model.put("user",new User());
		  return new ModelAndView("user/deleteuser");
}

@RequestMapping(value = "user/deleteuser", method = RequestMethod.POST)
public ModelAndView fileUpload(Map<String, Object> model, 
			HttpSession session, HttpServletRequest request,			
			UserForm form) throws IOException {

	  String error = "";
	  boolean berror = false;
	  String username = form.getUsername();
	  if((username == ""))
	  {
		  error = "Datos incompletos no se puede generar usuario";
		  berror = true;
		  logger.info(error);
	  }
	  else
	  {
		  User usr = null;
	        List<User> lst = userService.getUsers();
	        for(int i = 0; i< lst.size(); i++)
	        {
	        	usr = lst.get(i);
	        	if(usr.getUsername().equals(username))
	        		break;
	        		
	        }
		 // User usr = userService.getUser(username);
		  if(usr == null)
		  {
			  error = "usuario no se encuentra en el la DB";
			  berror = true;
		  }
		  else
		  {
			  userService.deleteUser(username);
			  error = "usuario creado  correctamente: "+ usr.getFullName();
			  
		  }
		  
		  logger.info(error);
	  }
	  model.put("isError", berror);
	  model.put("error", error);
	  model.put("user", new User());
	  
	  
return new ModelAndView("user/deleteuser");

}
	  
@RequestMapping(value = "user/createuser", method = RequestMethod.GET)
public ModelAndView createuserGet(Map<String, Object> model, HttpSession session) {
		  model.put("user",new User());
		  return new ModelAndView("user/createuser");
}	  
	  
	  @RequestMapping(value = "user/createuser", method = RequestMethod.POST)
	  public ModelAndView createuser(Map<String, Object> model, 
				HttpSession session, HttpServletRequest request,
				UserForm form) throws IOException {

		  String error = "";
		  boolean berror = false;
		  
		  String fullname = form.getFullName();
		  String username = form.getUsername();
		  String password = form.getPassword();
				  
		  if((fullname == "")||(username == "")||(password == ""))
		  {
			  error = "Datos incompletos no se puede generar usuario";
			  berror = true;
			  
		  }
		  else
		  {
			  User usr = null;
		        List<User> lst = userService.getUsers();
		        for(int i = 0; i< lst.size(); i++)
		        {
		        	usr = lst.get(i);
		        	if(usr.getUsername().equals(username))
		        		break;
		        		
		        }
			  if(usr == null)
			  {
				  usr = userService.createUser(username, password, fullname);
				  error = "usuario creado  correctamente: "+ usr.getFullName();
			  }
			  else
			  {
				 error= "Usuario creado previamente";
				 berror = true;
			  }
			  
			  
		  }
		  logger.info(error);
		  model.put("isError", berror);
		  model.put("error", error);
		  model.put("user", new User());
		  
		  
return new ModelAndView("user/createuser");

}

}
