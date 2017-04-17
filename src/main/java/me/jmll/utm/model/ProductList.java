package me.jmll.utm.model;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "products")
public class ProductList {
	private List<Product> value;
	 @XmlElement(name = "product")
	    public List<Product> getValue() {
	        return value;
	    }

	    public void setValue(List<Product> products) {
	        this.value = products;
	    }

}
