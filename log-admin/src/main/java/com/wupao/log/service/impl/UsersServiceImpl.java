package com.wupao.log.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wupao.log.mapper.UsersMapper;
import com.wupao.log.pojo.Users;
import com.wupao.log.service.UsersService;
import com.wupao.log.utils.PageDataResult;
import com.wupao.log.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    UsersMapper usersMapper;
    
    @Override
    @Transactional
    public ResultUtil delUsersInfo(Long id) {
        ResultUtil r=new ResultUtil();
        int count=usersMapper.delUsersInfo(id);
        if(count>0){
            r.setResult(ResultUtil.SUCCESS);
        }else{
            r.setResult(ResultUtil.ERROR);
        }
        return r;
    }

    @Override
    @Transactional
    public ResultUtil addUsers(Users users) {
        ResultUtil r=new ResultUtil();
        int count=usersMapper.addUsers(users);
        if(count>0){
            r.setResult(ResultUtil.SUCCESS);
        }else{
            r.setResult(ResultUtil.ERROR);
        }
        return r;
    }

    @Override
    @Transactional
    public ResultUtil updateUsers(Users users) {
        ResultUtil r=new ResultUtil();
        int count=usersMapper.updateUsers(users);
        if(count>0){
            r.setResult(ResultUtil.SUCCESS);
        }else{
            r.setResult(ResultUtil.ERROR);
        }
        return r;
    }

    @Override
    public ResultUtil getUsers(Long id) {
        Users users= usersMapper.getUsers(id);
        return new ResultUtil(ResultUtil.SUCCESS,"查询成功",users);
    }


    @Override
    public PageDataResult selectUsersInfoList(Users users, String startDate, String endDate, int page, int limit) {

        PageDataResult pdr=new PageDataResult();
        PageHelper.startPage(page, limit);
        List<Users> usersList=usersMapper.selectUsersInfoList(users, startDate, endDate);
        PageInfo<Users> pageInfo = new PageInfo<Users>(usersList);
        pdr.setTotals(Long.valueOf(pageInfo.getTotal()).intValue());
        pdr.setList(usersList);
        return pdr;
    }
}
