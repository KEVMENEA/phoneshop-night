package com.piseth.java.school.phoneshop_night.service.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface PageUtil {
    int DEFAULT_PAGE_LIMIT = 2;
    int DEFAULT_PAGE_NUMBER = 0;
    String PAGE_LIMIT = "_limit";
    String PAGE_NUMBER = "_page";

    static Pageable getPageable(int pageNumber, int pageSize) {
      if (pageNumber < DEFAULT_PAGE_NUMBER) {
          pageNumber = DEFAULT_PAGE_NUMBER;
      }
      if (pageSize < 1) {
          pageSize = DEFAULT_PAGE_LIMIT;

      }
      Pageable pageable = PageRequest.of(pageNumber, pageSize);
      return pageable;
    }

}
