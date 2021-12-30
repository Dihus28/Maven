package work4;

import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import static org.assertj.core.api.Assertions.assertThat;

public class CalcAreaTest {
    @Test
    void successSquareCalculation() throws Exception {
        Assertions.assertEquals(TriangleArea.calcArea(3,4,5), 6);
        Assertions.assertTrue(Math.abs(TriangleArea.calcArea(3,4,5) - 6) < 0.0001);
    }

    @Test
    void negativeTriangleTest() {
        Assertions.assertThrows(Exception.class, () -> TriangleArea.calcArea(-3,4,5));
    }
}
