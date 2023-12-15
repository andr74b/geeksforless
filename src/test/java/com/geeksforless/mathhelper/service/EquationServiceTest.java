package com.geeksforless.mathhelper.service;

import com.geeksforless.mathhelper.model.EquationModel;
import com.geeksforless.mathhelper.repository.EquationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EquationServiceTest {

    private final static String EQUATION = "2x=2x";

    private final static double ROOT = 1.0;

    @Mock
    private EquationRepository equationRepository;

    @InjectMocks
    private EquationService equationService;

    @Test
    public void testIsValidRoot_True() {
        assertTrue(equationService.isValidRoot(EQUATION, ROOT));
    }

    @Test
    public void testIsValidRoot_False() {
        assertFalse(equationService.isValidRoot("2x=5x", ROOT));
    }

    @Test
    public void testSaveEquation() {
        EquationModel model = new EquationModel(EQUATION, ROOT, null);
        model.setId(1L);
        when(equationRepository.save(new EquationModel(EQUATION, ROOT, null))).thenReturn(model);
        assertEquals((Long)1L, equationService.saveEquation(EQUATION, ROOT, null));
    }

    @Test
    public void testUpdateEquation() {
        EquationModel model = new EquationModel(EQUATION, ROOT, null);
        model.setId(1L);
        when(equationRepository.findByEquation(EQUATION)).thenReturn(model);
        assertEquals((Long)1L, equationService.updateEquation(EQUATION, 2.0, 3.0));
    }

    @Test
    public void testGetEquationsByRoot() {
        List<String> expected = List.of(EQUATION);
        when(equationRepository.getEquationsByRoot((double)1)).thenReturn(expected);
        assertEquals(expected, equationService.getEquationsByRoot(1));
    }

    @Test
    public void testGetEquationsThatHaveTheSameRoot() {
        List<String> expected = List.of(EQUATION);
        when(equationRepository.getEquationsThatHaveTheSameRoot()).thenReturn(expected);
        assertEquals(expected, equationService.getEquationsThatHaveTheSameRoot());
    }
}