package com.wupao.log.web.log;

import com.wupao.log.pojo.Logs;
import com.wupao.log.service.LogsService;
import com.wupao.log.utils.Constants;
import com.wupao.log.utils.PageDataResult;
import com.wupao.log.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/logs")
@Slf4j
public class LogsController {
    @Autowired
    LogsService logsService;

    @GetMapping("/logsList")
    public String logsList(String curr, HttpServletRequest request) {
        request.setAttribute(Constants.CURRENTPAGE, curr);
        return "/logs/logsList";
    }
   
    @PostMapping("/selectLogsInfoList")
    @ResponseBody
    public PageDataResult selectLogsInfoList(Logs logs, String startDate, String endDate,
                                                @RequestParam("page") int page,
                                                @RequestParam("limit") int limit) {
        PageDataResult pdr = new PageDataResult();
        try {
            log.info(this.getClass()+".selectLogsInfoList()根据参数获取记录列表的请求参数==>logs:{},startDate:{}endDate:{}page:{}limit:{}",logs,startDate,endDate,page,limit);
            pdr = logsService.selectLogsInfoList(logs, startDate, endDate, page, limit);
            log.info(this.getClass()+".selectLogsInfoList()根据参数获取记录列表成功:{}" ,pdr);
        } catch (Exception e) {
            e.printStackTrace();
            pdr.setCode(Constants.HTTP_500_CODE);
            log.error(this.getClass()+".selectLogsInfoList()根据参数获取记录列表失败原因为：{}", e);
        }
        return pdr;
    }
    
    @PostMapping("/delLogsInfo/{id}")
    @ResponseBody
    public ResultUtil delLogsInfo(@PathVariable("id") Long id ){
       return  logsService.delLogsInfo(id);
    }

    @PostMapping("/addLogs")
    @ResponseBody
    public ResultUtil addLogs(Logs logs){
        return  logsService.addLogs(logs);
    }
    @PostMapping("/updateLogs")
    @ResponseBody
    public ResultUtil updateLogs(Logs logs){
        return  logsService.updateLogs(logs);
    }

    @PostMapping("/getLogs/{id}")
    @ResponseBody
    public ResultUtil getLogs(@PathVariable Long id) {
        return logsService.getLogs(id);
    }
}
