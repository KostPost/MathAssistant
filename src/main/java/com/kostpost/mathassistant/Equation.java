package com.kostpost.mathassistant;

import java.util.Stack;

public class Equation {

    private String equation;

    private String equationX;

    public String getEquationX() {
        return equationX;
    }

    public void setEquationX(String equationX) {
        this.equationX = equationX;
    }

    public String getEquation() {
        return equation;
    }

    public void setEquation(String equation) {
        this.equation = equation;
    }











    ///////////////////////////////////// CHECK ///////////////////////////////////////////////
    private boolean validateEquation(String equation, boolean isValid) {
        if (!checkBrackets(equation)) {
            return false;
        }

        if (!isOperatorSequenceValid(equation)) {
            return false;
        }

        return true;
    }

    private  boolean checkBrackets(String equation) {
        Stack<Character> stack = new Stack<>();

        for (char c : equation.toCharArray()) {
            if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                if (stack.isEmpty() || stack.pop() != '(') {
                    return false; // Невірне закриття дужок
                }
            }
        }

        return stack.isEmpty(); // Повертає true, якщо всі дужки були коректно закриті
    }

    public static boolean isOperatorSequenceValid(String equation) {
        for (int i = 0; i < equation.length() - 1; i++) {
            char currentChar = equation.charAt(i);
            char nextChar = equation.charAt(i + 1);

            if ((currentChar == '+' || currentChar == '-' || currentChar == '*' || currentChar == '/')
                    && (nextChar == '+' || nextChar == '-' || nextChar == '*' || nextChar == '/')) {
                return false;
            }
        }
        return true; // Якщо не знайдено два оператори підряд, повертаємо true
    }

    private String removeExtraSpaces(String task) {
        // Видаляємо зайві пробіли між операторами та числами
        return task.replaceAll("\\s*(\\+|\\-|\\*|\\/|=)\\s*", "$1");
    }

    public boolean checkEquation(){
        boolean isValid = false;
        isValid = validateEquation(equation, isValid);

        return isValid;
    }
    ///////////////////////////////////// CHECK ///////////////////////////////////////////////
}
