package com.broughty.advent24.dayone;


import com.broughty.advent24.DayTest;
import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.util.Tuple;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

import static com.broughty.advent24.dayone.DayOneDataConstants.DATA;
import static com.broughty.advent24.dayone.DayOneDataConstants.TEST_DATA;
import static java.util.Collections.frequency;
import static org.apache.commons.lang3.StringUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class DayOneTest implements DayTest {


    @Test
    public void partOneTestData() {
        assertEquals(11, reconcile(TEST_DATA));
    }

    @Test
    public void partOneData() {
        assertEquals(1319616, reconcile(DATA));
    }

    @Test
    public void partTwoTestData() {
        assertEquals(31, similarity(TEST_DATA));
    }

    @Test
    public void partTwoData() {
        assertEquals(27267728, similarity(DATA));
    }


    public int similarity(String data) {
        var lists = readLists(data);

        var left = lists._1();
        var right = lists._2();
        int total = 0;

        for (Integer value : left) {
            var matches = frequency(right, value);
            total += (value * matches);
        }
        return total;

    }

    public int reconcile(String data) {

        var lists = readLists(data);

        var left = lists._1();
        var right = lists._2();
        return IntStream.range(0, left.size()).map(i -> left.get(i).compareTo(right.get(i)) > 0 ? left.get(i) - right.get(i) : right.get(i) - left.get(i)).sum();

    }


    public Tuple<List<Integer>, List<Integer>> readLists(String data) {
        var left = new ArrayList<Integer>();
        var right = new ArrayList<Integer>();
        Scanner scanner = new Scanner(data);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            left.add(Integer.valueOf(substringBefore(line, " ")));
            right.add(Integer.valueOf(trim(substringAfter(line, "  "))));
        }
        scanner.close();

        Collections.sort(left);
        Collections.sort(right);

        return new Tuple<>(left, right);
    }

}
