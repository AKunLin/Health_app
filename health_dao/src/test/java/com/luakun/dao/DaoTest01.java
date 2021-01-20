package com.luakun.dao;

import com.luakun.pojo.CheckItem;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created with IntelliJ IDEA.
 *
 * @author AKunLin
 * Date: 2021/1/20  17:06
 * Description: DaoTest01
 */
public class DaoTest01 {
    @Test
    public void Test001(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext-dao.xml");
        CheckItemDao checkItemDao = (CheckItemDao) applicationContext.getBean("checkItemDao");
        checkItemDao.add(new CheckItem(null,"6666","123","0","18",5.0f,"检查","hahah","wu1"));
    }
    @Test
    public void Test02(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext-dao.xml");
        CheckItemDao checkItemDao = (CheckItemDao) applicationContext.getBean("checkItemDao");
        System.out.println(checkItemDao.findAll());
    }

}
