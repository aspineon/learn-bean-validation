package com.backcountry.demos.demo04.model;


import com.backcountry.demos.demo04.constraits.ChronologicalOrder;
import com.backcountry.demos.demo04.constraits.HasItems;
import com.backcountry.demos.demo04.constraits.HasStatus;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ChronologicalOrder
public class Order {

    private Long id;
    private Date creationDate;
    private Double totalAmount;
    private Date paymentDate;

    @Past
    private Date deliveryDate;

    @NotNull
    @HasStatus(OrderStatus.PENDING)
    private OrderStatus status;

    @HasItems
    @Valid //graph validation
    private List<OrderLine> orderLines = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(@NotNull Long id) {
        this.id = id;
    }

    @Past
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }
}
