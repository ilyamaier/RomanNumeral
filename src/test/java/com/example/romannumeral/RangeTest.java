package com.example.romannumeral;

import com.example.romannumeral.helpers.RangeHelper;
import com.example.romannumeral.models.RomanNumeral;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class RangeTest {

    @Test
    void testRanges() {
        assertArrayEquals(generateRange(1, 2), new RangeHelper(1, 2).convertRange().getConversions());
        assertArrayEquals(generateRange(1, 3999), new RangeHelper(1, 3999).convertRange().getConversions());
        assertArrayEquals(generateRange(254, 999), new RangeHelper(254, 999).convertRange().getConversions());
        assertArrayEquals(generateRange(1123, 2789), new RangeHelper(1123, 2789).convertRange().getConversions());
        assertArrayEquals(generateRange(23, 3743), new RangeHelper(23, 3743).convertRange().getConversions());
    }

    @Test
    void testInvalidRange() {
        assertThrows(IllegalArgumentException.class, () -> new RangeHelper(1, 1).convertRange());
        assertThrows(IllegalArgumentException.class, () -> new RangeHelper(0, 1).convertRange());
        assertThrows(IllegalArgumentException.class, () -> new RangeHelper(1, 4000).convertRange());
        assertThrows(IllegalArgumentException.class, () -> new RangeHelper(40001, 40002).convertRange());
        assertThrows(IllegalArgumentException.class, () -> new RangeHelper(-12, 1).convertRange());
    }

    private RomanNumeral[] generateRange(int min, int max) {
        RomanNumeral[] result = new RomanNumeral[max - min + 1];
        for (int i = 0; i < result.length; i++) {
            result[i] = new RomanNumeral(i + min);
        }
        return result;
    }

}
