package com.geeksforless.mathhelper.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents an equation model in the application.
 * It is mapped to a database table (by default the table name will be equation_model).
 */
@Entity
@Data
@NoArgsConstructor
public class EquationModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String equation;
    private Double firstRoot;
    private Double secondRoot;

    /**
     * Constructor to create a new EquationModel with provided equation and roots.
     *
     * @param equation Desired equation for the model.
     * @param firstRoot Desired first root for the model.
     * @param secondRoot Desired second root for the model.
     */
    public EquationModel(String equation, Double firstRoot, Double secondRoot) {
        this.equation = equation;
        this.firstRoot = firstRoot;
        this.secondRoot = secondRoot;
    }
}
