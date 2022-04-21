/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Cart;
import entities.Product;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author aicha
 */
public class CartService {
    private static CartService INSTANCE;
    public static CartService getInstance(){
        if(INSTANCE==null){
            INSTANCE = new CartService();
        }
        return INSTANCE;
    }
    private Map<String, Cart> entries;
    public CartService(){
        this.entries = new HashMap<>();
    }
    public void addP(String productName){
        Cart produit = entries.get(productName.toUpperCase());
        if(produit!=null){
            produit.increaseQuantity();
        }
        else{
            Product prod = Product.valuesOf(productName);
            Cart c = new Cart(prod, 1);
            entries.put(productName, c);
                 
        }
    }
    
    public void removeProduct(String productName){
        Cart produit = entries.get(productName);
        if(produit!=null){
            produit.decreaseQuantity();
        }
    }
    
    public int getQuantity(String productName){
        Cart cart = entries.get(productName);
        if(cart!=null){
            return cart.getQuantity();
        }
        return 0;
    }
    
    public float calculateTotal(){
        float total=0;
        for(Cart cart:entries.values()){
            float Cost = cart.getProduct().getPrix()*cart.getQuantity();
            total = total + Cost;
        }
        return total;
    }
    public List<Cart> getEntries(){
        return new ArrayList<>(entries.values());
    }
    
    
}
