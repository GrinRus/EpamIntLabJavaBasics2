package com.epam.ryabov.javabasics.service.impl;

import com.epam.ryabov.javabasics.service.Calculator;
import com.epam.ryabov.javabasics.utility.Bracket;
import com.epam.ryabov.javabasics.utility.Operations;

import java.util.Arrays;
import java.util.Optional;
import java.util.Stack;
import java.util.regex.Pattern;

public class CalculatorImpl implements Calculator {

    @Override
    public char[] parseString(String string) {

        if (Pattern.matches(".*[a-zA-Z]+.*", string)) {
            throw new RuntimeException("Ошибка парсинга");
        }
        String res = string.replace(",", ".");
        return res.replaceAll("\\s+", " ").toCharArray();
    }

    private void appendSpace(char[] chars, StringBuilder stringBuilder, int i) {
        if (chars[i] == ' ') {
            stringBuilder.append(' ');
        }
    }

    private int resRes(char[] chars, StringBuilder stringBuilder, int i) {
        String regex = "[0-9]|[.]|[ ]";
        while (Pattern.matches(regex, String.valueOf(chars[i]))) {
            if (chars[i] == ' ') {
                stringBuilder.append(' ');
                break;
            } else {
                stringBuilder.append(chars[i]);
                i++;
            }
        }
        return i;
    }

    private boolean enumLoop(char aChar) {
        return Arrays.stream(Operations.values()).anyMatch(operations -> operations.getChar() == aChar);
    }

    private int enumLoopPrivateLvl(char aChar) {
/*        return Arrays.stream(Operations.values())
                .filter(operations -> operations.getChar() == aChar)
                .findFirst()
                .map(operations -> operations.priorityLvl(aChar))
                .orElse(0);*/
        for (Operations operations : Operations.values()){
            if (operations.getChar() == aChar){
                return operations.priorityLvl(aChar);
            }
        }
        return 0;
    }

    private Double enumLoopOperation(char aChar, Double b, Double a) {
        return Arrays.stream(Operations.values()).filter(operations -> operations.getChar() == aChar).findFirst().map(operations -> operations.operate(b, a)).orElse(0.0);
/*        for (Operations operations : Operations.values()) {
            if (operations.getChar() == aChar) {
                return operations.operate(b, a);
            }
        }
        return 0.0;*/
    }

    @Override
    public char[] transformationToReversePolishNotation(String string) {
        char[] chars = parseString(string);
        Stack<Character> stack = new Stack<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            appendSpace(chars, stringBuilder, i);
            if (stack.empty()) {
                if (enumLoop(chars[i]) || chars[i] == Bracket.OPENBRACKET.getChar() || chars[i] == Bracket.CLOSEBRACKET.getChar()) {
                    stack.push(chars[i]);
                } else {
                    i = resRes(chars, stringBuilder, i);
                }
            } else {
                if (chars[i] == Bracket.CLOSEBRACKET.getChar()) {
                    while (stack.peek() != Bracket.OPENBRACKET.getChar()) {
                        stringBuilder.append(stack.pop());
                        if (stack.peek() == Bracket.OPENBRACKET.getChar()) {
                            stack.pop();
                            break;
                        }
                    }
                } else if (chars[i] == Bracket.OPENBRACKET.getChar()) {
                    stack.push(chars[i]);
                } else if (enumLoop(chars[i])) {
                    if (enumLoopPrivateLvl(chars[i]) <= enumLoopPrivateLvl(stack.peek())) {
                        stringBuilder.append(stack.pop());
                        stack.push(chars[i]);
                    } else {
                        stack.push(chars[i]);
                    }
                } else {
                    i = resRes(chars, stringBuilder, i);
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
            } else if (enumLoop(chars[i])) {
                stack.push(enumLoopOperation(chars[i], stack.pop(), stack.pop()));
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