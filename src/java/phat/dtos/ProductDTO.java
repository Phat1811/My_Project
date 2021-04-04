/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phat.dtos;

/**
 *
 * @author ACER
 */
public class ProductDTO {
    private String productID;
    private String productName;
    private float price;
    private int quantity;
    private String catagoryID;

    public ProductDTO() {
    }

    public ProductDTO(String productID, String productName, float price, int quantity, String catagoryID) {
        this.productID = productID;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.catagoryID = catagoryID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCatagoryID() {
        return catagoryID;
    }

    public void setCatagoryID(String catagoryID) {
        this.catagoryID = catagoryID;
    }

    @Override
    public String toString() {
        return "ProductDTO{" + "productID=" + productID + ", productName=" + productName + ", price=" + price + ", quantity=" + quantity + ", catagoryID=" + catagoryID + '}';
    }
    
    
}
