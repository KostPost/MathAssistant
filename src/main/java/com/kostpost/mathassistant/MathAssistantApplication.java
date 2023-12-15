package com.kostpost.mathassistant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Objects;
import java.util.Scanner;
import java.util.Stack;

@SpringBootApplication
public class MathAssistantApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(MathAssistantApplication.class, args);
//
//		Equation equation = new Equation();
//        equation.setEquation("1 + -2");
//        System.out.println(equation.checkEquation())

        Scanner askAction = new Scanner(System.in);
        String action;
        do {
            System.out.println("1 - Create equation");
            action = askAction.nextLine();

            switch (action) {


                case "1": {
                    Scanner askEquation = new Scanner(System.in);
                    String newEquation = null;

                    do {
                        System.out.println("---------------------------------");
                        System.out.println("Enter new Equation\nEnter 'exit' to back");
                        newEquation = askEquation.nextLine();

                        if (Objects.equals(newEquation, "")) {
                            System.out.println("---------------------------------");
                            System.out.println("Equation can't be null");
                            System.out.println("---------------------------------");
                        }
						else if(Objects.equals(newEquation, "exit")) break;

                    } while (newEquation == null || newEquation.isEmpty());

                    if (!newEquation.equals("exit")) {

                        Equation createEquation = new Equation();
                        createEquation.setEquation(newEquation);
                        boolean isValid = createEquation.checkEquation();


                        Scanner askX = new Scanner(System.in);
                        String actionX = "";
                        if (isValid) {
                            System.out.println("---------------------------------");
                            System.out.println("This equation is valid");
                            System.out.println("---------------------------------");

                            do{
                                System.out.println("Would u like to enter a x? y/n");
                                actionX = askX.nextLine();


                            }while (!(actionX.equals("y") || actionX.equals("n")));
                        } else {
                            System.out.println("---------------------------------");
                            System.out.println("This equation is not valid");
                            System.out.println("---------------------------------");
                        }

                        if(Objects.equals(actionX, "y")){
                            System.out.println("---------------------------------");
                            System.out.println("Enter a x for equation: '" + newEquation + "'");


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
