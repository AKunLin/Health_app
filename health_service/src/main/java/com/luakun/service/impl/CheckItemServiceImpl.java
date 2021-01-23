package com.luakun.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.luakun.dao.CheckItemDao;
import com.luakun.entity.PageResult;
import com.luakun.entity.QueryPageBean;
import com.luakun.pojo.CheckItem;
import com.luakun.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author AKunLin
 * Date: 2021/1/20  17:23
 * Description: CheckItemServiceImpl
 */
@Service
@Transactional
public class CheckItemServiceImpl implements CheckItemService {
    @Autowired
    private CheckItemDao checkItemDao;

    /**
     * 添加检查项
     * @param checkItem
     */
    @Override
    public void add(CheckItem checkItem) {
        checkItemDao.add(checkItem);
    }

    /**
     * 分页查询检查项
     * @param currentPage
     * @param pageSize
     * @param queryString
     * @return
     */
    @Override
    public PageResult findPage(Integer currentPage, Integer pageSize, String queryString) {
        PageHelper.startPage(currentPage,pageSize);
        Page<CheckItem> page = checkItemDao.selectByCondition(queryString);
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public void delete(Integer id) throws RuntimeException{
        long count = checkItemDao.findCountByCheckItemId(id);
        if(count > 0){
            throw new RuntimeException("该项被某查询组引用,不能被删除");
        }
        checkItemDao.deleteById(id);
    }

    @Override
    public CheckItem findById(Integer id) {
        CheckItem checkItem = checkItemDao.findById(id);
        return checkItem;
    }

    @Override
    public void edit(CheckItem checkItem) {
        checkItemDao.edit(checkItem);
    }

    @Override
    public List<CheckItem> findAll() {
        List<CheckItem> checkItemList = checkItemDao.findAll();
        return checkItemList;
    }


}
