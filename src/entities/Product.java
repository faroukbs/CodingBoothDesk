/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author doghm
 */
public class Product {
    private int id;
    private String product_name;
    private String img,desc;
    private int stock;
    private double price;
    private int quantity=1;
    private int bought=0;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

   
    
    public Product(){
        
    }
    
    public Product(int id,String product_name,String img,int stock,double price,int quantity,int bought){
        this.id=id;
        this.product_name=product_name;
        this.img=img;
        this.stock=stock;
        this.price=price;
        this.quantity=quantity;
        this.bought=bought;
    }
    
    public Product(int id,String product_name,String img,int stock,double price,int quantity){
        this.id=id;
        this.product_name=product_name;
        this.img=img;
        this.stock=stock;
        this.price=price;
        this.quantity=quantity;
    }
    
    
    
    public Product(String product_name,String img,int stock,double price,int quantity){
        this.product_name=product_name;
        this.img=img;
        this.stock=stock;
        this.price=price;
        this.quantity=quantity;
    }

    
    public Product(int id,String product_name,String img,int stock,double price){
        this.id=id;
        this.product_name=product_name;
        this.img=img;
        this.stock=stock;
        this.price=price;
    }

    public Product(String product_name,String img,int stock,double price){
        this.product_name=product_name;
        this.img=img;
        this.stock=stock;
        this.price=price;
    }
    
    public Product(int id,int quantity){
        this.id=id;
        this.quantity=quantity;
    }
    
    public Product(String product_name){
        this.product_name=product_name;
    }
    public Product(String product_name,int quantity){
        this.product_name=product_name;
        this.quantity=quantity;
    }

    public Product(Product cart) {
       
    }

    
    public int getId() {
        return id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getImg() {
        return img;
    }

    public int getStock() {
        return stock;
    }

    public double getPrice() {
        return price;
    }

    public int getBought() {
        return bought;
    }
    


    public void setId(int id) {
        this.id = id;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setBought(int bought) {
        this.bought = bought;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", product_name=" + product_name + ", img=" + img + ", stock=" + stock + ", price=" + price + ", quantity=" + quantity + ", bought=" + bought + '}';
    }
       
}
