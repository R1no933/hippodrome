import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class HippordromeTest {
    @Test
    public void nullListException() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    public void cannotBeNullExceptionMessage() {
        try {
            new Hippodrome(null);
        } catch (IllegalArgumentException error) {
            assertEquals("Horses cannot be null.", error.getMessage());
        }
    }

    @Test
    public void emptyListException() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
    }

    @Test
    public void emptyListExceptionMessage() {
        try {
            new Hippodrome(new ArrayList<>());
        } catch (IllegalArgumentException error) {
            assertEquals("Horses cannot be empty.", error.getMessage());
        }
    }

    @Test
    public void getHorsesTest() {
        List<Horse> horseList = new ArrayList<>();
        for (int i = 1; i < 30; i++) {
            horseList.add(new Horse("" + i, i, i));
        }

        Hippodrome hippodrome = new Hippodrome(horseList);
        assertEquals(horseList, hippodrome.getHorses());
    }

    @Test
    public void moveTest() {
        List<Horse> horseMockList = new ArrayList<>();
        for (int i = 1; i < 50; i++) {
            horseMockList.add(mock(Horse.class));
        }

        new Hippodrome(horseMockList).move();

        for (Horse horse: horseMockList) {
            verify(horse).move();
        }
    }

    @Test
    public void getWinnerTest() {
        Horse firstHorse = new Horse("horse1", 1, 5.001);
        Horse secondHorse = new Horse("horse2", 1, 5.010);
        Horse thirdHorse = new Horse("horse3", 1, 5.100);
        Horse fourthHorse = new Horse("horse4", 1, 5.101);

        Hippodrome hippodrome = new Hippodrome(List.of(firstHorse, secondHorse,thirdHorse,fourthHorse));

        assertSame(fourthHorse, hippodrome.getWinner());
    }
}
