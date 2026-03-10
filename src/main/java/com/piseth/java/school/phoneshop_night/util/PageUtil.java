package com.piseth.java.school.phoneshop_night.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public class PageUtil {
    public static final int DEFAULT_PAGE_NUMBER = 1;
    public static final int DEFAULT_PAGE_LIMIT = 10;
    public static final String PAGE_NUMBER_PARAM = "page";
    public static final String PAGE_LIMIT_PARAM = "limit";

    public static int getPageNumber(Map<String, String> params) {
        if (params.containsKey(PAGE_NUMBER_PARAM)) {
            try {
                int page = Integer.parseInt(params.get(PAGE_NUMBER_PARAM));
                return Math.max(page, 1);
            } catch (NumberFormatException e) {
                // Ignore and return default
            }
        }
        return DEFAULT_PAGE_NUMBER;
    }

    public static int getPageLimit(Map<String, String> params) {
        if (params.containsKey(PAGE_LIMIT_PARAM)) {
            try {
                int limit = Integer.parseInt(params.get(PAGE_LIMIT_PARAM));
                return limit > 0 ? limit : DEFAULT_PAGE_LIMIT;
            } catch (NumberFormatException e) {
                // Ignore and return default
            }
        }
        return DEFAULT_PAGE_LIMIT;
    }

    public static Pageable getPageable(int pageNumber, int pageLimit) {
        // Spring Pageable is 0-indexed, so we subtract 1
        return PageRequest.of(pageNumber - 1, pageLimit);
    }
}