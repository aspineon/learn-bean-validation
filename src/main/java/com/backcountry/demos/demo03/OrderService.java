package com.backcountry.demos.demo03;

import com.backcountry.demos.Utils;
import com.backcountry.demos.demo03.model.Order;
import com.backcountry.demos.demo03.model.OrderStatus;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static com.backcountry.demos.Utils.date;

/**
 * @author Edwin Dalorzo.
 */
public class OrderService {

    private ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
    private Validator validator = vf.getValidator();


    public void payOrder(Order order) {
        Set<ConstraintViolation<Order>> violations;
        violations = validator.validate(order);
        Utils.printErrors(violations);
    }

    public void deliverOrder(Order order) {
        Set<ConstraintViolation<Order>> violations;
        violations = validator.validate(order);
        Utils.printErrors(violations);
    }

    public void createOrder(Order order) {
        Set<ConstraintViolation<Order>> violations;
        violations = validator.validate(order);
        Utils.printErrors(violations);
    }

    public static void main(String[] args) {
        Order order = new Order();
        order.setId(12345L);
        order.setStatus(OrderStatus.PENDING);
        order.setCreationDate(date("2014-01-01"));
        order.setDeliveryDate(null);
        order.setPaymentDate(null);

        OrderService service = new OrderService();
        service.createOrder(order);
        //service.payOrder(order);
        //service.deliverOrder(order);
    }
}
