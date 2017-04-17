package me.jmll.utm.repository;

import java.util.List;
import java.util.ArrayList;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import me.jmll.utm.model.Product;


import java.sql.*;

@Repository("ProductRepository")
public class ProductRepositoryImpl implements ProductRepository {

	Connection con = null;
	CallableStatement cstmt = null;
	ResultSet rs = null;
	
	String connectionUrl = "jdbc:sqlserver://localhost\\SQLEXPRESS:1434;databaseName=prolams;responseBuffering=adaptive;loginTimeout=50;user=sa;password=1234;";
	private static final Logger log = LogManager.getLogger();
	
	public ProductRepositoryImpl(){
		try {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionUrl);
			
			
		} 
		catch (SQLException e) {
			
			log.debug("Error: {}", e.getMessage());
		}
		catch (ClassNotFoundException e) {
			log.debug("Error: {}", e.getMessage());
			log.debug("error2: {}", e.getLocalizedMessage());
		}
		
	}
	private void instanceconnector()
	{
		
		try {
		
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(connectionUrl);
			
			
		} 
		catch (SQLException e) {
			
			log.debug("Error: {}", e.getMessage());
		}
		catch (ClassNotFoundException e) {
			log.debug("Error: {}", e.getMessage());
		}
	}
	
	@Override
	public List<Product> getProduct() {
		List<Product> ProductList = new ArrayList<Product>();
		try{
			cstmt = null;
			if(con == null)
				this.instanceconnector();
			cstmt = con.prepareCall("{call dbo.damePieza (?)}");
			cstmt.setInt(1, 0);
			rs = cstmt.executeQuery();
			Product prt = null;
			while(rs.next()){
				prt = new Product(rs.getInt("idMaterial"), rs.getLong("folioMaterial"), rs.getString("compuesto"), rs.getString("espesor"),rs.getString("kgPieza"), rs.getLong("piezasPaquetes") );
				ProductList.add(prt);
			}
		}
		catch(SQLException e){
			log.debug("Error: {}", e.getMessage());
		}
		
		return ProductList;
		
	}

	@Override
	public Product getProductById(int id) {
	
		Product prt = null;
		try{
			cstmt = null;
			if(con == null)
				this.instanceconnector();
			cstmt = con.prepareCall("{call dbo.damePieza (?)}");
			cstmt.setInt(1, id);
			rs = cstmt.executeQuery();
			
			while(rs.next()){
				prt = new Product(rs.getInt("idMaterial"), rs.getLong("folioMaterial"), rs.getString("compuesto"), rs.getString("espesor"),rs.getString("kgPieza"), rs.getLong("piezasPaquetes") );
				break;
				
			}
		}
		catch(SQLException e){
			log.debug("Error: {}", e.getMessage());
		}
		return prt;
	}

	@Override
	public Product createProduct(long folio, String compuesto, String espesor, String kgPieza, long piezasPaquetes) {
		int idfinal = 0;
		Product prt = null;
		try{
			cstmt = null;
			if(con == null)
				this.instanceconnector();
			cstmt = con.prepareCall("{call dbo.insertaPieza (?,?,?,?,?)}");
			cstmt.setLong(1, folio);
			cstmt.setString(2, compuesto);
			cstmt.setString(3, espesor);
			cstmt.setString(4, kgPieza);
			cstmt.setLong(5, piezasPaquetes);
			rs = cstmt.executeQuery();
			
			while(rs.next()){
				idfinal = rs.getInt("ultimo");
				
				break;
				
			}
			
			prt = this.getProductById(idfinal);
			
		}
		catch(SQLException e){
			log.debug("Error: {}", e.getMessage());
		}
		return prt;
	}

	@Override
	public void deleteProduct(int id) {
		
		try{
			cstmt = null;
			if(con == null)
				this.instanceconnector();
			cstmt = con.prepareCall("{call dbo.deletePieza (?)}");
			cstmt.setInt(1, id);
			cstmt.execute();
			
		}
		catch(SQLException e){
			log.debug("Error: {}", e.getMessage());
		}
		
	}

	@Override
	public Product updateUser(Product product) {
		try{
			cstmt = null;
			if(con == null)
				this.instanceconnector();
			cstmt = con.prepareCall("{call dbo.updatePieza (?,?,?,?,?,?)}");
			cstmt.setInt(1,product.getId());
			cstmt.setLong(1, product.getFolio());
			cstmt.setString(2, product.getCompuesto());
			cstmt.setString(3, product.getEspesor());
			cstmt.setString(4, product.getKgPieza());
			cstmt.setLong(1, product.getPiezasPaquetes());
			rs = cstmt.executeQuery();
			
			
			
			product = this.getProductById(product.getId());
			
		}
		catch(SQLException e){
			log.debug("Error: {}", e.getMessage());
		}
		return product;
	}

}
