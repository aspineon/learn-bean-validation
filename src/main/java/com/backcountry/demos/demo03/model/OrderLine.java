package com.backcountry.demos.demo03.model;

/**
 * @author Edwin Dalorzo.
 */
public class OrderLine {

    private String id;
    private String sku;
    private Integer quantity;

    public OrderLine(){
    }

    public OrderLine(String id, String sku, Integer quantity){
        this.id = id;
        this.sku = sku;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
