package com.geeksforless.mathhelper.command;

import com.geeksforless.mathhelper.exception.CustomValidationInputException;
import com.geeksforless.mathhelper.service.EquationService;
import com.geeksforless.mathhelper.util.InputUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Scanner;

/**
 * This command is responsible for entering an equation and its roots.
 * The equation and roots are entered by the user via standard input.
 * After entering the equation and roots, the command validates the input and saves it to the system.
 */
@Component
@RequiredArgsConstructor
public class EnterEquationOption implements MenuOption {

    private final EquationService equationService;

    private final Scanner scanner;

    /**
     * Executes the EnterEquationOption command.
     */
    @Override
    public void execute() {
        String equation = promptAndParseEquation();
        Double[] roots = promptAndParseRoots();
        saveRootsIfValid(equation, roots);
    }

    /**
     * Prompts the user to enter an equation, parses that equation, validates it, and saves it.
     * @return The equation that has been entered.
     */
    private String promptAndParseEquation() {
        System.out.println("Enter equation:");
        String equation = scanner.nextLine();

        if (!InputUtility.isValidInput(equation)) {
            throw new CustomValidationInputException("Your equation is not valid...");
        }

        equationService.saveEquation(equation, null, null);

        return equation;
    }

    /**
     * Prompts the user to enter the roots of the equation, and then parses those roots.
     * @return The roots that have been entered.
     */
    private Double[] promptAndParseRoots() {
        System.out.println("Enter roots, divided by comma");

        return Arrays.stream(scanner.nextLine().split(","))
                .map(String::trim)
                .map(Double::parseDouble)
                .toArray(Double[]::new);
    }

    /**
     * Validates the roots of an equation and saves them.
     * If a root is invalid, a CustomValidationInputException is thrown.
     *
     * @param equation The equation for which the roots need to be validated.
     * @param roots The roots to be validated.
     */
    private void saveRootsIfValid(String equation, Double[] roots) {
        for (Double root : roots) {
            if (!equationService.isValidRoot(equation, root)) {
                throw new CustomValidationInputException("Root " + root + " is not valid for the equation...");
            }
        }

        Double root1 = roots[0];
        Double root2 = roots.length == 2 ? roots[1] : roots[0];
        equationService.updateEquation(equation, root1, root2);
    }
}
