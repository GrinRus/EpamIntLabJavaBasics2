package com.epam.ryabov.javabasics.service.impl;

import com.epam.ryabov.javabasics.service.Calculator;

import java.util.Stack;
import java.util.regex.Pattern;

public class CalculatorImpl implements Calculator {

    @Override
    public char[] parseString(String string) {

        if (Pattern.matches(".*[a-zA-Z]+.*", string)) {
            throw new RuntimeException("Ошибка парсинга");
        }
        String res = string.replaceAll(",", ".");
        return res.replaceAll("\\s+", " ").toCharArray();
    }

    @Override
    public Double operation(char o, Double b, Double a) {
        switch (o) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0) {
                    throw new RuntimeException("На ноль делить нельзя");
                }
                return a / b;

        }
        return (double) 0;
    }

    @Override
    public int priorityLvl(char a) {
        switch (a) {
            case '(':
                return 0;
            case ')':
                return 1;
            case '+':
            case '-':
                return 2;
            case '*':
            case '/':
                return 3;
        }
        return 0;
    }

    @Override
    public char[] transformationToReversePolishNotation(String string) {
        char[] chars = parseString(string);
        Stack<Character> stack = new Stack<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ' ') {
                stringBuilder.append(' ');
            } else {
                if (stack.empty()) {
                    if (chars[i] == '+' || chars[i] == '-' || chars[i] == '/' || chars[i] == '*' || chars[i] == '(' || chars[i] == ')') {
                        stack.push(chars[i]);
                    } else {
                        while (Pattern.matches("[0-9]|[.]|[ ]", String.valueOf(chars[i]))) {
                            if (chars[i] == ' ') {
                                stringBuilder.append(' ');
                                break;
                            } else {
                                stringBuilder.append(chars[i]);
                                i++;
                            }
                        }
                    }
                } else {
                    if (chars[i] == ')') {
                        while (stack.peek() != '(') {
                            stringBuilder.append(stack.pop());
                            if (stack.peek() == '(') {
                                stack.pop();
                                break;
                            }
                        }
                    } else if (chars[i] == '(') {
                        stack.push(chars[i]);
                    } else if (chars[i] == '+' || chars[i] == '-' || chars[i] == '/' || chars[i] == '*') {
                        if (priorityLvl(chars[i]) <= priorityLvl(stack.peek())) {
                            stringBuilder.append(stack.pop());
                            stack.push(chars[i]);
                        } else {
                            stack.push(chars[i]);
                        }
                    } else if (Pattern.matches("[0-9]|[.]|[ ]", String.valueOf(chars[i]))) {
                        while (Pattern.matches("[0-9]|[.]|[ ]", String.valueOf(chars[i]))) {
                            if (chars[i] == ' ') {
                                stringBuilder.append(' ');
                                break;
                            } else {
                                stringBuilder.append(chars[i]);
                                i++;
                            }
                        }
                    }
                }
            }
        }
        while (!stack.empty()) {
            stringBuilder.append(stack.pop());
        }
        return stringBuilder.toString().toCharArray();
    }

    @Override
    public Double calcReversePolishNotation(String string) {
        char[] chars = transformationToReversePolishNotation(string);
        Stack<Double> stack = new Stack<>();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ' ') {
                continue;
            } else if (chars[i] == '+' || chars[i] == '-' || chars[i] == '/' || chars[i] == '*') {
                stack.push(operation(chars[i], stack.pop(), stack.pop()));
            } else {
                StringBuilder stringBuilder = new StringBuilder();
                while (Pattern.matches("[0-9]|[.]", String.valueOf(chars[i]))) {
                    stringBuilder.append(chars[i]);
                    i++;
                }
                stack.push(Double.parseDouble(String.valueOf(stringBuilder)));
            }
        }
        return stack.pop();
    }
}