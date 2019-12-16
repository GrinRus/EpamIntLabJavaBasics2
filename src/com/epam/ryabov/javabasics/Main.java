package com.epam.ryabov.javabasics;

import com.epam.ryabov.javabasics.service.DemoService;
import com.epam.ryabov.javabasics.service.impl.DemoServiceImpl;

public class Main {

    public static void main(String[] args) {
        DemoService demoService = new DemoServiceImpl();
        demoService.demoService();
    }
}
