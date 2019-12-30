package com.epam.ryabov.javabasics.utility;

public enum Bracket {

    OPENBRACKET('(') {
        @Override
        public Double operate(Double b, Double a) {
            return null;
        }

        @Override
        public int priorityLvl(char aChar) {
            return 0;
        }
    },
    CLOSEBRACKET(')') {
        @Override
        public Double operate(Double b, Double a) {
            return null;
        }

        @Override
        public int priorityLvl(char aChar) {
            return 1;
        }
    };

    private char aChar;

    public abstract Double operate(Double b, Double a);

    public abstract int priorityLvl(char aChar);

    Bracket(char aChar) {
        this.aChar = aChar;
    }

    public char getChar() {
        return aChar;
    }
}
