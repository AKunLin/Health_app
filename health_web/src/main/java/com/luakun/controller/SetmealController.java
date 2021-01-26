package com.luakun.controller;

import com.luakun.constant.MessageConstant;
import com.luakun.constant.RedisConstant;
import com.luakun.entity.PageResult;
import com.luakun.entity.QueryPageBean;
import com.luakun.entity.Result;
import com.luakun.pojo.CheckGroup;
import com.luakun.pojo.Setmeal;
import com.luakun.service.SetmealService;
import com.luakun.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 *
 * @author AKunLin
 * Date: 2021/1/23  16:34
 * Description: SetmealController
 */
@RestController
@RequestMapping("/setmeal")
public class SetmealController {
    @Autowired
    private SetmealService setmealService;
    @Autowired
    private JedisPool jedisPool;

    @PostMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        return setmealService.findPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize(), queryPageBean.getQueryString());
    }

    @RequestMapping("/upload")
    public Result upload(@RequestParam("imgFile") MultipartFile imgFile) {
        try {
            //原始文件名称
            String originalFilename = imgFile.getOriginalFilename();
            //获取文件名后缀
            int lastIndexOf = originalFilename.lastIndexOf(".");
            String suffix = originalFilename.substring(lastIndexOf);
            String fileName = UUID.randomUUID().toString() + suffix;

            QiniuUtils.upload2Qiniu(imgFile.getBytes(), fileName);
            //将文件上传后的文件名返给SETMEAL_PIC_RESOURCES集合中
            jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_RESOURCES,fileName);
            Result result = new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS, fileName);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            //图片上传失败
            return new Result(false, MessageConstant.PIC_UPLOAD_FAIL);
        }
    }
    @PostMapping("/add")
    public Result add(@RequestBody Setmeal setmeal, Integer[] checkgroupIds){
        try {
            setmealService.add(checkgroupIds,setmeal);
            jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES,setmeal.getImg());

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.ADD_SETMEAL_FAIL);
        }
        return new Result(true,MessageConstant.ADD_SETMEAL_SUCCESS);
    }

    @GetMapping("/findById")
    public Result findById(Integer id){
        try {
            Setmeal setmeal = setmealService.findById(id);
            return new Result(true,MessageConstant.QUERY_CHECKGROUP_SUCCESS,setmeal);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
    }
    @PostMapping("/edit")
    public Result edit(@RequestBody Setmeal setmeal){
        try {
            setmealService.edit(setmeal);
            return new Result(true,MessageConstant.EDIT_CHECKGROUP_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.EDIT_CHECKGROUP_FAIL);
        }
    }

    @GetMapping("/delete")
    public Result delete(Integer id) {
        try {
            setmealService.delete(id);
        }  catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.DELETE_CHECKGROUP_FAIL);
        }
        return new Result(true,MessageConstant.DELETE_CHECKGROUP_SUCCESS);
    }

    @RequestMapping("/findCheckGroupIdsBysetmeal")
    public List<Integer> findCheckGroupIdsBysetmeal(Integer id){
        List<Integer> list = setmealService.findCheckGroupIdsBysetmeal(id);
        return list;
    }
    @GetMapping("/findAll")
    public Result findAll() {
        // 查询所有的检查组
        List<Setmeal> setmealList = setmealService.findAll();
        if (setmealList != null && setmealList.size() > 0) {
            Result result = new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS, setmealList);
            return result;
        }
        return new Result(false, MessageConstant.QUERY_CHECKGROUP_FAIL);
    }
}
