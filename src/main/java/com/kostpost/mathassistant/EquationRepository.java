package com.kostpost.mathassistant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface EquationRepository extends JpaRepository<Equation, Long> {

    ArrayList<Equation> findByEquation(String equation);

    ArrayList<Equation> findByRootCount(long rootCount);

    ArrayList<Equation> findByRootEquation(String RootEquation);
}
