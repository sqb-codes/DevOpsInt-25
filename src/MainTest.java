import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @Test
    void testAdd() {
        Main calc = new Main();
        assertEquals(8, calc.add(5, 3));
        assertEquals(0, calc.add(-2, 2));
        assertEquals(-5, calc.add(-2, -3));
    }

    @Test
    void testSubtract() {
        Main calc = new Main();
        assertEquals(2, calc.subtract(5, 3));
        assertEquals(-4, calc.subtract(-2, 2));
        assertEquals(1, calc.subtract(-2, -3));
    }
}