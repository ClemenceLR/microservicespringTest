package com.ecommerce.microcommerce.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ecommerce.microcommerce.dao.ProductDao;
import com.ecommerce.microcommercemodel.Product;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import java.net.URI;
import java.util.List;

@RestController //désigne la classe comme controleur  //renverra grace a l'annotation les réponses en json a l'utilisateur : pas de vue

public class ProductController { 
	@Autowired
	private ProductDao productDao;
	
	//Methode GET/Produits
    @RequestMapping(value="/Produits", method=RequestMethod.GET)	
    public MappingJacksonValue listeProduits() {

        List<Product> produits = productDao.findAll();

        SimpleBeanPropertyFilter monFiltre = SimpleBeanPropertyFilter.serializeAllExcept("prixAchat");

        FilterProvider listDeNosFiltres = new SimpleFilterProvider().addFilter("filtreDynamique", monFiltre);

        MappingJacksonValue produitsFiltres = new MappingJacksonValue(produits);

        produitsFiltres.setFilters(listDeNosFiltres);

        return produitsFiltres;
    }

    
    
    public List<Product> listeProduits2() {
		return productDao.findAll();
	}
    
    //Accéder à la page d'un produit
    @GetMapping(value="/Produits/{id}")
    public Product afficherUnProduit(@PathVariable int id) {
    	return productDao.findById(id);
    }
    
    //Ajouter un produit
    @PostMapping(value = "/Produits")
    public ResponseEntity<Void> ajouterProduit(@RequestBody Product product) {

        Product productAdded =  productDao.save(product); //on récupère le produit ajouté

        if (productAdded == null) //si il n'y a pas de produit ajouté on retourne le code NoContent : 204 (build construit le header et y ajoute le code)
            return ResponseEntity.noContent().build();
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(productAdded.getId())
                .toUri();
        //on récupère l'id et l'url de l'objet qui vie d'être ajouté et on crée l'objet
        return ResponseEntity.created(location).build();
    }


    
	
}
