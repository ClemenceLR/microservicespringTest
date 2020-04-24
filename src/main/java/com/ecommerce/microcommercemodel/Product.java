package com.ecommerce.microcommercemodel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;



@Entity
public class Product {
	@Id
	@GeneratedValue
	private int id;
	private String nom;
	private int prix;
	
	//information que l'on ne veut pas exposer :
	//@JsonIgnore //fonctionne
	
	//@JsonFilter("filtreDynamique")
	private int prixAchat; 

	//@JsonIgnoreProperties(value = {"prixAchat", "id"}) //n'a pas fonctionné
	
	public Product(int id2, String string, int i, int prixAchat) {
		setId(id2);
		setNom(string);
		setPrix(i);
		setPrixAchat(prixAchat);
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getPrix() {
		return prix;
	}
	public void setPrix(int prix) {
		this.prix = prix;
	}
	
	public int getPrixAchat() {
		return prixAchat;
	}


	public void setPrixAchat(int prixAchat) {
		this.prixAchat = prixAchat;
	}
	
	
	@Override
	//mis en forme pour JSON comprehension
	public String toString() {
		
		return "Product{"+
		"id = "+id+
		",nom = '" + nom + '\'' +
		",prix = "+ prix + '}';
	}


	
}
