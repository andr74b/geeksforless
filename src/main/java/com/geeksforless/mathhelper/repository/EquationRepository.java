package com.geeksforless.mathhelper.repository;

import com.geeksforless.mathhelper.model.EquationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Repository interface for operations on EquationModel entities.
 */
public interface EquationRepository extends JpaRepository<EquationModel, Long> {

    /**
     * Retrieves equations in which the specified value is a root.
     *
     * @param root The root value to match in equations.
     * @return List of equations where the provided value is a root.
     */
    @Query("select equation from EquationModel where firstRoot = ?1 or secondRoot = ?1")
    List<String> getEquationsByRoot(Double root);

    /**
     * Retrieves all equations that have the same value for the first and second root.
     *
     * @return List of equations where both roots are the same.
     */
    @Query("select equation from EquationModel where firstRoot = secondRoot")
    List<String> getEquationsThatHaveTheSameRoot();

    /**
     * Finds an equation model by the equation string.
     *
     * @param equation The equation to match.
     * @return An EquationModel with the specified equation.
     * Null if no such equation exists.
     */
    EquationModel findByEquation(String equation);
}
