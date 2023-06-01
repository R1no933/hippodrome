import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

public class HorseTest {

    @Test
    public void nullNameException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1, 1));
    }

    @Test
    public void cannotBeNullExceptionMessage() {
        try {
            new Horse(null, 1, 1);
        } catch (IllegalArgumentException error) {
            assertEquals("Name cannot be null.", error.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "   ", "\t\t", "\n\n\n\n"})
    public void blankNameExceptin(String name) {
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class, () -> new Horse(name, 1, 1));
        assertEquals("Name cannot be blank.", error.getMessage());
    }

    @Test
    public void speedNotNegativeException() {
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class, () -> new Horse("some name", -1, 1));
        assertEquals("Speed cannot be negative.", error.getMessage());
    }

    @Test
    public void distanceNotNegativeException() {
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class, () -> new Horse("some name", 1, -1));
        assertEquals("Distance cannot be negative.", error.getMessage());
    }

    @Test
    public void getNameTest() throws NoSuchFieldException, IllegalAccessException {
        Horse horse = new Horse("qwerty", 1, 1);

        Field horseName = Horse.class.getDeclaredField("name");
        horseName.setAccessible(true);
        String fieldValue = (String) horseName.get(horse);
        assertEquals("qwerty", fieldValue);
    }

    @Test
    public void getSpeedTest() {
        Horse horse = new Horse("name", 1, 1);
        assertEquals(1, horse.getSpeed());
    }

    @Test
    public void getDistanceTest() {
        Horse horse = new Horse("name", 1, 1);
        assertEquals(1, horse.getDistance());
    }
}
