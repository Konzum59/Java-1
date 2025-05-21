package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class ProblemTest {
    private Problem problem;
    private final int TEST_SEED = 123;
    private final int TEST_ITEMS_COUNT = 5;
    private final int LOWER_BOUND = 1;
    private final int UPPER_BOUND = 10;

    @BeforeEach
    void setUp() {
        problem = new Problem(TEST_ITEMS_COUNT, TEST_SEED, LOWER_BOUND, UPPER_BOUND);
    }

    @Test
    void constructorShouldCreateCorrectNumberOfItems() {
        assertEquals(TEST_ITEMS_COUNT, problem.getPrzedmioty().size());
    }

    @Test
    void itemsShouldHaveValuesAndWeightsWithinBounds() {
        List<Przedmiot> items = problem.getPrzedmioty();
        for (Przedmiot item : items) {
            assertTrue(item.getWartosc() >= LOWER_BOUND && item.getWartosc() <= UPPER_BOUND,
                    "Wartość przedmiotu poza zakresem");
            assertTrue(item.getWaga() >= LOWER_BOUND && item.getWaga() <= UPPER_BOUND,
                    "Waga przedmiotu poza zakresem");
        }
    }

    @Test
    void solveShouldReturnAtLeastOneItemWhenCapacityIsSufficient() {
        Result result = problem.solve(20);
        assertFalse(result.getNumeryPrzedmiotow().isEmpty(),
                "Powinien zwrócić przynajmniej jeden przedmiot gdy pojemność jest wystarczająca");
        assertTrue(result.getSumaWag() > 0,
                "Sumaryczna waga powinna być większa od zera");
    }

    @Test
    void solveShouldReturnEmptyResultWhenCapacityIsZero() {
        Result result = problem.solve(0);
        assertTrue(result.getNumeryPrzedmiotow().isEmpty(),
                "Nie powinien zwrócić żadnych przedmiotów gdy pojemność jest zerowa");
        assertEquals(0, result.getSumaWag(),
                "Sumaryczna waga powinna być zerowa");
        assertEquals(0, result.getSumaWartosci(),
                "Sumaryczna wartość powinna być zerowa");
    }

    @Test
    void solveShouldReturnCorrectResultForSpecificInstance() {
        Problem specificProblem = new Problem(3, 42, 1, 10);

        // Dla ziarna 42 i 3 przedmiotów powinniśmy dostać:
        // No: 0 v: 1 w: 4
        // No: 1 v: 9 w: 5
        // No: 2 v: 1 w: 6

        Result result = specificProblem.solve(10);



        assertEquals(1, result.getNumeryPrzedmiotow().size());
        assertEquals(2, (int)result.getIlosci().get(0));
        assertEquals(10, result.getSumaWag());
        assertEquals(18, result.getSumaWartosci());
    }

    @Test
    void constructorShouldThrowExceptionForInvalidBounds() {
        assertThrows(IllegalArgumentException.class,
                () -> new Problem(5, 123, 10, 1),
                "Powinien rzucić wyjątek gdy dolny zakres jest większy od górnego");
    }

    @Test
    void solveShouldThrowExceptionForNegativeCapacity() {
        assertThrows(IllegalArgumentException.class,
                () -> problem.solve(-1),
                "Powinien rzucić wyjątek dla ujemnej pojemności plecaka");
    }
}