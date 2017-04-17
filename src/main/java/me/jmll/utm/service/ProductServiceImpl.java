package me.jmll.utm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.jmll.utm.model.Product;
import me.jmll.utm.repository.ProductRepository;
@Service
public class ProductServiceImpl implements ProductService  {

	@Autowired 
	ProductRepository prodrep;
	
	@Override
	public List<Product> getProduct() {
		
		return prodrep.getProduct();
	}

	@Override
	public Product getProductById(int id) {

		return prodrep.getProductById(id);
	}

	@Override
	public Product createProduct(long folio, String compuesto, String espesor, String kgPieza, long piezasPaquetes) {
		// 
		return prodrep.createProduct(folio, compuesto, espesor, kgPieza, piezasPaquetes);
	}

	@Override
	public void deleteProduct(int id) {
	 prodrep.deleteProduct(id);
	 
		
	}

	@Override
	public Product updateUser(Product product) {
		
		return prodrep.updateUser(product);
	}

}
