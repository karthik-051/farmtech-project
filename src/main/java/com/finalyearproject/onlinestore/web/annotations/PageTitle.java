package com.mitron.onlinestore.web.annotations;

import static com.mitron.onlinestore.util.constants.AppConstants.EMPTY_STRING;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface PageTitle {

    String value() default EMPTY_STRING;
}
