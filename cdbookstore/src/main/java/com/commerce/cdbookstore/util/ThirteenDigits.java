package com.commerce.cdbookstore.util;

import javax.inject.Qualifier;
import java.lang.annotation.*;
 

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER,
        ElementType.TYPE})
@Documented
public @interface ThirteenDigits {
}