package com.epam.ryabov.javabasics.service.impl;

import com.epam.ryabov.javabasics.service.Calculator;
import com.epam.ryabov.javabasics.service.DemoService;

public class DemoServiceImpl implements DemoService {

    private Calculator calculator = new CalculatorImpl();

    @Override
    public void demoService() {
        System.out.println(calculator.calcReversePolishNotation("( 15 / 3 + 11 - 3 * 5 ) / 3.2 * ( 5.6 - 10 )"));
    }
}
