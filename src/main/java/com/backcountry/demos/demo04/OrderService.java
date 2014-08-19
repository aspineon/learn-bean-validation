package com.backcountry.demos.demo04;

import com.backcountry.demos.Utils;
import com.backcountry.demos.demo04.model.Order;
import com.backcountry.demos.demo04.model.OrderLine;
import com.backcountry.demos.demo04.model.OrderStatus;

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
        order.setPaymentDate(date("2014-01-02"));
        order.setDeliveryDate(date("2014-01-03"));
        order.getOrderLines().add(new OrderLine("1", "AAI000H-X002-Y002", 1));
        //order.setOrderLines(null);
        //order.setOrderLines(new ArrayList<>());

        OrderService service = new OrderService();
        service.createOrder(order);
        //service.payOrder(order);
        //service.deliverOrder(order);
    }

}
