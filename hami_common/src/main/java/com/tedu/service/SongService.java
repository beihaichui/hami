package com.tedu.service;

import com.tedu.model.Song;
import com.tedu.query.SongQuery;
import org.springframework.stereotype.Service;

import java.util.List;

//歌手的业务层接口
public interface SongService extends BaseService<SongQuery,Song> {

    List<Song> selectSongBySids (List<Integer> sids);
    //添加歌手的业务层接口
    //通过歌手获取歌手详细信息的接�?
    //更新歌手的方法接�?
    //删除歌手

}
