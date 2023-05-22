package com.tedu.controller;

import com.tedu.model.Mtype;
import com.tedu.model.Song;
import com.tedu.model.Songer;
import com.tedu.query.SongQuery;
import com.tedu.query.SongerQuery;
import com.tedu.service.MtypeService;
import com.tedu.service.SongService;
import com.tedu.service.SongerService;
import com.tedu.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("protal")
public class SearchController {
    @Autowired
    private SongService songService;
    @Autowired
    private MtypeService mtypeService;
    @RequestMapping("search.do")
    public String doFindAll(SongQuery mq, Model model){
//        //为了程序的严谨性
        if(mq.getPageNo() == null){
            mq.setPageNo(1);
        }
//        //1,根据条件查询到所有的mtype数据并且分页        2,把数据传到前端页面
//        //查询分页的数据
        Page<Song> page = songService.selectByConditionPage(mq);
//        //把page对象发给页面
        List<Mtype> mtypes=mtypeService.selectObjectAll();
        model.addAttribute("page", page);
//        //把查询条件也要回显
        model.addAttribute("mq", mq);
        model.addAttribute("mtypes", mtypes);

        return "search";
    }


    /**
     *
     * 在cookie中存储历史播放  playids=12,34,5,67,8
     *
     * @return
     */


}
