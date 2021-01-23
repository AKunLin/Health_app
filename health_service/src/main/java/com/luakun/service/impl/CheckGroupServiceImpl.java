package com.luakun.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.luakun.dao.CheckGroupDao;
import com.luakun.entity.PageResult;
import com.luakun.pojo.CheckGroup;
import com.luakun.service.CheckGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author AKunLin
 * Date: 2021/1/22  20:13
 * Description: CheckGroupServiceImpl
 */
@Service
public class CheckGroupServiceImpl implements CheckGroupService {
    @Autowired
    private CheckGroupDao checkGroupDao;
    @Override
    public PageResult findPage(Integer currentPage, Integer pageSize, String queryString) {
        PageHelper.startPage(currentPage,pageSize);
        Page<CheckGroup> page = checkGroupDao.selectByCondition(queryString);
        return new PageResult(page.getTotal(),page.getResult());
    }
    @Override
    //添加检查组合，同时需要设置检查组合和检查项的关联关系
    public void add(CheckGroup checkGroup, Integer[] checkitemIds) {
        checkGroupDao.add(checkGroup);
        setCheckGroupAndCheckItem(checkGroup.getId(),checkitemIds);
    }

    @Override
    public void edit(CheckGroup checkGroup) {
        checkGroupDao.edit(checkGroup);
    }

    @Override
    public CheckGroup findById(int id) {
        CheckGroup checkGroup = checkGroupDao.findById(id);
        return checkGroup;
    }

    @Override
    public void delete(Integer id) throws RuntimeException{
        checkGroupDao.deleteAssociation(id);

        checkGroupDao.deleteById(id);
    }

    @Override
    public List<Integer> findCheckItemIdsByGroupId(Integer id) {
        List<Integer> list = checkGroupDao.findCheckItemIdsByGroupId(id);
        return list;
    }


    public void setCheckGroupAndCheckItem(Integer checkGroupId, Integer[] checkitemIds) {
        if(checkitemIds != null && checkitemIds.length > 0){
            for (Integer checkitemId : checkitemIds) {
                Map<String, Integer> map = new HashMap<String, Integer>();
                map.put("checkgroup_id",checkGroupId);
                map.put("checkitem_id",checkitemId);
                checkGroupDao.setCheckGroupAndCheckItem(map);
            }
        }
    }

}
