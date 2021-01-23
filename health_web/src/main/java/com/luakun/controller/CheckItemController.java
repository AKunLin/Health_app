package com.luakun.controller;

import com.luakun.constant.MessageConstant;
import com.luakun.entity.PageResult;
import com.luakun.entity.QueryPageBean;
import com.luakun.entity.Result;
import com.luakun.pojo.CheckItem;
import com.luakun.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author AKunLin
 * Date: 2021/1/20  17:32
 * Description: CheckItemController
 */
@RestController
@RequestMapping("/checkitem")
public class CheckItemController {
    @Autowired
    private CheckItemService checkItemService;

    @PostMapping("/add")
    public Result add(@RequestBody CheckItem checkItem) {
        try {
            checkItemService.add(checkItem);
            return new Result(true, MessageConstant.ADD_CHECKITEM_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_CHECKITEM_FAIL);
        }
    }

    @PostMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {

        return checkItemService.findPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize(), queryPageBean.getQueryString());

    }

    @GetMapping("/delete")
    public Result delete(Integer id) {
        try {
            checkItemService.delete(id);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new Result(false, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.DELETE_CHECKITEM_FAIL);
        }
        return new Result(true,MessageConstant.DELETE_CHECKITEM_SUCCESS);
    }

    @GetMapping("/findById")
    public Result findById(Integer id){
        try {
            CheckItem checkItem = checkItemService.findById(id);
            return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,checkItem);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }
    @PostMapping("/edit")
    public Result edit(@RequestBody CheckItem checkItem){
        try {
            checkItemService.edit(checkItem);
            return new Result(true,MessageConstant.EDIT_CHECKITEM_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.EDIT_CHECKITEM_FAIL);
        }
    }

    @GetMapping("/findAll")
    public Result findAll(){
        List<CheckItem> checkItemList = checkItemService.findAll();
        if (checkItemList!=null && checkItemList.size()>0) {
            return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,checkItemList);
        }else {
            return new Result(false,MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }

}
