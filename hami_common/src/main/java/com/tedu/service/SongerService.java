package com.tedu.service;

import com.tedu.model.Songer;
import com.tedu.query.SongerQuery;
import org.springframework.stereotype.Service;

//歌手的业务层接口
public interface SongerService extends BaseService<SongerQuery,Songer> {

    //添加歌手的业务层接口
    //通过歌手获取歌手详细信息的接口
    //更新歌手的方法接口
    //删除歌手

}
