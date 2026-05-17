package com.ecommerce.common.response;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 分页响应格式
 *
 * @param <T> 分页数据类型
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PageResponse<T> extends Response<List<T>> {

    /** 总记录数 */
    private long total = 0L;

    /** 每页显示的记录数 */
    private long size = 10L;

    /** 当前页码 */
    private long current;

    /** 总页数 */
    private long pages;

    /**
     * 创建空分页响应
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
     * 从 MyBatis-Plus Page 对象创建分页响应
     *
     * @param page MyBatis-Plus 分页对象
     * @param data 转换后的 VO 列表
     * @param <T> VO 类型
     * @param <E> Entity 类型
     * @return 分页响应
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
     * 从分页参数创建分页响应
     *
     * @param data VO 列表
     * @param current 当前页
     * @param size 每页大小
     * @param total 总记录数
     * @param <T> VO 类型
     * @return 分页响应
     */
    public static <T> PageResponse<T> success(List<T> data, long current, long size, long total) {
        PageResponse<T> response = new PageResponse<>();
        response.setData(data);
        response.setTotal(total);
        response.setSize(size);
        response.setCurrent(current);
        response.setPages((total + size - 1) / size); // 计算总页数
        return response;
    }
}