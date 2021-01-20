package com.luakun.dao;

import com.luakun.pojo.CheckItem;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author AKunLin
 * Date: 2021/1/20  16:58
 * Description: CheckItemDao
 */
public interface CheckItemDao {
    void add(CheckItem checkItem);
    List<CheckItem> findAll();
}
