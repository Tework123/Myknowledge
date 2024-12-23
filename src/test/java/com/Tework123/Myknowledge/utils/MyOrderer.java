package com.Tework123.Myknowledge.utils;

import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.ClassOrdererContext;

import java.util.Collections;

// order class test, create users and get tokens -> then other logic
public class MyOrderer implements ClassOrderer {
    @Override
    public void orderClasses(ClassOrdererContext context) {
        Collections.reverse(context.getClassDescriptors());
    }
}
