package com.example.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Gao on 2018/12/7.
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.CLASS)
public @interface MyOnClick {
    int value();
}
