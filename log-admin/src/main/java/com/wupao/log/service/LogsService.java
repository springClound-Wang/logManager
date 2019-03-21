package com.wupao.log.service;


import com.wupao.log.pojo.Logs;
import com.wupao.log.utils.PageDataResult;
import com.wupao.log.utils.ResultUtil;


public interface LogsService {

	PageDataResult selectLogsInfoList(Logs logs, String startDate, String endDate, int page, int limit);
	ResultUtil delLogsInfo(Long id);
    ResultUtil addLogs(Logs logs);
    ResultUtil updateLogs(Logs logs);
    ResultUtil getLogs(Long id);
}
