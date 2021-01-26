package com.luakun.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.luakun.dao.SetmealDao;
import com.luakun.entity.PageResult;
import com.luakun.pojo.CheckGroup;
import com.luakun.pojo.Setmeal;
import com.luakun.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author AKunLin
 * Date: 2021/1/23  16:37
 * Description: SetmealServiceImpl
 */
@Service
@Transactional
public class SetmealServiceImpl implements SetmealService {
    @Autowired
    private SetmealDao setmealDao;

    /**
     * 分页查询
     * @param currentPage
     * @param pageSize
     * @param queryString
     * @return
     */

    @Override
    public PageResult findPage(Integer currentPage, Integer pageSize, String queryString) {
        PageHelper.startPage(currentPage,pageSize);
        Page<Setmeal> page = setmealDao.selectByCondition(queryString);
        return new PageResult(page.getTotal(),page.getResult());
    }

    /**
     * 新增套餐
     * @param checkgroupIds
     * @param setmeal
     */
    @Override
    public void add(Integer[] checkgroupIds, Setmeal setmeal) {
        setmealDao.add(setmeal);
        setSetmealAndCheckGroup(setmeal.getId(),checkgroupIds);
    }

    @Override
    public void edit(Setmeal setmeal) {
        setmealDao.edit(setmeal);
    }

    @Override
    public void delete(Integer id) {
        long count = setmealDao.findCountBySelmealId(id);
        if(count > 0){
            setmealDao.deleteBySeId(id);
        }
        setmealDao.delete(id);
    }
    @Override
    public Setmeal findById(int id) {
        Setmeal setmeal = setmealDao.findById(id);
        return setmeal;
    }

    /**
     * 对中间表进行插入
     * @param id
     * @param checkgroupIds
     */
    public void setSetmealAndCheckGroup(Integer id,Integer[] checkgroupIds){
        for (Integer checkgroupId : checkgroupIds) {
            Map<String, Integer> map = new HashMap<>();
            map.put("setmeal_id",id);
            map.put("checkgroup_id",checkgroupId);
            setmealDao.setSetmealAndCheckGroup(map);
        }
    }
    @Override
    public List<Integer> findCheckGroupIdsBysetmeal(Integer id) {
        List<Integer> list = setmealDao.findCheckGroupIdsBysetmeal(id);
        return list;
    }
    @Override
    public List<Setmeal> findAll() {
        return setmealDao.findAll();
    }
}
