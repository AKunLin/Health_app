package com.luakun.service;

import com.luakun.entity.PageResult;
import com.luakun.pojo.Setmeal;

import java.util.List;

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

    void edit(Setmeal setmeal);

    Setmeal findById(int id);

    void delete(Integer id);

    List<Integer> findCheckGroupIdsBysetmeal(Integer id);

    List<Setmeal> findAll();

}
