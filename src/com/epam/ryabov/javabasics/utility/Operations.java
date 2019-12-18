package com.epam.ryabov.javabasics.utility;

public enum Operations {

    PLUS('+') {
        @Override
        public Double operate(Double b, Double a) {
            return a + b;
        }

        @Override
        public int priorityLvl(char aChar) {
            return 2;
        }
    },
    MINUS('-') {
        @Override
        public Double operate(Double b, Double a) {
            return a - b;
        }

        @Override
        public int priorityLvl(char aChar) {
            return 2;
        }
    },
    MULTIPLY('*') {
        @Override
        public Double operate(Double b, Double a) {
            return a * b;
        }

        @Override
        public int priorityLvl(char aChar) {
            return 3;
        }
    },
    DIVIDE('/') {
        @Override
        public Double operate(Double b, Double a) {
            if (b == 0) {
                throw new RuntimeException("На ноль делить нельзя");
            }
            return a / b;
        }

        @Override
        public int priorityLvl(char aChar) {
            return 3;
        }
    };

    private char aChar;

    public abstract Double operate(Double b, Double a);

    public abstract int priorityLvl(char aChar);

    Operations(char aChar) {
        this.aChar = aChar;
    }

    public char getChar() {
        return aChar;
    }
}
