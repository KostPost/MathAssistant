package com.kostpost.mathassistant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Objects;
import java.util.Scanner;
import java.util.Stack;

@SpringBootApplication
public class MathAssistantApplication {

    public static void main(String[] args) throws ScriptException {

        ConfigurableApplicationContext context = SpringApplication.run(MathAssistantApplication.class, args);
        EquationService service = context.getBean(EquationService.class);

        Scanner askAction = new Scanner(System.in);
        String action;
        do {
            System.out.println("1 - Create equation");
            action = askAction.nextLine();

            switch (action) {


                case "1": {
                    Scanner askEquation = new Scanner(System.in);
                    String newEquation = "";

                    do {
                        System.out.println("---------------------------------");
                        System.out.println("Enter new Equation\nEnter 'exit' to back");
                        newEquation = askEquation.nextLine();

                        if (Objects.equals(newEquation, "")) {
                            System.out.println("---------------------------------");
                            System.out.println("Equation can't be null");
                            System.out.println("---------------------------------");
                        } else if (Objects.equals(newEquation, "exit")) break;

                    } while (newEquation == null || newEquation.isEmpty());

                    if (!newEquation.equals("exit")) {

                        Equation createEquation = new Equation();
                        createEquation.setEquation(newEquation);
                        boolean isValid = createEquation.checkEquation();


                        Scanner askX = new Scanner(System.in);
                        String actionX = "";
                        if (isValid) {
                            ////////////////////////// VALID //////////////////////////////
                            System.out.println("---------------------------------");
                            System.out.println("This equation is valid");
                            System.out.println("---------------------------------");

                            do {
                                System.out.println("Would u like to enter a x? y/n");
                                actionX = askX.nextLine();


                            } while (!(actionX.equals("y") || actionX.equals("n")));
                        } else {
                            ////////////////////////// NOT VALID //////////////////////////////
                            System.out.println("---------------------------------");
                            System.out.println("This equation is not valid");
                            System.out.println("---------------------------------");
                        }


                        double setX = 0;
                        boolean correctX = false;
                        if (Objects.equals(actionX, "y")) {


                            do {
                                try {
                                    System.out.println("---------------------------------");
                                    System.out.println("Enter a x for equation: '" + newEquation + "'");
                                    setX = askX.nextDouble();
                                    break;
                                } catch (Exception e) {
                                    System.out.println("Invalid input. Please enter a valid number.");
                                    askX.nextLine();
                                }
                            } while (true);


                            String updatedEquation = newEquation.replace("x", String.valueOf(setX));

                            String[] parts = updatedEquation.split("=");

                            Equation leftEquation = new Equation();
                            leftEquation.setEquation(parts[0].trim());
                            double leftResult = leftEquation.resolveExpression();

                            Equation rightEquation = new Equation();
                            leftEquation.setEquation(parts[1].trim());
                            double rightResult = leftEquation.resolveExpression();


                            if (leftResult == rightResult) {
                                ////////////////// CORRECT X //////////////////////////
                                System.out.println("Equation: " + newEquation + "' with x: " + setX + "' is correct");

                                Equation finalEquation = new Equation();
                                finalEquation.setEquation(newEquation);
                                finalEquation.setXForEquation(String.valueOf(setX));

                                service.addEquation(finalEquation);
                                break;

                            } else {

                                System.out.println("Equation: " + newEquation + "' with x: " + setX + "' is not correct");
                                System.out.println("Must be: " + rightResult + ", but it's: " + leftResult);
                            }


                        } else  if (Objects.equals(actionX, "n")) {
                            Equation finalEquation = new Equation();
                            finalEquation.setEquation(newEquation);
                            finalEquation.setXForEquation("-");

                            service.addEquation(finalEquation);
                        }
                    }

                    break;
                }

                default: {
                    System.out.println("error");
                }
            }

        } while (!Objects.equals(action, "3"));


        context.close();
        SpringApplication.run(MathAssistantApplication.class, args);
    }


}
