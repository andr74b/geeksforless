package com.geeksforless.mathhelper.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InputUtilityTest {

    @Test
    public void testValidEquation() {
        String equation = "(x+1)=(x*2)";
        assertTrue(InputUtility.isValidInput(equation));
    }

    @Test
    public void testInvalidEquationWithUnbalancedParentheses() {
        String equation = "((x+1=x*2)";
        assertFalse(InputUtility.isValidInput(equation));
    }

    @Test
    public void testInvalidEquationWithRepeatedOperators() {
        String equation = "x++1=x*2";
        assertFalse(InputUtility.isValidInput(equation));
    }

    @Test
    public void testInvalidEquationWithMultipleEqualSigns() {
        String equation = "x=1=x*2";
        assertFalse(InputUtility.isValidInput(equation));
    }

    @Test
    public void testInvalidEquationWithNoUnknowns() {
        String equation = "1+1=2";
        assertFalse(InputUtility.isValidInput(equation));
    }

    @Test
    public void testInvalidEquationWithNoEqualSign() {
        String equation = "x+1*2";
        assertFalse(InputUtility.isValidInput(equation));
    }
}