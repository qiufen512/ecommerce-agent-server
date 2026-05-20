package com.ecommerce.common.response;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * Paginated Response Format
 *
 * @param <T> Paginated Data Type
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PageResponse<T> extends Response<List<T>> {

    /** Total Records */
    private long total = 0L;

    /** Records Per Page */
    private long size = 10L;

    /** Current Page Number */
    private long current;

    /** Total Pages */
    private long pages;

    /**
     * Create Empty Paginated Response
     */
    public static <T> PageResponse<T> empty() {
        PageResponse<T> response = new PageResponse<>();
        response.setData(null);
        response.setTotal(0L);
        response.setSize(10L);
        response.setCurrent(1L);
        response.setPages(0L);
        return response;
    }

    /**
     * Create Paginated Response from MyBatis-Plus Page Object
     *
     * @param page MyBatis-Plus Pagination Object
     * @param data Converted VO List
     * @param <T> VO Type
     * @param <E> Entity Type
     * @return Paginated Response
     */
    public static <T, E> PageResponse<T> success(Page<E> page, List<T> data) {
        PageResponse<T> response = new PageResponse<>();
        response.setData(data);
        response.setTotal(page.getTotal());
        response.setSize(page.getSize());
        response.setCurrent(page.getCurrent());
        response.setPages(page.getPages());
        return response;
    }

    /**
     * Create Paginated Response from Pagination Parameters
     *
     * @param data VO List
     * @param current Current Page
     * @param size Page Size
     * @param total Total Records
     * @param <T> VO Type
     * @return Paginated Response
     */
    public static <T> PageResponse<T> success(List<T> data, long current, long size, long total) {
        PageResponse<T> response = new PageResponse<>();
        response.setData(data);
        response.setTotal(total);
        response.setSize(size);
        response.setCurrent(current);
        response.setPages((total + size - 1) / size); // Calculate total pages
        return response;
    }
}