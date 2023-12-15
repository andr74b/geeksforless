package com.geeksforless.mathhelper.util;

/**
 * Utility class providing methods to verify input correctness for equation strings.
 */
public class InputUtility {

    /**
     * Checks if the input equation string is valid.
     *
     * @param equation The equation string to check.
     * @return true If the equation string is valid; false otherwise.
     */
    public static boolean isValidInput(String equation) {
        return isParenthesesBalanced(equation)
                &&
                isOperatorsRepeating(equation)
                &&
                isOnlyOneEqualSign(equation)
                &&
                hasOneUnknown(equation);
    }

    /**
     * Checks if operators (+, -, *, /, =) in the equation string aren't repeated consecutively.
     *
     * @param equation The equation string to check.
     * @return true If operators are not repeated consecutively; false otherwise.
     */
    private static boolean isOperatorsRepeating(String equation) {
        return !equation.matches(".*[+/*=]{2,}.*");
    }

    /**
     * Checks if the parentheses in the equation string are balanced.
     *
     * @param equation The equation string to check.
     * @return true If parentheses are balanced; false otherwise.
     */
    private static boolean isParenthesesBalanced(String equation) {
        long leftParenthesesCount = equation.chars().filter(character -> character == '(').count();
        long rightParenthesesCount = equation.chars().filter(character -> character == ')').count();

        return leftParenthesesCount == rightParenthesesCount;
    }

    /**
     * Checks if the equation string contains exactly one equal sign (=).
     *
     * @param equation The equation string to check.
     * @return true If the equation string contains exactly one equal sign; false otherwise.
     */
    private static boolean isOnlyOneEqualSign(String equation) {
        long equalCount = equation.chars().filter(character -> character == '=').count();

        return equalCount == 1;
    }

    /**
     * Checks if the equation string contains at least one variable x.
     *
     * @param equation The equation string to check.
     * @return true If the equation string contains at least one variable x; false otherwise.
     */
    private static boolean hasOneUnknown(String equation) {
        return equation.chars().anyMatch(character -> character == 'x');
    }
}