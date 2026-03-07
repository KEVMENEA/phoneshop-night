package com.piseth.java.school.phoneshop_night.util;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GeneralUtilsTest {

    @Test
    public void TestToIntegerList() {
        // given

        List<String> names  = List.of("Dara", "Cheata", "Thida");
        // [4, 6, 5]

        // when
        List<Integer> list = GeneralUtils.toIntegerList(names);

        // then
        assertEquals(3, list.size());
        assertEquals(4, list.get(0));
        assertEquals(6, list.get(1));
        assertEquals(5, list.get(2));

    }

    @Test
    public void TestGetEvenNumber() {

        // given
        List<Integer> number = List.of(2, 3, 4, 5, 6, 7, 8);


        // when
        List<Integer> evenNumber = GeneralUtils.getEvenNumber(number);

        // then
        assertEquals(4, evenNumber.size());
        assertEquals(2, evenNumber.get(0));


    }
}
