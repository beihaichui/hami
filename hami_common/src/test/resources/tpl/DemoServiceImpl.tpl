package com.tedu.service.impl;

import com.tedu.dao.DemoMapper;
import com.tedu.model.Demo;
import com.tedu.query.DemoQuery;
import com.tedu.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 歌手业务层的实现类  实现歌手的业务层接口
 */
@Service
public class DemoServiceImpl extends BaseServiceImpl<DemoQuery,Demo> implements DemoService {

     private DemoMapper demoMapper;

     @Autowired
    public void setDemoMapper(DemoMapper demoMapper) {
        this.demoMapper = demoMapper;
        this.BaseDao=demoMapper;
    }
}