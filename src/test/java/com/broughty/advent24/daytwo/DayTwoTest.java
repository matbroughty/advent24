package com.broughty.advent24.daytwo;

import com.broughty.advent24.DayTest;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static com.broughty.advent24.daytwo.DayTwoDataConstants.DATA;
import static com.broughty.advent24.daytwo.DayTwoDataConstants.TEST_DATA;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DayTwoTest implements DayTest {

    Logger logger = LoggerFactory.getLogger(DayTwoTest.class);

    @Test
    public void partOneTestData() {
        assertEquals(2, safeReports(TEST_DATA));
    }


    @Test
    public void partOneData() {
        assertEquals(202, safeReports(DATA));

    }

    @Test
    public void partTwoTestData() {
        assertEquals(4, safeReports(TEST_DATA, true));
    }

    @Test
    public void partTwoData() {
        assertEquals(271, safeReports(DATA, true));

    }

    public int safeReports(String data) {
        return safeReports(data, false);
    }

    @SuppressWarnings("SuspiciousListRemoveInLoop")
    public int safeReports(String data, boolean stabiliser) {
        Scanner scanner = new Scanner(data);
        int safe = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            var list = Arrays.stream(line.split(" ")).map(Integer::valueOf).toList();
            if (isSafe(list)) {
                safe += 1;
            } else {
                if (stabiliser) {
                    for (int i = 0; i < list.size(); i++) {
                        ArrayList<Integer> stabiliserList = new ArrayList<>(list);
                        stabiliserList.remove(i);
                        if (isSafe(stabiliserList)) {
                            safe += 1;
                            break;
                        }
                    }
                }
            }
        }
        scanner.close();
        return safe;
    }

    private boolean isSafe(List<Integer> list) {
        logger.debug("processing list {}", StringUtils.join(list, "|"));
        // doesn't move so stop here
        if (list.getFirst().compareTo(list.getLast()) == 0) {
            return false;
        }
        boolean incrementing = list.getFirst().compareTo(list.getLast()) < 0;
        //int stabiliserIndexUsed = 0;
        for (int value = 0; value < list.size(); value++) {
            if (list.size() == value + 1) {
                // reached end so must be ok
                return true;
            }
            //int current = list.get(stabiliserIndexUsed > 0 && stabiliserIndexUsed == value ? value - 1 : value);
            int current = list.get(value);
            int next = list.get(value + 1);
            int diff = incrementing ? next - current : current - next;
            if (diff < 1 || diff > 3) {
                if (diff == 0) {
                    logger.debug("same values in list at {} and {} - {}", value, value + 1, StringUtils.join(list, "|"));
                }
                logger.debug("List {} is not safe",
                        StringUtils.join(list, "|"));
                return false;
            }
        }
        return true;
    }
}

