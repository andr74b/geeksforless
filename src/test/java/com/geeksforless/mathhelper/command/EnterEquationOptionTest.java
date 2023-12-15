package com.geeksforless.mathhelper.command;

import com.geeksforless.mathhelper.service.EquationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Scanner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EnterEquationOptionTest {

    @Mock
    private EquationService equationService;

    @Mock
    private Scanner scanner;

    @InjectMocks
    private EnterEquationOption enterEquationOption;

    @Test
    void execute_shouldCallServiceMethods() {
        when(scanner.nextLine()).thenReturn("x=0", "0");
        when(equationService.isValidRoot(anyString(), any(Double.class))).thenReturn(true);

        enterEquationOption.execute();

        verify(equationService, times(1)).saveEquation(anyString(), any(), any());
        verify(equationService, times(1)).updateEquation(anyString(), any(Double.class), any(Double.class));
    }
}