package com.piseth.java.school.phoneshop_night.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PaginationDTO {
    private int pageSize;
    private int pageNumber;
    private int totalPages;
    private long totalElements;
    private Long numberOfElements;
    private boolean first;
    private boolean last;
    private boolean empty;
}
