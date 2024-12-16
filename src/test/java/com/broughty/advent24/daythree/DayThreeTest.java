package com.broughty.advent24.daythree;

import com.broughty.advent24.DayTest;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DayThreeTest implements DayTest {

    @Test
    public void partOneTestData() {
        assertEquals(161, sumMultiples(DayThreeDataConstants.TEST_DATA));
    }


    @Test
    public void partOneData() {
        assertEquals(189600467, sumMultiples(DayThreeDataConstants.DATA));
    }

    @Test
    public void partTwoTestData() {

    }

    @Test
    public void partTwoData() {

    }

    private int sumMultiples(String data) {
        Scanner scanner = new Scanner(data);
        int count = 0;
        while (scanner.hasNextLine()) {
            count += multiplyLine(scanner.nextLine());
        }
        scanner.close();
        return count;
    }

    private int multiplyLine(String line) {
        int lineCount = 0;
        line = StringUtils.substringAfter(line, "mul(");
        while (!line.isEmpty()) {
            var args = StringUtils.substringBefore(line, ")");
            var num = StringUtils.substringBefore(args, ",");
            var multiplier = StringUtils.substringAfter(args, ",");
            if(StringUtils.isNumeric(num) && StringUtils.isNumeric(multiplier)){
                lineCount += (Integer.parseInt(num) * Integer.parseInt(multiplier));
            }
            line = StringUtils.substringAfter(line, "mul(");
        }
        return lineCount;
    }

}
