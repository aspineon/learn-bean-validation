package com.backcountry.demos.demo04.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author Edwin Dalorzo.
 */
public class OrderLine {

    private String id;

    @NotNull
    private String sku;

    @Min(0)
    private Integer quantity;

    public OrderLine(){
    }

    public OrderLine(String id, @NotNull String sku, @Min(0) Integer quantity){
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
