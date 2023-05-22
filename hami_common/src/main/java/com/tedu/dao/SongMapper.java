package com.tedu.dao;

import com.tedu.model.Song;
import com.tedu.model.Songer;
import com.tedu.query.SongQuery;
import com.tedu.query.SongerQuery;

import java.util.List;

public interface SongMapper extends BaseDao<SongQuery, Song>  {

    List<Song> selectSongBySids(List<Integer> sids);

}