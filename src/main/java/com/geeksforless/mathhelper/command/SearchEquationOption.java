package com.geeksforless.mathhelper.command;

import com.geeksforless.mathhelper.exception.CustomValidationInputException;
import com.geeksforless.mathhelper.service.EquationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static com.geeksforless.mathhelper.constant.Constants.INNER_MENU;
import static com.geeksforless.mathhelper.constant.Constants.OPTION_ONE;
import static com.geeksforless.mathhelper.constant.Constants.OPTION_TWO;

/**
 * Represents the command to search for equations based on certain criteria.
 * The command will display a menu to users and based on their input perform several tasks.
 */
@Component
@RequiredArgsConstructor
public class SearchEquationOption implements MenuOption {

    private final EquationService equationService;

    private final Scanner scanner;

    /**
     * Executes the SearchEquationOption command.
     */
    @Override
    public void execute() {
        displayMenu();

        String input = scanner.nextLine();
        executeOption(input);
    }

    /**
     * Executes an action based on the given user input option.
     * @param option The option selected by the user.
     */
    private void executeOption(String option) {

        if (OPTION_ONE.equals(option)) {
            searchEquationsByRoot();
            return;
        }

        if (OPTION_TWO.equals(option)) {
            displayEquationsWithSameRoot();
            return;
        }

        throw new CustomValidationInputException("Incorrect option is chosen!");
    }

    /**
     * Prompts the user to enter a root to search for equations where root is a solution.
     * Then, it searches for such equations and prints the results.
     */
    private void searchEquationsByRoot() {
        System.out.println("Enter the root to search:");
        double root = Double.parseDouble(scanner.nextLine());
        System.out.println(equationService.getEquationsByRoot(root));
    }

    /**
     * Searches and prints all equations that have the same root.
     */
    private void displayEquationsWithSameRoot() {
        System.out.println(equationService.getEquationsThatHaveTheSameRoot());
    }

    /**
     * Displays the search menu to the user.
     */
    private void displayMenu() {
        System.out.println(INNER_MENU);
    }
}
