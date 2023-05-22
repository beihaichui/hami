package com.tedu.service;

import com.tedu.model.Demo;
import com.tedu.query.DemoQuery;
import org.springframework.stereotype.Service;

//歌手的业务层接口
public interface DemoService extends BaseService<DemoQuery,Demo> {
    //添加歌手的业务层接口
    //通过歌手获取歌手详细信息的接口
    //更新歌手的方法接口
    //删除歌手

}