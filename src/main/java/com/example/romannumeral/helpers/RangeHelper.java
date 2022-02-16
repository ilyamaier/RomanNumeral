package com.example.romannumeral.helpers;

import com.example.romannumeral.models.Range;
import com.example.romannumeral.models.RomanNumeral;

public class RangeHelper {

    private final int min, max;

    public RangeHelper(Integer min, Integer max) {
        if (min == null || max == null) throw new IllegalArgumentException("Given input is null");

        if (min < 1 || max > 3999) throw new IllegalArgumentException("Given input isn't in range 1-3999");
        if (min >= max) throw new IllegalArgumentException("Given min is greater than or equal to max");

        this.min = min;
        this.max = max;
    }

    // Converts the given range to Roman numerals stored in Range.
    // Splits the range into 8 parts and converts the parts in parallel.
    public Range convertRange() {
        int size = max - min + 1;
        Range range = new Range(size);

        // we need to save the threads in order to wait for them to finish later
        RangeThread[] threads = new RangeThread[8];

        if (size < 7) { // can't split into 8 parts
            threads[0] = new RangeThread(range.getConversions(), min, max + 1, min);
            threads[0].start();
        } else {
            for (int i = 0; i < 8; i++) {
                threads[i] = new RangeThread(
                        range.getConversions(),
                        min + i * (size / 7),
                        Math.min(min + (i + 1) * (size / 7), max + 1), // to prevent outOfBoundsException
                        min
                );
                threads[i].start();
            }
        }

        // wait for threads to finish
        for (int i = 0; i < 8; i++) {
            if (threads[i] != null) {
                try {
                    threads[i].join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        return range;
    }

    // Thread for converting each part from start (inclusive) to end (exclusive).
    // Saves the conversions in the given conversions array.
    private class RangeThread extends Thread {
        private final RomanNumeral[] conversions;
        private final int start, end, first;

        public RangeThread(RomanNumeral[] conversions, int start, int end, int first) {
            this.conversions = conversions;
            this.start = start;
            this.end = end;
            this.first = first;
        }

        @Override
        public void run() {
            for (int i = start; i < end; i++) {
                conversions[i - first] = new RomanNumeral(i);
            }
        }
    }

}
