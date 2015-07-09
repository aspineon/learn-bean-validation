# Bean Validation #

## Official Documentation ##

* [JSR-303 Bean Validation](https://jcp.org/en/jsr/detail?id=303)
* [JSR-349 Bean Validation 1.1](https://jcp.org/en/jsr/detail?id=349)
* [Bean Validation Official Site](http://beanvalidation.org/)
* [Hibernate Validator - Reference Implementation](http://hibernate.org/validator)
* [Hibernate Validator Documentation](http://docs.jboss.org/hibernate/validator/5.1/reference/en-US/html/index.html)
* [Bean Validation Course at PluralSite](http://pluralsight.com/training/Courses/TableOfContents/bean-validation)
* [Programming by Contract](http://www.cs.usfca.edu/~parrt/course/601/lectures/programming.by.contract.html)

## Maven Setup ##

```
#!xml
<dependency>
   <groupId>javax.validation</groupId>
   <artifactId>validation-api</artifactId>
   <version>1.1.0.Final</version>
</dependency>
<dependency>
   <groupId>org.hibernate</groupId>
   <artifactId>hibernate-validator</artifactId>
   <version>5.1.2.Final</version>
</dependency>
<dependency>
   <groupId>javax.el</groupId>
   <artifactId>javax.el-api</artifactId>
   <version>2.2.4</version>
</dependency>
<dependency>
   <groupId>org.glassfish.web</groupId>
   <artifactId>javax.el</artifactId>
   <version>2.2.4</version>
</dependency>
```

## Code Examples ##

### Spring COnfiguration ###

```xml
<bean id="validator" class="org.springframework.validation.beanvalidation.LocalValidatorFactoryBean"/>
<bean class="org.springframework.validation.beanvalidation.MethodValidationPostProcessor"/>
```

### Obtaining the Validator ###

```
#!java
private ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
private Validator validator = vf.getValidator();
private Set<ConstraintViolation<Customer>> violations;

public void createCustomer(Customer customer){
    violations = validator.validate(customer);
    if(violations.size() > 0){
        throw new ConstraintViolationException(violations);
    }
}
```

### Obtaining Validation Error Details ###

```java
private <T> void printErrors(Set<? extends ConstraintViolation<T>> violations){
    Formatter out = new Formatter();
    for(ConstraintViolation<T> violation : violations){
        String className = violation.getRootBeanClass().getSimpleName();
        String property = violation.getPropertyPath().toString();
        Object invalidValue = violation.getInvalidValue();
        String message = violation.getMessage();
        out.format("%s.%s (%s): %s%n", className, property, invalidValue, message);
    }
    System.out.println(out.toString());
}
```

### Composite Constraints ###

```java
@Target({FIELD, METHOD, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
@Documented
@Size(min=5)
@Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}$")
@Constraint(validatedBy = {})
//@ReportAsSingleViolation
public @interface Email {
    String message() default "invalid email address";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
```

### Group Validation ###

```java
public interface Payment{}
public interface Delivery{}

public class Order {

    @NotNull
    private Long id;
    private Date creationDate;
    private Double totalAmount;

    @NotNull(groups = Payment.class)
    @Past(groups = Payment.class)
    private Date paymentDate;

    @NotNull(groups = Delivery.class)
    @Past(groups = Delivery.class)
    private Date deliveryDate;

    private OrderStatus status;
    private List<OrderLine> orderLines;
    
    //getters and setters
}

//laters in the service method
public void deliverOrder(Order order){
   violations = validator.validate(order, Default.class, Delivery.class);
   printErrors(violations);
}

public void payOrder(Order order){
   violations = validator.validate(order, Default.class, Payment.class);
   printErrors(violations);
}
```

### Custom Constraints ###

```java
@Target({FIELD, METHOD, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = HasStatusValidator.class)
public @interface HasStatus {
    OrderStatus value();
    String message() default "The order should have status {value}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    @Target({ METHOD, FIELD, CONSTRUCTOR, PARAMETER })
    @Retention(RUNTIME)
    @Documented
    @interface List {
        HasStatus[] value();
    }
}
```

The validator code is defined as:

```java
public class HasStatusValidator implements ConstraintValidator<HasStatus, OrderStatus> {

    private OrderStatus status;

    @Override
    public void initialize(HasStatus constraintAnnotation) {
        this.status = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(OrderStatus orderStatus, ConstraintValidatorContext context) {
        return orderStatus != null && orderStatus.equals(status);
    }
}
```

The annotation can be used as follows:

**Standalone**
```java
@HasStatus(PENDING)
private OrderStatus status;
```

**Multiple**
```java
@HasStatus.List({
   @HasStatus(PENDING),
   @HasStatus(value = FULLFILLED, groups = Delivery.class)
})
private OrderStatus status;
```

### Class Level Validations ###

```java
@Target({TYPE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = HasOrderLineValidator.class)
public @interface HasOrderLines {
    String message() default "{com.backcountry.demo5.constraints.HasOrderLines.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
```

And the validator

```java
public class HasOrderLineValidator implements ConstraintValidator<HasOrderLines, Order> {

    @Override
    public void initialize(HasOrderLines constraintAnnotation) {
    }

    @Override
    public boolean isValid(Order order, ConstraintValidatorContext context) {
        return order.getOrderLines().size() > 0;
    }
}
```

It can be used at the class level as:

```java
@HasOrderLines
public class Order {
  //...
}
```

### Constructor Parameters, Method Parameters and Return Types ###

```java
private ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
private Validator validator = vf.getValidator();
private ExecutableValidator exeValidator  = validator.forExecutables();
private Set<ConstraintViolation<Order>> violations;

public void createOrder(Long id, Date creationDate){
    Constructor<Order> constructor = ConstructorUtils.getAccessibleConstructor(Order.class, Long.class, Date.class);
    violations = exeValidator.validateConstructorParameters(constructor, new Object[]{id, creationDate});
    printErrors(violations);
}
```

There is also a method `validateParameters` and `validateReturnValue`.

### Validate Properties and Values ###

```java
public void changeDeliveryDate(Order order){
    order.setDeliveryDate(date("2016-01-01"));

    violations = validator.validateProperty(order, "deliveryDate");
   printErrors(violations);
}

public void changeDeliveryDate(Order order, Date deliveryDate){

    violations = validator.validateValue(Order.class, "deliveryDate", deliveryDate);
    printErrors(violations);
    order.setDeliveryDate(deliveryDate);
}
```

### Message Bundles ###

Define keys in a `ValidationMessages.properties`:

```
com.backcountry.demo5.constraints.HasOrderLines.message=The order must have at least one order line
```

Then you can define bundles per locale, like `ValidationMessages_es_CR.properties`

```
com.backcountry.demo5.constraints.HasOrderLines.message=La orden debe tener al menos una linea.
```

### Spring Integration ###

Validations can automatically triggered by Spring MVC and errors reported in BindingResult.

```java
@RequestMapping(
            value = "/customers",
            method = RequestMethod.PUT,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    public CustomerModel createCustomer(@RequestBody @Validated CustomerModel model, BindingResult result) throws ServiceException {
        if(result.hasErrors()){
            throw new ValidationServiceException(result);
        }
        return customerService.createCustomer(model);
    }
```

The `@Validated` annotation from Spring also allows the programmer to specify validation groups.

In order to inject a validator directly into a service layer class, we can define a Spring bean in our configuration:

```xml
<bean id="validator" class="org.springframework.validation.beanvalidation.LocalValidatorFactoryBean"/>
<bean class="org.springframework.validation.beanvalidation.MethodValidationPostProcessor"/>
```

Then, at the service layer we can manually validate models:

```java
@Autowired
private Validator validator;

@Override
public CustomerModel createCustomer(CustomerModel model) throws ServiceException {
    Set<ConstraintViolation<CustomerModel>> violations = validator.validate(model);
    if (violations.size() > 0) {
        throw new ValidationServiceException(violations);
    }
    try {
        return map(customerRepository.save(map(model)));
    } catch (Exception e) {
        throw new InternalServerErrorServiceException(e.getMessage());
    }
}
```

Since the validator is autowrired, also all validator classes used by it can be Spring components, which, in turn can have autowired dependencies:

```java
@Component
@Scope("prototype")
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void initialize(UniqueEmail constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && customerRepository.findByEmail(value) == null;
    }
}
```

Finally, service layer methods can also be automatically validated, without having to resort to an autowired validator, if stated so in their interface.

```java
@Validated
public interface CustomerService {

    public CustomerModel createCustomer(@Valid CustomerModel model) throws ServiceException;
    public CustomerModel getCustomerById(Integer id) throws ServiceException;

}
```

