package com.backcountry.demos.dem05;

import com.backcountry.demos.Utils;
import com.backcountry.demos.demo04.model.OrderLine;
import org.apache.commons.lang3.reflect.ConstructorUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.executable.ExecutableValidator;
import java.lang.reflect.Constructor;
import java.util.Set;

/**
 * @author Edwin Dalorzo.
 */
public class OrderLineService {

    private ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
    private Validator validator = vf.getValidator();
    private ExecutableValidator exeValidator = validator.forExecutables();

    public OrderLine createOrderLine(String id, String sku, Integer quantity) {
        Set<ConstraintViolation<OrderLine>> violations;
        Constructor<OrderLine> orderLineConst = ConstructorUtils.getAccessibleConstructor(OrderLine.class, String.class, String.class, Integer.class);
        Object[] params = {id, sku, quantity};
        violations = exeValidator.validateConstructorParameters(orderLineConst, params);
        Utils.printErrors(violations);
        return new OrderLine(id, sku, quantity);
    }

    public static void main(String[] args) {
        OrderLineService service = new OrderLineService();
        service.createOrderLine("123", "AAI000H-X002-Y002", -1);
    }


}
