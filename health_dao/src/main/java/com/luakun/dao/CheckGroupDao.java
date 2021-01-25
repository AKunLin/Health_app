package com.luakun.dao;

import com.github.pagehelper.Page;
import com.luakun.pojo.CheckGroup;
import com.luakun.pojo.CheckItem;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author AKunLin
 * Date: 2021/1/22  20:13
 * Description: CheckGroupDao
 */
@Repository
public interface CheckGroupDao {
    Page<CheckGroup> selectByCondition(String queryString);


    void add(CheckGroup checkGroup);

    void setCheckGroupAndCheckItem(Map<String, Integer> map);

    CheckGroup findById(int id);

    void edit(CheckGroup checkGroup);

    void deleteAssociation(Integer id);

    void deleteById(Integer id);

    List<Integer> findCheckItemIdsByGroupId(Integer id);

    List<CheckGroup> findAll();
}
