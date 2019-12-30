package com.epam.ryabov.javabasics.service;

public interface Calculator {
    char[] parseString(String string);

    char[] transformationToReversePolishNotation(String string);

    Double calcReversePolishNotation(String string);
}
