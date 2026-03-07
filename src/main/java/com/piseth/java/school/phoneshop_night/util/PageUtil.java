package com.piseth.java.school.phoneshop_night.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface PageUtil {
    int DEFAULT_PAGE_LIMIT = 2;
    int DEFAULT_PAGE_NUMBER = 1; // (or 0)
    String PAGE_LIMIT = "_limit";
    String PAGE_NUMBER = "_page";

    static int getPageLimit(Map<String, String> params) {
        int pageLimit = DEFAULT_PAGE_LIMIT;
        if (params.containsKey(PAGE_LIMIT)) {
            pageLimit = Integer.parseInt(params.get(PAGE_LIMIT));
        }
        return pageLimit;
    }

    static int getPageNumber(Map<String, String> params) {
        int pageNumber = DEFAULT_PAGE_NUMBER;
        if (params.containsKey(PAGE_NUMBER)) {
            pageNumber = Integer.parseInt(params.get(PAGE_NUMBER));
        }
        return pageNumber;
    }

    static Pageable getPageable(int pageNumber, int pageSize) {
        if (pageNumber < 1) {
            pageNumber = DEFAULT_PAGE_NUMBER;
        }
        if (pageSize < 1) {
            pageSize = DEFAULT_PAGE_LIMIT;
        }
        return PageRequest.of(pageNumber - 1, pageSize);
    }
}
