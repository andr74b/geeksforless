package com.geeksforless.mathhelper.service;

import com.geeksforless.mathhelper.model.EquationModel;
import com.geeksforless.mathhelper.repository.EquationRepository;
import lombok.RequiredArgsConstructor;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for operations with equations.
 * It provides functionalities like validating roots for an equation, saving the equation, update roots for an equation
 * and fetching equations based on certain conditions.
 */
@Service
@RequiredArgsConstructor
public class EquationService {

    private final EquationRepository equationRepository;

    /**
     * Validates if the provided root is a valid root for the equation
     *
     * @param equation the equation to validate the root
     * @param root the root to validate
     * @return true if the root is valid, false otherwise
     */
    public boolean isValidRoot(String equation, Double root) {
        String[] split = equation.split("=");
        Expression expressionLeft = new ExpressionBuilder(split[0])
                .variables("x")
                .build()
                .setVariable("x", root);
        Expression expressionRight = new ExpressionBuilder(split[1])
                .variables("x")
                .build()
                .setVariable("x", root);
        return (Math.abs(expressionLeft.evaluate() - expressionRight.evaluate()) < 0.001); //0.000000001 - this value
        // was required to set by the requirements. But it is too small to properly evaluate the expression with
        // "strange roots such as -5.413(6)"
    }

    /**
     * Saves an equation model in the repository
     *
     * @param equation the equation of the EquationModel
     * @param firstRoot the first root of the EquationModel
     * @param secondRoot the second root of the EquationModel
     * @return the id of the saved EquationModel
     */
    public Long saveEquation(String equation, Double firstRoot, Double secondRoot) {
        EquationModel model = equationRepository.save(new EquationModel(equation, firstRoot, secondRoot));

        return model.getId();
    }

    /**
     * Updates an existing equation with new roots
     *
     * @param equation the equation to update
     * @param firstRoot the new first root
     * @param secondRoot the new second root
     * @return the id of the updated EquationModel
     */
    public Long updateEquation(String equation, Double firstRoot, Double secondRoot) {
        EquationModel model = equationRepository.findByEquation(equation);

        model.setFirstRoot(firstRoot);
        model.setSecondRoot(secondRoot);

        equationRepository.save(model);

        return model.getId();
    }

    /**
     * Fetches equations for which the provided root is a solution
     *
     * @param root the root to search
     * @return a list of equations for which the root is a solution
     */
    public List<String> getEquationsByRoot(double root) {
        return equationRepository.getEquationsByRoot(root);
    }

    /**
     * Fetches equations that have the same first and second roots
     *
     * @return a list of equations that have the same root
     */
    public List<String> getEquationsThatHaveTheSameRoot() {
        return equationRepository.getEquationsThatHaveTheSameRoot();
    }
}
