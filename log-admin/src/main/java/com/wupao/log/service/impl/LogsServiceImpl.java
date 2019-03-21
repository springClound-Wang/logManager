package com.wupao.log.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wupao.log.mapper.LogsMapper;
import com.wupao.log.pojo.Logs;
import com.wupao.log.service.LogsService;
import com.wupao.log.utils.PageDataResult;
import com.wupao.log.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LogsServiceImpl implements LogsService {
    @Autowired
    LogsMapper logsMapper;
    
    @Override
    @Transactional
    public ResultUtil delLogsInfo(Long id) {
        ResultUtil r=new ResultUtil();
        int count=logsMapper.delLogsInfo(id);
        if(count>0){
            r.setResult(ResultUtil.SUCCESS);
        }else{
            r.setResult(ResultUtil.ERROR);
        }
        return r;
    }

    @Override
    @Transactional
    public ResultUtil addLogs(Logs logs) {
        ResultUtil r=new ResultUtil();
        int count=logsMapper.addLogs(logs);
        if(count>0){
            r.setResult(ResultUtil.SUCCESS);
        }else{
            r.setResult(ResultUtil.ERROR);
        }
        return r;
    }

    @Override
    @Transactional
    public ResultUtil updateLogs(Logs logs) {
        ResultUtil r=new ResultUtil();
        int count=logsMapper.updateLogs(logs);
        if(count>0){
            r.setResult(ResultUtil.SUCCESS);
        }else{
            r.setResult(ResultUtil.ERROR);
        }
        return r;
    }

    @Override
    public ResultUtil getLogs(Long id) {
        Logs logs= logsMapper.getLogs(id);
        return new ResultUtil(ResultUtil.SUCCESS,"查询成功",logs);
    }


    @Override
    public PageDataResult selectLogsInfoList(Logs logs, String startDate, String endDate, int page, int limit) {

        PageDataResult pdr=new PageDataResult();
        PageHelper.startPage(page, limit);
        List<Logs> logsList=logsMapper.selectLogsInfoList(logs, startDate, endDate);
        PageInfo<Logs> pageInfo = new PageInfo<Logs>(logsList);
        pdr.setTotals(Long.valueOf(pageInfo.getTotal()).intValue());
        pdr.setList(logsList);
        return pdr;
    }
}
