package com.luakun.jobs;

import com.luakun.constant.RedisConstant;
import com.luakun.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisPool;

import java.util.Iterator;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 *
 * @author AKunLin
 * Date: 2021/1/23  19:37
 * Description: ClearImgJob
 */
public class ClearImgJob {
    @Autowired
    private JedisPool jedisPool;


    /**
     * 清理垃圾图片
     */
    public void testClearImg(){

            //1.得到差集
            Set<String> set = jedisPool.getResource().sdiff(RedisConstant.SETMEAL_PIC_RESOURCES, RedisConstant.SETMEAL_PIC_DB_RESOURCES);
            System.out.println(set);
            if(set != null && set.size()>0){
                for (String fileName : set) {
                        //2. 调用七牛云删除
                        QiniuUtils.deleteFileFromQiniu(fileName);
                        //3.操作redis删除集合中垃圾图片
                    jedisPool.getResource().srem(RedisConstant.SETMEAL_PIC_RESOURCES, fileName);
                }
            }
        }
}
