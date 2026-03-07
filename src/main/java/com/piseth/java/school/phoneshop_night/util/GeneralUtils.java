package com.piseth.java.school.phoneshop_night.util;

import org.springframework.data.relational.core.sql.In;

import java.util.List;

public class GeneralUtils {

    public static List<Integer> toIntegerList(List<String> list) {
        return list.stream()
                .map(s -> s.length())
                .toList();
    }

    public static List<Integer> getEvenNumber(List<Integer> list) {
        return list.stream()
                .filter(x -> x %2 ==0)
                .toList();
    }
}
