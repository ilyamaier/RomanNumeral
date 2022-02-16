package com.example.romannumeral.controllers;

import com.example.romannumeral.helpers.RangeHelper;
import com.example.romannumeral.models.Range;
import com.example.romannumeral.models.RomanNumeral;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestController
public class MainController {

    /**
     * Converts the given integer in range 1-3999 to corresponding Roman numeral.
     *
     * @param query the {@code Integer} to convert.
     * @return a {@code RomanNumeral} with the <i>query</i> as input, and
     * corresponding Roman numeral as output.
     */
    @GetMapping(value = "/romannumeral", params = "query")
    public RomanNumeral convertOne(Integer query) {
        return new RomanNumeral(query);
    }

    /**
     * Converts the given range (inclusive) to corresponding Roman numerals.
     * The range bounds must be in range 1-3999 and the lower bound
     * must be less than the upper bound.
     *
     * @param min the lower bound,
     * @param max the upper bound.
     * @return a {@code Range} containing the converted numbers in the
     * array <i>conversions</i>.
     */
    @GetMapping(value = "/romannumeral", params = {"min", "max"})
    public Range convertRange(Integer min, Integer max) {
        return new RangeHelper(min, max).convertRange();
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleMethodArgumentTypeMismatchException() {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Given input isn't of type Integer.");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleIllegalArgumentException(
            IllegalArgumentException exception
    ) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exception.getMessage());
    }

}
