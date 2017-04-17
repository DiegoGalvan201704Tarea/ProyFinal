package me.jmll.utm.repository;
import java.util.List;
import me.jmll.utm.model.Product;

public interface ProductRepository {
	
	
	public List<Product> getProduct();
	public Product getProductById(int id);
	public Product createProduct(long folio, String compuesto, String espesor, String kgPieza, long piezasPaquetes); 
	public void deleteProduct(int id);
	public Product updateUser(Product product);

}
