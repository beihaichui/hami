package com.tedu.service;

import com.tedu.dao.MtypeMapper;
import com.tedu.model.Mtype;
import com.tedu.model.Songer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application.xml"})
public class MtypeTest {

    @Autowired
    MtypeMapper mtypeMapper;


    /**
     * 测试mtype的查询数据
     */
    @Test
    public void selectmtypeTest(){
        Mtype mtype = mtypeMapper.selectByPrimaryKey(19);
        System.out.println(mtype);
    }



}
