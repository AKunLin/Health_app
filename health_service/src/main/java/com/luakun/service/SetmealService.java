package com.luakun.service;

import com.luakun.entity.PageResult;
import com.luakun.pojo.Setmeal;

/**
 * Created with IntelliJ IDEA.
 *
 * @author AKunLin
 * Date: 2021/1/23  16:36
 * Description: SetmealService
 */

public interface SetmealService {
    PageResult findPage(Integer currentPage, Integer pageSize, String queryString);

    void add(Integer[] checkgroupIds, Setmeal setmeal);
}
