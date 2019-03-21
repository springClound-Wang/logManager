package com.wupao.log.service;


import com.wupao.log.pojo.Users;
import com.wupao.log.utils.PageDataResult;
import com.wupao.log.utils.ResultUtil;


public interface UsersService {

	PageDataResult selectUsersInfoList(Users users, String startDate, String endDate, int page, int limit);
	ResultUtil delUsersInfo(Long id);
    ResultUtil addUsers(Users users);
    ResultUtil updateUsers(Users users);
    ResultUtil getUsers(Long id);
}
