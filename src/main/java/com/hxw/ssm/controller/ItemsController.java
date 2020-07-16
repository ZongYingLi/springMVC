package com.hxw.ssm.controller;

import com.hxw.ssm.pojo.Items;
import com.hxw.ssm.service.ItemsService;
import com.hxw.ssm.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/items")
public class ItemsController {

    @Autowired
    private ItemsService service;

    /**
     * 条件+查询商品列表
     * 参数绑定：
     * 1.若参数是简单类型，要求请求参数中的key与方法的形参名称一致
     *   若是上传图片，要求请求参数中的key与方法的形参名称一致
     * 2.若参数是pojo类型，要求请求参数中的key与方法的形参pojo中的属性名称一致
     * 注意：
     *   url?key1=value1&key2=value2       请求参数就是key1、key2
     *   form表单中的input标签的name="key"  请求参数就是key
     */
    @RequestMapping("/findAll.action")
    public String findAll(String query , Integer pageNow , Model model){
        //查询PageVO
        PageVO vo = service.findAll(query, pageNow);
        //将数据传递至请求域中
        model.addAttribute("vo",vo);
        //返回将要跳转的视图的逻辑名
        return "itemsList.jsp";
    }

    //添加商品  MultipartFile 实现上传图片
    @RequestMapping("/add.action")
    public String add(Items items, MultipartFile file) throws IOException {
        //上传图片
        if(file!=null){
            //获得原始图片名称
            String oldName = file.getOriginalFilename();
            System.out.println("oldName = "+oldName);
            //当前若上传图片
            if(oldName!=null && oldName.length()>0){
                //产生新的图片名称 = 随机数+原图片的后缀
                String newName = UUID.randomUUID()+oldName.substring(oldName.lastIndexOf("."));

                //将此图片上传至本地图片服务器路径
                file.transferTo(new File("D:/homework/temp/"+newName));

                //将此图片传值到items商品中
                items.setPic("/pic/"+newName);
            }
        }

        //添加商品
        service.add(items);
        //为了避免重复提交表单的操作，可以选择重定向，地址栏显示目标地址
        return "redirect:findAll.action";
    }

    @RequestMapping("/edit.action")
    public String edit(Items items, MultipartFile file) throws IOException {
        items.toString();
        if(file!=null){
            //获得原始图片名称
            String oldName = file.getOriginalFilename();
            System.out.println("oldName = "+oldName);
            //当前若上传图片
            if(oldName!=null && oldName.length()>0){
                //产生新的图片名称 = 随机数+原图片的后缀
                String newName = UUID.randomUUID()+oldName.substring(oldName.lastIndexOf("."));

                //将此图片上传至本地图片服务器路径
                file.transferTo(new File("D:/homework/temp/"+newName));

                //将此图片传值到items商品中
                items.setPic("/pic/"+newName);
            }
        }
        service.edit(items);
        return "redirect:findAll.action";
    }

    @RequestMapping("/deleteAll.action")
    public String deleteAll(){
        service.delall();
        return "redirect:findAll.action";
    }

    @RequestMapping("/deleteOne.action")
    public String deleteOne(Integer id){
        service.delone(id);
        return "redirect:findAll.action";
    }
}
