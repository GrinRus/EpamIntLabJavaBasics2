package com.epam.ryabov.javabasics.service;

public interface Calculator {
    char[] parseString(String string);

    Double operation(char o, Double b, Double a);

    int priorityLvl(char a);

    char[] transformationToReversePolishNotation(String string);

    Double calcReversePolishNotation(String string);
}
