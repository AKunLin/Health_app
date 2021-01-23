package com.luakun.dao;

import com.github.pagehelper.Page;
import com.luakun.pojo.CheckItem;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author AKunLin
 * Date: 2021/1/20  16:58
 * Description: CheckItemDao
 */
@Repository
public interface CheckItemDao {
    void add(CheckItem checkItem);
    List<CheckItem> findAll();

    Page<CheckItem> selectByCondition(String queryString);

    long findCountByCheckItemId(Integer id);

    void deleteById(Integer id);

    CheckItem findById(Integer id);

    void edit(CheckItem checkItem);
}
