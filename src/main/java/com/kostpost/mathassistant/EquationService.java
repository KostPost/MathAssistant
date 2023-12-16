package com.kostpost.mathassistant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.kostpost.mathassistant.Equation;
import com.kostpost.mathassistant.EquationRepository;
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

}
