package com.geeksforless.mathhelper.command;

import com.geeksforless.mathhelper.service.EquationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Scanner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SearchEquationOptionTest {
    @Mock
    private EquationService equationService;

    @Mock
    private Scanner scanner;

    @InjectMocks
    private SearchEquationOption searchEquationOption;

    @Test
    void whenUserInputsOne_searchByRoot() {
        when(scanner.nextLine()).thenReturn("1","0");
        when(equationService.getEquationsByRoot(0)).thenReturn(List.of("x=0"));

        searchEquationOption.execute();

        verify(equationService).getEquationsByRoot(0);
    }

    @Test
    void whenUserInputsTwo_searchBySameRoot() {
        when(scanner.nextLine()).thenReturn("2");

        searchEquationOption.execute();

        verify(equationService).getEquationsThatHaveTheSameRoot();
    }
}