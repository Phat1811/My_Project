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
public class ProductErrorDTO {
    private String productIDError;
    private String productNameError;
    private String priceError;
    private String quantityError;
    private String catagoryIDError;

    public ProductErrorDTO() {
    }

    public ProductErrorDTO(String productIDError, String productNameError, String priceError, String quantityError, String catagoryIDError) {
        this.productIDError = productIDError;
        this.productNameError = productNameError;
        this.priceError = priceError;
        this.quantityError = quantityError;
        this.catagoryIDError = catagoryIDError;
    }

    public String getProductIDError() {
        return productIDError;
    }

    public void setProductIDError(String productIDError) {
        this.productIDError = productIDError;
    }

    public String getProductNameError() {
        return productNameError;
    }

    public void setProductNameError(String productNameError) {
        this.productNameError = productNameError;
    }

    public String getPriceError() {
        return priceError;
    }

    public void setPriceError(String priceError) {
        this.priceError = priceError;
    }

    public String getQuantityError() {
        return quantityError;
    }

    public void setQuantityError(String quantityError) {
        this.quantityError = quantityError;
    }

    public String getCatagoryIDError() {
        return catagoryIDError;
    }

    public void setCatagoryIDError(String catagoryIDError) {
        this.catagoryIDError = catagoryIDError;
    }
    
    
    
    
    
    
}
