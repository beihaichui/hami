package com.tedu.controller;

import com.tedu.model.Album;
import com.tedu.model.Mtype;
import com.tedu.model.Song;
import com.tedu.model.Songer;
import com.tedu.query.SongQuery;
import com.tedu.service.AlbumService;
import com.tedu.service.MtypeService;
import com.tedu.service.SongService;

import com.tedu.service.SongerService;
import com.tedu.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@Controller
@RequestMapping("song")
public class SongController {

    @Autowired
    private SongService songService;

    @Autowired
    private MtypeService mtypeService;

    @Autowired
    private SongerService songerService;


    @Autowired
    private AlbumService albumService;




    @RequestMapping("dofindAll")
    public String list(SongQuery aq, Model model){
//        //为了程序的严谨性
        if(aq.getPageNo() == null){
            aq.setPageNo(1);
        }
//        //1,根据条件查询到所有的mtype数据并且分页        2,把数据传到前端页面
//        //查询分页的数据
        Page<Song> page = songService.selectByConditionPage(aq);
//        //把page对象发给页面
        List<Mtype> mtypes=mtypeService.selectObjectAll();
        model.addAttribute("page", page);
//        //把查询条件也要回显
        model.addAttribute("song", aq);
        model.addAttribute("mtypes", mtypes);
//       //具体的展现业务还没做
//        //查询所有的流派信息  mtype的业务层service
//        //Page<T> selectByConditionPage(Q q);
//        //Page<Mtype> mtypePage = mtypeService.selectByConditionPage(query);
        return "song";
    }

    @RequestMapping("toadd")
    public String toadd(Model model){
        List<Mtype> mtypes=mtypeService.selectObjectAll();
        List<Songer> songer=songerService.selectObjectAll();
        List<Album> albums=albumService.selectObjectAll();

        model.addAttribute("mtypes", mtypes);
        model.addAttribute("songer", songer);
        model.addAttribute("albums", albums);

        return "addSong";
    }

}
