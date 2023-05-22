package com.tedu.controller;

import com.tedu.model.Mtype;
import com.tedu.model.Song;
import com.tedu.model.Songer;
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
@RequestMapping("songer")
public class SongerController {

    @Autowired
    private SongService songService;

    @Autowired
    private SongerService songerService;
    @Autowired
    private MtypeService mtypeService;

    @RequestMapping("/list")
    public String listType(SongerQuery mq, Model model){
        if(mq.getPageNo() == null){
            mq.setPageNo(1);
        }
        mq.setPageSize(20);
        Page<Songer> page = songerService.selectByConditionPage(mq);
        List<Mtype> mtypes = mtypeService.selectObjectAll();

        List<List<Songer>> list = new ArrayList<>();
        List<Songer> slist = page.getList();
        List<Songer> list1 = null;
        for (int i = 0; i < 20; i++) {
            if(i % 5 == 0){
                list1 = new ArrayList<>();
                list.add(list1);
            }
            Songer s = null;
            if(i < slist.size()){
                s = slist.get(i);
                list1.add(s);
            }

        }
        model.addAttribute("list", list);
        model.addAttribute("page", page);
        model.addAttribute("mq", mq);
        model.addAttribute("mtypes", mtypes);
        return "songers";
    }

    @RequestMapping("play")
    public String play(String sids, HttpServletRequest request, HttpServletResponse response, Model model) throws UnsupportedEncodingException {
        //定义接收string的ids切割完之后的数组
        String[] idsArr =null;  //{"1","2","3","4"}
        //先判断 sids不为null   想办法把sids转换成数组或着集合
        if(sids != null && !"".equals(sids)){
            idsArr = sids.split(",");
        }
        //拿到你的cookie
        Cookie[] cookies = request.getCookies();
        String cookieIds = null;
        String[] idsArrCookie = null;
        //遍历所有浏览器中的cookie  找到我要的
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                //获取遍历的cookie的名字
                String cookieName = cookie.getName();
                //匹配我要找的cookie
                if ("playids".equals(cookieName)) {
                    //获取我要的cookie的value值
                    cookieIds = cookie.getValue();
                    //解码 前端浏览器的编码  可不是utf8
                    cookieIds = URLDecoder.decode(cookieIds, "UTF-8");
                }
            }
        }
        //拿到cookie值后判断是不是空  不为空  我就解析它  拿到对应的所有id
        if(cookieIds != null){
            idsArrCookie = cookieIds.split(",");
        }
        //定义一个整数类型的集合
        List<Integer> sidsInt = new ArrayList<>();  //{4,5}
        //往前端返回的cookie值
        cookieIds = "";
        //判断id的数组不为null
        if(idsArr != null) {
            //遍历数组 将数组中的字符串的id值转换成integer的值 放入集合中  为后续业务层调用ids的集合做查询做准备
            for (String s : idsArr) {
                sidsInt.add(new Integer(s));
                cookieIds = cookieIds + s+ ",";
            }
        }
        if(idsArrCookie != null && !"".equals(idsArrCookie)){
            for (String s : idsArrCookie) {
                Integer sid = new Integer(s);
                boolean exists = false;
                for (Integer i : sidsInt) {
                    if(sid.equals(i)){
                        exists = true;
                        break;
                    }
                }
                if(!exists){
                    sidsInt.add(sid);
                    cookieIds = cookieIds + s+ ",";
                }
            }
        }
        //调用业务层进行数据查询
        List<Song> songs = songService.selectSongBySids(sidsInt);
        cookieIds = URLEncoder.encode(cookieIds, "UTF-8");
        Cookie cookie = new Cookie("playids", cookieIds);
        cookie.setPath("/");
        cookie.setMaxAge(60*60*24*30);
        response.addCookie(cookie);
        //利用model将数据返回给前台页面
        model.addAttribute("songs", songs);
        return "player";
    }

}
