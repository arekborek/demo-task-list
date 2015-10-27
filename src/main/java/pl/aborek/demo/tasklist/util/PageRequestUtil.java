package pl.aborek.demo.tasklist.util;

import org.springframework.data.domain.PageRequest;

import java.util.Optional;

public class PageRequestUtil {

    private static final Integer DEFAULT_PAGE_SIZE = 5;
    private static final Integer DEFAULT_PAGE_NUMBER = 0;

    public static PageRequest createPageRequest(Integer pageSize, Integer pageNumber) {
        return createPageRequest(Optional.ofNullable(pageSize), Optional.ofNullable(pageNumber));
    }

    public static PageRequest createPageRequest(Optional<Integer> pageSize, Optional<Integer> pageNumber) {
        return new PageRequest(pageNumber.orElse(DEFAULT_PAGE_NUMBER), pageSize.orElse(DEFAULT_PAGE_SIZE));
    }

}
