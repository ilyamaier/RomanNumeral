package com.example.romannumeral.models;

import java.util.Objects;

public class RomanNumeral {

    private final String input;
    private final String output;

    public RomanNumeral(Integer input) {
        if (input == null) throw new IllegalArgumentException("Given input is null");
        this.input = input.toString();
        this.output = convertToRoman(input);
    }

    public String getInput() {
        return input;
    }

    public String getOutput() {
        return output;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RomanNumeral that = (RomanNumeral) o;
        return Objects.equals(input, that.input) && Objects.equals(output, that.output);
    }

    /**
     * Converts the given {@code int} to a Roman numeral.
     * <p>
     * Since for every decimal place, Roman numerals have the same structure,
     * we convert every decimal place to the corresponding literals via
     * {@code processOneDecimal}.
     * </p>
     *
     * @param input the {@code int} to convert.
     * @return a {@code String} with the Roman representation of the given number.
     * @throws IllegalArgumentException if the given input isn't in the valid range.
     */
    public static String convertToRoman(int input) {
        if (input < 1 || input > 3999) throw new IllegalArgumentException("Given input isn't in range 1-3999");

        StringBuilder roman = new StringBuilder();

        roman.append(processOneDecimal(input / 1000, "M", "", ""));
        roman.append(processOneDecimal((input % 1000) / 100, "C", "D", "M"));
        roman.append(processOneDecimal((input % 100) / 10, "X", "L", "C"));
        roman.append(processOneDecimal(input % 10, "I", "V", "X"));

        return roman.toString();
    }

    /**
     * Converts the given decimal to corresponding literals.
     *
     * @param decimal the {@code int} to convert,
     * @param first   the {@code String} of the first used literal for
     *                the given decimal place, i.e. X for tens,
     * @param second  the {@code String} of the second such literal, i.e. L for tens,
     * @param third   the {@code String} of the third such literal, i.e. C for tens.
     * @return a {@code String} with the corresponding literals of the given decimal.
     */
    private static String processOneDecimal(int decimal, String first, String second, String third) {
        switch (decimal) {
            case 1: return first;
            case 2: return first + first;
            case 3: return first + first + first;
            case 4: return first + second;
            case 5: return second;
            case 6: return second + first;
            case 7: return second + first + first;
            case 8: return second + first + first + first;
            case 9: return first + third;
            default: return "";
        }
    }

}
