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

import me.jmll.utm.form.ProductForm;

import me.jmll.utm.model.Product;
import me.jmll.utm.model.ProductList;

import me.jmll.utm.rest.exception.ResourceNotFoundException;
import me.jmll.utm.service.ProductService;

@Controller
public class ProductRest {
	
	@Autowired
	ProductService prodservice;
	
	@RequestMapping(value = "product", 
			method = RequestMethod.GET)
	@ResponseBody @ResponseStatus(HttpStatus.OK)
	public ProductList getUsers() {
		ProductList list = new ProductList();
	list.setValue(this.prodservice.getProduct());
	return list;
	}
	
	@RequestMapping(value = "product/{id}", 
			method = RequestMethod.GET)
	@ResponseBody @ResponseStatus(HttpStatus.OK)
	public Product getProduct(@PathVariable("id") int id) {
	Product prt = this.prodservice.getProductById(id);
	if(prt == null)
	    throw new ResourceNotFoundException("User was not found");
	return prt;
	}
	
	@RequestMapping(value = "product/{id}", 
			method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteProduct(@PathVariable("id") int id) {
	if(this.prodservice.getProductById(id) == null)
	    throw new ResourceNotFoundException("User was not found");
	this.prodservice.deleteProduct(id);
	
	}
	
	@RequestMapping(value = "product", 
			method = RequestMethod.POST)
	public ResponseEntity<Product> create(@RequestBody ProductForm form){
	Product newprt = this.prodservice.createProduct(form.getFolio(), form.getCompuesto(), form.getEspesor(), form.getKgPieza(), form.getPiezasPaquetes()); 
			
	
	String uri = ServletUriComponentsBuilder.fromCurrentServletMapping()
	        .path("/product/{id}").buildAndExpand(newprt.getId()).toString();
	HttpHeaders headers = new HttpHeaders();
	headers.add("Location", uri);
	
	return new ResponseEntity<>(newprt, headers, HttpStatus.CREATED);
	}
	
	 @RequestMapping(value = "product/{id}", 
				method = RequestMethod.PUT)
		@ResponseStatus(HttpStatus.NO_CONTENT)
		public void update(@PathVariable("username") int id,
				@RequestBody ProductForm form){
		 Product prt = this.prodservice.getProductById(id);
		 if(prt == null)
		     throw new ResourceNotFoundException("User was not found");
		prt.setFolio(form.getFolio());
		prt.setCompuesto(form.getCompuesto());
		prt.setEspesor(form.getEspesor());
		prt.setKgPieza(form.getKgPieza());
		prt.setPiezasPaquetes(form.getPiezasPaquetes());
		 this.prodservice.updateUser(prt);
		}
	 
	 @RequestMapping(value = "product", 
				method = RequestMethod.OPTIONS)
		public ResponseEntity<Void> ProductIndex() {
			HttpHeaders headers = new HttpHeaders();
			headers.add("Allow", "OPTIONS,HEAD,GET,POST");
			return new ResponseEntity<>(null, headers, HttpStatus.NO_CONTENT);
		}
	 
	 @RequestMapping(value = "product/{id}", 
				method = RequestMethod.OPTIONS)
	    public ResponseEntity<Void> ProductOptions(@PathVariable("id") int id) {
	        if(this.prodservice.getProductById(id) == null)
	            throw new ResourceNotFoundException("User was not found");

	        HttpHeaders headers = new HttpHeaders();
	        headers.add("Allow", "OPTIONS,HEAD,GET,PUT,DELETE");
	        return new ResponseEntity<>(null, headers, HttpStatus.NO_CONTENT);
	    }

}
