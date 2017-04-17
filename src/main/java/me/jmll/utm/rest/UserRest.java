package me.jmll.utm.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import me.jmll.utm.form.UserForm;
import me.jmll.utm.model.User;
import me.jmll.utm.model.UserList;
import me.jmll.utm.rest.exception.ResourceNotFoundException;
import me.jmll.utm.service.UserService;

@Controller
public class UserRest {
	@Autowired
	UserService userService;
	
	/**
	 * Recurso para obtener colección de entidades
	 * */
	@RequestMapping(value = "user", 
					method = RequestMethod.GET)
    @ResponseBody @ResponseStatus(HttpStatus.OK)
    public UserList getUsers() {
        UserList list = new UserList();
        list.setValue(this.userService.getUsers());
        return list;
    }
	
	/**
	 * Recurso para obtener entidad
	 * */
	@RequestMapping(value = "user/{username}", 
					method = RequestMethod.GET)
    @ResponseBody @ResponseStatus(HttpStatus.OK)
    public User getUser(@PathVariable("username") int id) {
        User user = this.userService.getUser(id);
        if(user == null)
            throw new ResourceNotFoundException("User was not found");
        return user;
    }
	
	/**
	 * Recurso para eliminar entidad
	 * */
	@RequestMapping(value = "user/{username}", 
					method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("username") int id) {
        if(this.userService.getUser(id) == null)
            throw new ResourceNotFoundException("User was not found");
       // this.userService.deleteUser(username);
    }
	
	/**
	 * Recurso para crear entidad
	 * */
	@RequestMapping(value = "user/{username}", 
					method = RequestMethod.POST)
	public ResponseEntity<User> create(@PathVariable("username") String username,@RequestBody UserForm form){
		User newUser = this.userService.createUser(form.getUsername(), 
				form.getPassword(), form.getFullName());
		
		String uri = ServletUriComponentsBuilder.fromCurrentServletMapping()
                .path("/user/{username}").buildAndExpand(newUser.getUsername()).toString();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", uri);

        return new ResponseEntity<>(newUser, headers, HttpStatus.CREATED);
	}
	
	/**
	 * Recurso para actualizar entidad
	 * */
    @RequestMapping(value = "user/{id}", 
    				method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("username") int id,
    		@RequestBody UserForm form){
        User user = this.userService.getUser(id);
        if(user == null)
            throw new ResourceNotFoundException("User was not found");
        user.setFullName(form.getFullName());
        user.setPassword(form.getPassword());
        user.setUsername(form.getUsername());
        this.userService.updateUser(user);
    }
    
	@RequestMapping(value = "user", 
			method = RequestMethod.OPTIONS)
	public ResponseEntity<Void> userIndex() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Allow", "OPTIONS,HEAD,GET,POST");
		return new ResponseEntity<>(null, headers, HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value = "user/{id}", 
			method = RequestMethod.OPTIONS)
    public ResponseEntity<Void> userOptions(@PathVariable("username") int id) {
        if(this.userService.getUser(id) == null)
            throw new ResourceNotFoundException("User was not found");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Allow", "OPTIONS,HEAD,GET,PUT,DELETE");
        return new ResponseEntity<>(null, headers, HttpStatus.NO_CONTENT);
    }
}
