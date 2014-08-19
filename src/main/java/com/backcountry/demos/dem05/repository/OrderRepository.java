package com.backcountry.demos.dem05.repository;

import com.backcountry.demos.demo04.model.Order;
import com.backcountry.demos.demo04.model.OrderLine;
import com.backcountry.demos.demo04.model.OrderStatus;

import static com.backcountry.demos.Utils.date;

/**
 * @author Edwin Dalorzo.
 */
public class OrderRepository {

    public Order findById(Long id){
        Order order = new Order();
        order.setId(id);
        order.setStatus(OrderStatus.PENDING);
        order.setCreationDate(date("2014-01-01"));
        order.setPaymentDate(date("2014-01-02"));
        order.setDeliveryDate(date("2014-01-03"));
        order.getOrderLines().add(new OrderLine("1", "AAI000H-X002-Y002", 1));
        return order;
    }

}
