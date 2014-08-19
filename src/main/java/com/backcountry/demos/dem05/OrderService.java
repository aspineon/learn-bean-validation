package com.backcountry.demos.dem05;

import com.backcountry.demos.Utils;
import com.backcountry.demos.dem05.repository.OrderRepository;
import com.backcountry.demos.demo04.model.Order;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.executable.ExecutableValidator;
import java.util.Date;
import java.util.Set;

import static com.backcountry.demos.Utils.date;

/**
 * @author Edwin Dalorzo.
 */
public class OrderService {

    private OrderRepository orderRepository = new OrderRepository();
    private ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
    private Validator validator = vf.getValidator();
    private ExecutableValidator exeValidator = validator.forExecutables();

    public void changeDeliveryDate(Long id, Date newDeliveryDate) {

        Set<ConstraintViolation<Order>> violations;

        violations = validator.validateValue(Order.class, "deliveryDate", newDeliveryDate);
        Utils.printErrors(violations);

        Order order = orderRepository.findById(id);
        order.setDeliveryDate(newDeliveryDate);

        /*
        violations = validator.validateProperty(order, "deliveryDate");
        Utils.printErrors(violations);
        */
    }


    public static void main(String[] args) {
        OrderService service = new OrderService();
        service.changeDeliveryDate(123456L, date("2014-12-31"));
    }
}
