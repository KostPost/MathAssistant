package com.kostpost.mathassistant;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.solvers.BrentSolver;
import org.apache.commons.math3.analysis.solvers.UnivariateSolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootApplication
public class MathAssistantApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(MathAssistantApplication.class, args);

//		String task = "-1 * (1 + x) = 10";
//		task = removeExtraSpaces(task);

        String[] equations = {
                "2*x+5=17",
                "-1.3*5/x=1.2",
                "2*x*x=10",
                "2*(x+5+х)+5=10",
                "17=2*x+5"
        };

        for (String task : equations) {
            task = removeExtraSpaces(task);

            System.out.println(task);
            System.out.println(validateEquation(task));
			System.out.println(checkBrackets(task));
			System.out.println(checkOperators(task));
            System.out.println("\n");
        }

        context.close();
        SpringApplication.run(MathAssistantApplication.class, args);
    }
	public static boolean validateEquation(String equation) {
		// Перевірка введеного виразу на коректність розміщення дужок
		if (!checkBrackets(equation)) {
			return false;
		}

		// Перевірка коректності введеного виразу (відсутність двох знаків математичних операцій підряд)
		if (!checkOperators(equation)) {
			return false;
		}

		// Перевірка на вміст чисел, операцій та змінної x
		return true;
	}
	private static boolean checkBrackets(String equation) {
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
	private static boolean checkOperators(String equation) {
		for (int i = 0; i < equation.length() - 1; i++) {
			char currentChar = equation.charAt(i);
			char nextChar = equation.charAt(i + 1);

			if ("+-*/".indexOf(currentChar) != -1 && "+-*/".indexOf(nextChar) != -1) {
				return false; // Є два знаки операцій підряд
			}
		}

		return true;
	}
    public static String removeExtraSpaces(String task) {
        // Видаляємо зайві пробіли між операторами та числами
        return task.replaceAll("\\s*(\\+|\\-|\\*|\\/|=)\\s*", "$1");
    }



    //	public static boolean checkEquation(String equation) {
//		// Удаляем пробелы из уравнения
//		equation = equation.replaceAll("\\s+", "");
//
//		// Проверяем, начинается ли уравнение с "+"
//		if (equation.startsWith("+")) {
//			return false; // Уравнение начинается с неверного символа
//		}
//
//		// Проверяем, начинается ли уравнение с "-"
//		if (equation.startsWith("-")) {
//			// Проверяем, что после "-" идет число или "x"
//			char nextChar = equation.charAt(1);
//			if (Character.isDigit(nextChar) || nextChar == 'x') {
//				return true;
//			}
//		}
//
//		// Проверяем, начинается ли уравнение с "x" или числа
//		char firstChar = equation.charAt(0);
//		if (Character.isDigit(firstChar) || firstChar == 'x') {
//			return true;
//		}
//
//		return false; // Уравнение начинается с неверного символа
//	}
//
//	public static boolean checkSide(String side) {
//		// Проверяем, что строка не пустая
//		if (side.isEmpty()) {
//			return false;
//		}
//
//		// Ищем знак "*" или "+"
//		int operatorIndex = side.indexOf("*");
//		if (operatorIndex == -1) {
//			operatorIndex = side.indexOf("+");
//		}
//
//		// Если знак не найден или он находится в начале строки,
//		// либо в конце строки, то возвращаем false
//		if (operatorIndex == -1 || operatorIndex == 0 || operatorIndex == side.length() - 1) {
//			return false;
//		}
//
//		// Получаем символ после знака
//		char nextChar = side.charAt(operatorIndex + 1);
//
//		// Проверяем, что после знака идет число, "x" или отрицательное число
//		if (Character.isDigit(nextChar) || nextChar == 'x' || (nextChar == '-' && operatorIndex + 2 < side.length() && Character.isDigit(side.charAt(operatorIndex + 2)))) {
//			return true;
//		}
//
//		return false;
//	}
//
//	public static boolean checkBrackets(String equation) {
//		Stack<Character> stack = new Stack<>();
//
//		for (char c : equation.toCharArray()) {
//			if (c == '(') {
//				stack.push(c);
//			} else if (c == ')') {
//				if (stack.isEmpty() || stack.pop() != '(') {
//					return false; // Неверное закрытие скобок
//				}
//			}
//		}
//
//		return stack.isEmpty(); // Возвращает true, если все скобки были правильно закрыты
//	}
//	public static boolean validateEquation(String equation) {
//		equation = equation.replaceAll("\\s+", "");
//
//		// Проверяем неверное расположение операторов
//		for (int i = 0; i < equation.length() - 1; i++) {
//			char currentChar = equation.charAt(i);
//			char nextChar = equation.charAt(i + 1);
//
//			if ((currentChar == '+' || currentChar == '-' || currentChar == '*' || currentChar == '/') &&
//					(nextChar == '+' || nextChar == '*' || nextChar == '/')) {
//				return false;
//			}
//		}
//
//		// Проверяем унарные операторы перед числами
//		for (int i = 0; i < equation.length() - 1; i++) {
//			char currentChar = equation.charAt(i);
//			char nextChar = equation.charAt(i + 1);
//
//			if ((currentChar == '+' || currentChar == '-') &&
//					(Character.isDigit(nextChar) || nextChar == '.')) {
//				return false;
//			}
//		}
//
//		// Проверяем скобки
//		int openBracketCount = 0;
//		int closeBracketCount = 0;
//		for (char c : equation.toCharArray()) {
//			if (c == '(') {
//				openBracketCount++;
//			} else if (c == ')') {
//				closeBracketCount++;
//			}
//		}
//
//		if (openBracketCount != closeBracketCount) {
//			return false;
//		}
//
//		// Проверяем наличие переменной x и уравнения в стандартной форме
//		if (equation.contains("x") && equation.matches("^[\\dx\\(\\)\\+\\-\\*/\\.=]+$")) {
//			return true;
//		}
//
//		return false;
//	}

}
