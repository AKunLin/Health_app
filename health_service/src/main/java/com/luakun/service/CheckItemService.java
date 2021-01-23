package com.luakun.service;

import com.luakun.entity.PageResult;
import com.luakun.entity.QueryPageBean;
import com.luakun.pojo.CheckItem;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author AKunLin
 * Date: 2021/1/20  17:22
 * Description: CheckItemService
 */
public interface CheckItemService {
    /**
     * 添加检查项
     * @param checkItem
     */
    void add(CheckItem checkItem);

    /**
     * 分页查询检查项
     * @param currentPage
     * @param pageSize
     * @param queryString
     * @return
     */
    PageResult findPage(Integer currentPage, Integer pageSize, String queryString);

    /**
     * 删除检查项
     * @param id
     */
    void delete(Integer id);

    /**
     * 通过id查询检查项
     * @param id
     * @return
     */
    CheckItem findById(Integer id);

    /**
     * 修改检查项
     * @param checkItem
     */
    void edit(CheckItem checkItem);


    List<CheckItem> findAll();

}
