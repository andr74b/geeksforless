package com.geeksforless.mathhelper.component;

import com.geeksforless.mathhelper.command.MenuOption;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static com.geeksforless.mathhelper.constant.Constants.OPTION_ONE;
import static com.geeksforless.mathhelper.constant.Constants.OPTION_TWO;
import static com.geeksforless.mathhelper.constant.Constants.OPTION_ZERO;
import static com.geeksforless.mathhelper.constant.Constants.OUTER_MENU;

/**
 * The MenuComponent is the entry point for the application menu.
 * It displays the menu options and delegates the selected menu option to the corresponding command
 * using the Command design pattern. Options are collected from the Spring ApplicationContext and
 * each should implement the MenuOption interface.
 */
@Component
@RequiredArgsConstructor
public class MenuComponent implements CommandLineRunner {

    private Map<String, MenuOption> menuOptions;

    private Scanner scanner;

    private final ApplicationContext applicationContext;

    /**
     * Initializes the MenuComponent.
     * This method is called after the constructor and sets up the menu options and the Scanner.
     */
    @PostConstruct
    private void init() {
        this.scanner = new Scanner(System.in);
        this.menuOptions = new HashMap<>();

        Map<String, MenuOption> menuOptionBeans = applicationContext.getBeansOfType(MenuOption.class);
        this.menuOptions.put("0", () -> System.exit(0));
        this.menuOptions.put("1", menuOptionBeans.get("enterEquationOption"));
        this.menuOptions.put("2", menuOptionBeans.get("searchEquationOption"));
    }

    /**
     * Main method to start the application menu.
     * It runs indefinitely until the application is stopped, continuously accepting user input
     * and executing the corresponding menu command. If an exception occurs while processing a command,
     * it prints the error message and continues to the next iteration.
     * @param args Command line arguments. This parameter is not used.
     */
    @Override
    public void run(String... args) {
        while (true) {
            System.out.println(OUTER_MENU);
            String input = scanner.nextLine();

            try {
                if (input.equals(OPTION_ONE)) {
                    menuOptions.get(OPTION_ONE).execute();
                }

                if (input.equals(OPTION_TWO)) {
                    menuOptions.get(OPTION_TWO).execute();
                }

                if (input.equals(OPTION_ZERO)) {
                    menuOptions.get(OPTION_ZERO).execute();
                }
            } catch (RuntimeException exception) {
                System.out.println("Error: " + exception.getMessage());
            }
        }
    }
}
