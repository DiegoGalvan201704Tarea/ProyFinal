package me.jmll.utm.model;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Product {
	private int id;
	 private long folio;
	 private String compuesto;
	 private String espesor;
	 private String kgPieza;
	 private long piezasPaquetes;
	 
	 public Product()
	 {}
	 public Product(int _id, long _folio, String _compuesto, String _espesor, String _kgPieza, long _pieza){
		 
		 this.setId(_id);
		 this.setFolio(_folio);
		 this.setCompuesto(_compuesto);
		 this.setEspesor(_espesor);
		 this.setKgPieza(_kgPieza);
		 this.setPiezasPaquetes(_pieza);
		 
	 }
	 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public long getFolio() {
		return folio;
	}
	public void setFolio(long folio) {
		this.folio = folio;
	}
	public String getCompuesto() {
		return compuesto;
	}
	public void setCompuesto(String compuesto) {
		this.compuesto = compuesto;
	}
	public String getEspesor() {
		return espesor;
	}
	public void setEspesor(String espesor) {
		this.espesor = espesor;
	}
	public String getKgPieza() {
		return kgPieza;
	}
	public void setKgPieza(String kgPieza) {
		this.kgPieza = kgPieza;
	}
	public long getPiezasPaquetes() {
		return piezasPaquetes;
	}
	public void setPiezasPaquetes(long piezasPaquetes) {
		this.piezasPaquetes = piezasPaquetes;
	}
}
