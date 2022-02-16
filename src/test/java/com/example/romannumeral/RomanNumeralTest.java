package com.example.romannumeral;

import com.example.romannumeral.models.RomanNumeral;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class RomanNumeralTest {

    @Test
    void testSimpleNumbers() {
        assertEquals("I", RomanNumeral.convertToRoman(1));
        assertEquals("V", RomanNumeral.convertToRoman(5));
        assertEquals("X", RomanNumeral.convertToRoman(10));
        assertEquals("L", RomanNumeral.convertToRoman(50));
        assertEquals("C", RomanNumeral.convertToRoman(100));
        assertEquals("D", RomanNumeral.convertToRoman(500));
        assertEquals("M", RomanNumeral.convertToRoman(1000));
    }

    @Test
    void testPatternMatching() {
        assertEquals("II", RomanNumeral.convertToRoman(2));
        assertEquals("III", RomanNumeral.convertToRoman(3));
        assertEquals("IV", RomanNumeral.convertToRoman(4));
        assertEquals("VI", RomanNumeral.convertToRoman(6));
        assertEquals("VII", RomanNumeral.convertToRoman(7));
        assertEquals("VIII", RomanNumeral.convertToRoman(8));
        assertEquals("IX", RomanNumeral.convertToRoman(9));

        assertEquals("XX", RomanNumeral.convertToRoman(20));
        assertEquals("XXX", RomanNumeral.convertToRoman(30));
        assertEquals("XL", RomanNumeral.convertToRoman(40));
        assertEquals("LX", RomanNumeral.convertToRoman(60));
        assertEquals("LXX", RomanNumeral.convertToRoman(70));
        assertEquals("LXXX", RomanNumeral.convertToRoman(80));
        assertEquals("XC", RomanNumeral.convertToRoman(90));

        assertEquals("CC", RomanNumeral.convertToRoman(200));
        assertEquals("CCC", RomanNumeral.convertToRoman(300));
        assertEquals("CD", RomanNumeral.convertToRoman(400));
        assertEquals("DC", RomanNumeral.convertToRoman(600));
        assertEquals("DCC", RomanNumeral.convertToRoman(700));
        assertEquals("DCCC", RomanNumeral.convertToRoman(800));
        assertEquals("CM", RomanNumeral.convertToRoman(900));

        assertEquals("MM", RomanNumeral.convertToRoman(2000));
        assertEquals("MMM", RomanNumeral.convertToRoman(3000));
    }

    @Test
    void testRegularNumbers() {
        assertEquals("MMCLXXIII", RomanNumeral.convertToRoman(2173));
        assertEquals("DLXXVI", RomanNumeral.convertToRoman(576));
        assertEquals("XCIV", RomanNumeral.convertToRoman(94));
        assertEquals("LXXXVIII", RomanNumeral.convertToRoman(88));
        assertEquals("CDXXV", RomanNumeral.convertToRoman(425));
        assertEquals("MCXI", RomanNumeral.convertToRoman(1111));
        assertEquals("MMMCMXCIX", RomanNumeral.convertToRoman(3999));
    }

    @Test
    void testContainsZero() {
        assertEquals("MMLXXIII", RomanNumeral.convertToRoman(2073));
        assertEquals("DVI", RomanNumeral.convertToRoman(506));
        assertEquals("CDXX", RomanNumeral.convertToRoman(420));
        assertEquals("MI", RomanNumeral.convertToRoman(1001));
    }

    @Test
    void testValidRange() {
        assertThrows(IllegalArgumentException.class, () -> RomanNumeral.convertToRoman(0));
        assertThrows(IllegalArgumentException.class, () -> RomanNumeral.convertToRoman(4000));
        assertThrows(IllegalArgumentException.class, () -> RomanNumeral.convertToRoman(-1));
        assertThrows(IllegalArgumentException.class, () -> RomanNumeral.convertToRoman(Integer.MAX_VALUE));
        assertThrows(IllegalArgumentException.class, () -> RomanNumeral.convertToRoman(Integer.MIN_VALUE));
    }

}
