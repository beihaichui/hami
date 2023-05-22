package com.tedu.controller;

import com.tedu.model.Mtype;
import com.tedu.model.Songer;
import com.tedu.query.SongerQuery;
import com.tedu.service.MtypeService;
import com.tedu.service.SongerService;
import com.tedu.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("songer")
public class SongerController {
    @Autowired
    private SongerService songerService;

    @Autowired
    private MtypeService mtypeService;

    @RequestMapping("dofindAll")
    public String doFindAll(SongerQuery aq, Model model){
//        //为了程序的严谨性
        if(aq.getPageNo() == null){
            aq.setPageNo(1);
        }
//        //1,根据条件查询到所有的mtype数据并且分页        2,把数据传到前端页面
//        //查询分页的数据
        Page<Songer> page = songerService.selectByConditionPage(aq);
//        //把page对象发给页面
        List<Mtype> mtypes=mtypeService.selectObjectAll();
        model.addAttribute("page", page);
//        //把查询条件也要回显
        model.addAttribute("songer", aq);
        model.addAttribute("mtypes", mtypes);
//       //具体的展现业务还没做
//        //查询所有的流派信息  mtype的业务层service
//        //Page<T> selectByConditionPage(Q q);
//        //Page<Mtype> mtypePage = mtypeService.selectByConditionPage(query);
        return "songer";
    }

    @RequestMapping("toadd")
    public String toadd(Model model){
        List<Mtype> mtypes=mtypeService.selectObjectAll();
        model.addAttribute("mtypes", mtypes);

        return "addSonger";
    }


    @RequestMapping("add")
    public String add(Songer songer){
        songerService.insert(songer);
        return "redirect:dofindAll";
    }



}
