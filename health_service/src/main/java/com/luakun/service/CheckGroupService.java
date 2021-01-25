package com.luakun.service;

import com.luakun.entity.PageResult;
import com.luakun.pojo.CheckGroup;
import com.luakun.pojo.CheckItem;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author AKunLin
 * Date: 2021/1/22  20:13
 * Description: CheckGroupService
 */
public interface CheckGroupService {
    PageResult findPage(Integer currentPage, Integer pageSize, String queryString);

    void add(CheckGroup checkGroup, Integer[] checkitemIds);

    void edit(CheckGroup checkGroup);

    CheckGroup findById(int  id);

    void delete(Integer id);

    List<Integer> findCheckItemIdsByGroupId(Integer id);

    List<CheckGroup> findAll();

}
