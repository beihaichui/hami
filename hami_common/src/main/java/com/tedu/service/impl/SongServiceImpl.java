package com.tedu.service.impl;

import com.tedu.dao.SongMapper;
import com.tedu.model.Song;
import com.tedu.query.SongQuery;
import com.tedu.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 歌手业务层的实现�?  实现歌手的业务层接口
 */
@Service
public class SongServiceImpl extends BaseServiceImpl<SongQuery,Song> implements SongService {

     private SongMapper songMapper;

     @Autowired
    public void setSongMapper(SongMapper songMapper) {
        this.songMapper = songMapper;
        this.BaseDao=songMapper;
    }

    @Override
    public List<Song> selectSongBySids(List<Integer> sids){
         return songMapper.selectSongBySids(sids);
    }
}
