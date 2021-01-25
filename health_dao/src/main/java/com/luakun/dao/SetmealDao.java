package com.luakun.dao;

import com.github.pagehelper.Page;
import com.luakun.pojo.Setmeal;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author AKunLin
 * Date: 2021/1/23  16:38
 * Description: SetmealDao
 */
@Repository
public interface SetmealDao {
    Page<Setmeal> selectByCondition(String queryString);

    void add(Setmeal setmeal);


    void setSetmealAndCheckGroup(Map<String, Integer> map);
}
