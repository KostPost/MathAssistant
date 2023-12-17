package com.kostpost.mathassistant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.kostpost.mathassistant.Equation;
import com.kostpost.mathassistant.EquationRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
public class EquationService {

    private final EquationRepository equationRepository;

    @Autowired
    public EquationService(EquationRepository equationRepository){
        this.equationRepository = equationRepository;
    }

    public Equation addEquation(Equation newEquation){
        return equationRepository.save(newEquation);
    }

    public ArrayList<Equation> findByEquation(String equation){
        return equationRepository.findByEquation(equation);
    }
    public ArrayList<Equation> findByRootCount(long rootCount){
        return equationRepository.findByRootCount(rootCount);
    }
    public ArrayList<Equation> findByRootEquation(String rootEquation){
        return equationRepository.findByRootEquation(rootEquation);
    }

    public void Print(List<Equation> equations){
        System.out.println("----------------------------------------");
        for(Equation equation : equations ){
            System.out.println("Equation: " + equation.getEquation());
            System.out.println("Root Equation: " + equation.getRootEquation());
            System.out.println("Root Count: " + equation.getRootCount());
            System.out.println("----------------------------------------");
        }
    }

    public void Print(Equation equation){
        System.out.println("----------------------------------------");
        System.out.println("Equation: " + equation.getEquation());
        System.out.println("Root Equation: " + equation.getRootEquation());
        System.out.println("Root Count: " + equation.getRootCount());
        System.out.println("----------------------------------------");
    }

}
