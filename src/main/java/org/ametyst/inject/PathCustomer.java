package org.ametyst.inject;

import java.lang.annotation.*;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PathCustomer {
    String value() default "customerId";
}