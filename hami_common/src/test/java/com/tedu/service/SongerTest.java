package com.tedu.service;
import com.tedu.model.Songer;
import com.tedu.query.SongerQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application.xml"})
public class SongerTest {


    @Autowired
    SongerService songerService;

    /**
     * 测试songer的增伤删改查功能
     */
    @Test
       public void songerTest(){
        Songer s = new Songer();
        s.setArea("广东");
        s.setIntro("很好");
        s.setIsHot(2);
        s.setPic("dd");
        s.setSrname("李四");
        songerService.insert(s);
       }

    /**
     * 测试songer的增伤删改查功能
     */
    @Test
    public void delsongerTest(){
        songerService.deleteByPrimaryKey(23);
    }

    /**
     * 测试songer的查询数据
     */
    @Test
    public void selectsongerTest(){
        Songer songer = songerService.selectByPrimaryKey(21);
        System.out.println(songer);
    }

    /**
     * 测试songer的修改数据
     */
    @Test
    public void updatesongerTest(){
        Songer s = new Songer();
        s.setArea("深圳");
        s.setIntro("流行音乐歌手！！");
        s.setIsHot(1);
        s.setPic("dd");
        s.setSrname("赵六");
        s.setTid(19);
//        s.setSrid(19);
        int i = songerService.updateByPrimaryKeySelective(s);
        System.out.println(i);
    }


    /**
     * 测试songer的查询数据
     */
    @Test
    public void selectAllsongerTest(){

        List<Songer> songerList = songerService.selectObjectAll();
        System.out.println(songerList);
    }


    @Test
    public void selectByCondition() {
        SongerQuery query = new SongerQuery();
        query.setIsHot(1);
        query.setArea("香");
        //query.setSrname("亮");
        List<Songer> songers = songerService.selectByCondition(query);
        System.out.println(songers);
    }


}
