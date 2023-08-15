package com.example.be_dolan_final.utils;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.*;

import java.util.ArrayList;
import java.util.List;

import static com.example.be_dolan_final.utils.CompareUtils.strMatchAny;

public class PageableUtils extends PageRequest {
    public PageableUtils(int page, int size, String orderBy, boolean desc) {
        super(page - 1, size, desc ? Sort.by(orderBy).descending() : Sort.by(orderBy));
    }

    public static Pageable pageable(int page, int size, String orderBy, boolean desc) {
        return new PageableUtils(page, size, orderBy, desc);
    }

    public static PageRequest from(final int page, final int size, final String orderBy, final boolean isAscending) {
        return PageRequest.of(page - 1, size, isAscending ? Sort.Direction.ASC : Sort.Direction.DESC, orderBy);
    }

    public static PageRequest from(final int page, final int size) {
        return PageRequest.of(page - 1, size);
    }

    public static boolean isDesc(String sort){
        return strMatchAny(sort, Sort.Direction.DESC);
    }

    public static<T> Page<T> convertToPage(List<T> objectList, Pageable pageable){
        int start = (int) pageable.getOffset();
        int end = Math.min(start+pageable.getPageSize(),objectList.size());
        List<T> subList = start>=end?new ArrayList<>():objectList.subList(start,end);
        return new PageImpl<>(subList,pageable,objectList.size());
    }

    @NotNull
    public static <T, R> Page<R> extractFromPage(Page<T> origin, List<R> newContent) {
        return new PageImpl<>(newContent, origin.getPageable(), origin.getTotalElements());
    }
}
