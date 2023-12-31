package com.kostpost.mathassistant;

import org.hibernate.mapping.Array;
import org.hibernate.mapping.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

@SpringBootApplication
public class MathAssistantApplication {

    public static void main(String[] args) throws ScriptException {

        ConfigurableApplicationContext context = SpringApplication.run(MathAssistantApplication.class, args);
        EquationService service = context.getBean(EquationService.class);

        Scanner askAction = new Scanner(System.in);
        String action;
        do {
            System.out.println("""
            1 - Create equation
            2 - Find equation
            3 - Exit""");
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


                        Scanner askRoot = new Scanner(System.in);
                        String actionRoot = "";
                        if (isValid) {
                            ////////////////////////// VALID //////////////////////////////
                            System.out.println("---------------------------------");
                            System.out.println("This equation is valid");
                            System.out.println("---------------------------------");

                            do {
                                System.out.println("Would u like to enter a x? y/n");
                                actionRoot = askRoot.nextLine();


                            } while (!(actionRoot.equals("y") || actionRoot.equals("n")));
                        } else {
                            ////////////////////////// NOT VALID //////////////////////////////
                            System.out.println("---------------------------------");
                            System.out.println("This equation is not valid");
                            System.out.println("---------------------------------");
                        }

                        double setRoot = 0;
                        Equation finalEquation = new Equation();
                        if (Objects.equals(actionRoot, "y")) {


                            do {
                                try {
                                    System.out.println("---------------------------------");
                                    System.out.println("Enter a x for equation: '" + newEquation + "'");
                                    setRoot = askRoot.nextDouble();
                                    break;
                                } catch (Exception e) {
                                    System.out.println("Invalid input. Please enter a valid number.");
                                    askRoot.nextLine();
                                }
                            } while (true);


                            String updatedEquation = newEquation.replace("x", String.valueOf(setRoot));

                            String[] parts = updatedEquation.split("=");

                            Equation leftEquation = new Equation();
                            leftEquation.setEquation(parts[0].trim());
                            double leftResult = leftEquation.resolveExpression();

                            Equation rightEquation = new Equation();
                            rightEquation.setEquation(parts[1].trim());
                            double rightResult = leftEquation.resolveExpression();


                            if (leftResult == rightResult) {
                                ////////////////// CORRECT ROOT //////////////////////////
                                System.out.println("Equation: " + newEquation + "' with x: " + setRoot + "' is correct");

                                finalEquation.setEquation(newEquation);
                                finalEquation.setRootEquation(String.valueOf(setRoot));
                                finalEquation.setRootCount();

                                service.addEquation(finalEquation);
                                break;

                            } else {
                                System.out.println("Equation: " + newEquation + "' with x: " + setRoot + "' is not correct");
                                System.out.println("Must be: " + rightResult + ", but it's: " + leftResult);
                            }


                        } else if (Objects.equals(actionRoot, "n")) {

                            finalEquation.setEquation(newEquation);
                            finalEquation.setRootEquation("-");
                            finalEquation.setRootCount();

                            service.addEquation(finalEquation);
                        }
                    }

                    break;
                }

                case "2": {
                    Scanner askRootFind = new Scanner(System.in);
                    String doRootFind;

                    Scanner askDataForFind = new Scanner(System.in);


                    ArrayList<Equation> equationFind = new ArrayList<>();
                    do {
                        System.out.println("""
                                1 - Find by equation
                                2 - Find by root count
                                3 - Find by root
                                4 - Exit""");
                        doRootFind = askRootFind.nextLine();


                        switch (doRootFind){

                            case "1":{
                                String findByEquation = "";
                                System.out.println("Enter equation for find");
                                findByEquation = askDataForFind.nextLine();


                                equationFind = service.findByEquation(findByEquation);

                                if(equationFind != null){
                                    service.Print(equationFind);
                                }

                                break;
                            }

                            case "2":{
                                long findByRootCount;
                                System.out.println("Enter root count equation for find\nEnter: '-' for find equation with unknown root");
                                findByRootCount = askDataForFind.nextLong();


                                equationFind = service.findByRootCount(findByRootCount);

                                if(equationFind != null){
                                    service.Print(equationFind);
                                }

                                break;
                            }

                            case "3":{

                                String findByRoot = "";
                                System.out.println("Enter equation for find");
                                findByRoot = askDataForFind.nextLine();


                                equationFind = service.findByRootEquation(findByRoot);

                                if(equationFind != null){
                                    service.Print(equationFind);
                                }

                                break;
                            }

                            default:{
                                if(!doRootFind.equals("4")){
                                }
                                break;
                            }

                        }

                    }while (!Objects.equals(doRootFind, "4"));

                    
                }

                default: {
                }
            }

        } while (!Objects.equals(action, "3"));


        context.close();
        SpringApplication.run(MathAssistantApplication.class, args);
    }


}
