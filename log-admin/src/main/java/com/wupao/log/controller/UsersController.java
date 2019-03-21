package com.wupao.log.controller;

import com.wupao.log.pojo.Users;
import com.wupao.log.service.UsersService;
import com.wupao.log.utils.Constants;
import com.wupao.log.utils.PageDataResult;
import com.wupao.log.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/users")
@Slf4j
public class UsersController {
    @Autowired
    UsersService usersService;

    @GetMapping("/usersList")
    public String usersList(String curr, HttpServletRequest request) {
        request.setAttribute(Constants.CURRENTPAGE, curr);
        return "/users/usersList";
    }
   
    @PostMapping("/selectUsersInfoList")
    @ResponseBody
    public PageDataResult selectUsersInfoList(Users users, String startDate, String endDate,
                                                @RequestParam("page") int page,
                                                @RequestParam("limit") int limit) {
        PageDataResult pdr = new PageDataResult();
        try {
            log.info(this.getClass()+".selectUsersInfoList()根据参数获取记录列表的请求参数==>users:{},startDate:{}endDate:{}page:{}limit:{}",users,startDate,endDate,page,limit);
            pdr = usersService.selectUsersInfoList(users, startDate, endDate, page, limit);
            log.info(this.getClass()+".selectUsersInfoList()根据参数获取记录列表成功:{}" ,pdr);
        } catch (Exception e) {
            e.printStackTrace();
            pdr.setCode(Constants.HTTP_500_CODE);
            log.error(this.getClass()+".selectUsersInfoList()根据参数获取记录列表失败原因为：{}", e);
        }
        return pdr;
    }
    
    @PostMapping("/delUsersInfo/{id}")
    @ResponseBody
    public ResultUtil delUsersInfo(@PathVariable("id") Long id ){
       return  usersService.delUsersInfo(id);
    }

    @PostMapping("/addUsers")
    @ResponseBody
    public ResultUtil addUsers(Users users){
        return  usersService.addUsers(users);
    }
    @PostMapping("/updateUsers")
    @ResponseBody
    public ResultUtil updateUsers(Users users){
        return  usersService.updateUsers(users);
    }

    @PostMapping("/getUsers/{id}")
    @ResponseBody
    public ResultUtil getUsers(@PathVariable Long id) {
        return usersService.getUsers(id);
    }
}
