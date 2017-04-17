package me.jmll.utm.web;

import java.io.IOException;

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

import me.jmll.utm.form.ProductForm;

import me.jmll.utm.model.Product;
import me.jmll.utm.model.User;
import me.jmll.utm.service.ProductService;



@Controller
public class ProductController {

	
	private static final Logger logger = LogManager.getLogger();
	 @Autowired
	 ProductService productservice;
	 
	private ModelAndView getProductRedirect() {
	        return new ModelAndView(new RedirectView("/products/apis", true, false));
	    }
	 @RequestMapping(value="products", method=RequestMethod.GET)
	  public ModelAndView product(){
		  logger.info("Redireccionando");
		  return this.getProductRedirect();
	  }
	  
	 @RequestMapping(value = "products/productlis", 
				method = RequestMethod.GET)
	public ModelAndView list(Map<String, Object> model, HttpSession session) {
			  logger.info("Obteniendo usuarios");
	model.put("product",  productservice.getProduct());
	model.put("loginForm", new ProductForm());
	
	return new ModelAndView("product/productlis");
	
	}
	 
	 @RequestMapping(value = "products/apis", method = RequestMethod.GET)
	 public ModelAndView apis(Map<String, Object> model, HttpSession session) {
	 		  logger.info("apis");

	 model.put("product", new Product());
	 return new ModelAndView("products/apis");

	 }
	 
	 @RequestMapping(value = "products/apis", method = RequestMethod.POST)
	 public ModelAndView createuserapis(Map<String, Object> model, 
	 			HttpSession session, HttpServletRequest request,
	 			ProductForm form) throws IOException {

	 	  String error = "";
	 	  boolean berror = false;
	 	  Product prt = new Product();
	 	  long folio = form.getFolio();
	 	  String compuesto = form.getCompuesto();
	 	  String espesor = form.getEspesor();
	 	  String kgPieza = form.getKgPieza();
	 	  long piezasPaquetes = form.getPiezasPaquetes();
	 	  prt = productservice.createProduct(folio, compuesto, espesor, kgPieza, piezasPaquetes);
	 	  error = "producto creado  correctamente: "+ prt.getCompuesto();
	 		  logger.info(error);
	 	  
	 	  model.put("isError", berror);
	 	  model.put("error", error);
	 	  model.put("product", prt);
	 	  
	 	  
	 return getProductRedirect();

	 }
	 
	 @RequestMapping(value = "products/deleteproduct", method = RequestMethod.GET)
	 public ModelAndView deleteproductGet(Map<String, Object> model, HttpSession session) {
	 		  model.put("product",new Product());
	 		  return new ModelAndView("Productos/deleteproduct");
	 }
	 @RequestMapping(value = "products/deleteproduct", method = RequestMethod.POST)
	 public ModelAndView fileUpload(Map<String, Object> model, 
	 			HttpSession session, HttpServletRequest request,			
	 			ProductForm form) throws IOException {

	 	  String error = "";
	 	  boolean berror = false;
	 	  int id = form.getId();
	 	  if((id == 0))
	 	  {
	 		  error = "Datos incompletos no se puede generar usuario";
	 		  berror = true;
	 		  logger.info(error);
	 	  }
	 	  else
	 	  {
	 		  productservice.deleteProduct(id);
	 		  
	 		  logger.info(error);
	 	  }
	 	  model.put("isError", berror);
	 	  model.put("error", error);
	 	  model.put("product", new Product());
	 	  
	 	  
	 return new ModelAndView("products/deleteproduct");

	 }
	 
	 @RequestMapping(value = "products/createproduct", method = RequestMethod.GET)
	 public ModelAndView createuserGet(Map<String, Object> model, HttpSession session) {
	 		  model.put("user",new User());
	 		  return new ModelAndView("products/createproduct");
	 }	  
	 	  
	 	  @RequestMapping(value = "products/createproduct", method = RequestMethod.POST)
	 	  public ModelAndView createuser(Map<String, Object> model, 
	 				HttpSession session, HttpServletRequest request,
	 				ProductForm form) throws IOException {

	 		  String error = "";
	 		  boolean berror = false;
	 		 
	 		  Product prt = new Product();	  
	 		  
	 		  {
	 			  
	 			  {
	 				  prt = productservice.createProduct(form.getFolio(), form.getCompuesto(), form.getEspesor(), form.getKgPieza(), form.getPiezasPaquetes());
	 				  error = "usuario creado  correctamente: "+ prt.getFolio();
	 			  }
	 			 
	 			  
	 			  
	 		  }
	 		  logger.info(error);
	 		  model.put("isError", berror);
	 		  model.put("error", error);
	 		  model.put("product", prt);
	 		  
	 		  
	 return new ModelAndView("products/createproduct");

	 }
	 
	 
	 
	 
	 
}
