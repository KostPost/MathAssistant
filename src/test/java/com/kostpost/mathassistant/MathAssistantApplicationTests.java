package com.kostpost.mathassistant;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
@RunWith(SpringRunner.class)
@SpringBootTest
public class MathAssistantApplicationTests {

	@Test
	public void testBracketCheck() {
		Equation equation = new Equation();
		assertTrue(equation.checkBrackets("(1 + 2) * (3 - 4)")); // Правильные скобки
		assertTrue(equation.checkBrackets("((1 + 2) * (3 - 4))")); // Правильные скобки
		assertFalse(equation.checkBrackets("(1 + 2) * (3 - 4")); // Неправильные скобки
		assertFalse(equation.checkBrackets("(1 + 2) * 3 - 4)")); // Неправильные скобки
		assertFalse(equation.checkBrackets("((1 + 2) * (3 - 4)")); // Неправильные скобки
	}

	@Test
	public void testIsOperatorSequenceValid(){
		Equation equation = new Equation();
		assertTrue(equation.isOperatorSequenceValid("1 + x = 10"));
		assertTrue(equation.isOperatorSequenceValid("1 - x = 4 + 3"));
		assertTrue(equation.isOperatorSequenceValid("-1 * (x * 2) = 3"));

		assertFalse(equation.isOperatorSequenceValid("1 + *x = 10"));
		assertFalse(equation.isOperatorSequenceValid("-1 + +x = 14"));
		assertFalse(equation.isOperatorSequenceValid("1 + )x = 3"));

	}

	@Test
	public void isEquationValid(){
		Equation equation = new Equation();
		assertTrue(equation.validateEquation("1 - x = -4"));
		assertTrue(equation.validateEquation("1 - x = -32"));
		assertTrue(equation.validateEquation("1 * (3 + x) = 23 - 3"));

		assertFalse(equation.validateEquation("1 + x == 3"));
		assertFalse(equation.validateEquation("1 + 3 = 10"));
		assertFalse(equation.validateEquation("1 - 23 * )x( = 3"));
	}

}
