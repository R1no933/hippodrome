import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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
}
