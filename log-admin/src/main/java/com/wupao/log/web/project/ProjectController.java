package com.wupao.log.web.project;

import com.wupao.log.pojo.Project;
import com.wupao.log.service.ProjectService;
import com.wupao.log.utils.Constants;
import com.wupao.log.utils.PageDataResult;
import com.wupao.log.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: wzz
 * @packageName com.wupao.log.web.project
 * @Date: 2019/3/20 10:24
 * @Description: 项目名称控制层
 */
@Controller
@RequestMapping("/project")
@Slf4j
public class ProjectController {
    @Autowired
    ProjectService projectService;

    @GetMapping("/projectList")
    public String projectList(String curr, HttpServletRequest request) {
        request.setAttribute(Constants.CURRENTPAGE, curr);
        return "/project/projectList";
    }
    /**
     * @description 分页获取数据
     * @author wzz
     * @date 2019/3/20
     * @param[project, startDate, endDate, page, limit]
     * @return com.wupao.tools.entity.PageDataResult
     */
    @PostMapping("/selectProjectInfoList")
    @ResponseBody
    public PageDataResult selectProjectInfoList(Project project, String startDate, String endDate,
                                                @RequestParam("page") int page,
                                                @RequestParam("limit") int limit) {
        PageDataResult pdr = new PageDataResult();
        try {
            log.info(this.getClass()+".selectProjectInfoList()根据参数获取记录列表的请求参数==>project:{},startDate:{}endDate:{}page:{}limit:{}",project,startDate,endDate,page,limit);
            pdr = projectService.selectProjectInfoList(project, startDate, endDate, page, limit);
            log.info(this.getClass()+".selectProjectInfoList()根据参数获取记录列表成功:{}" ,pdr);
        } catch (Exception e) {
            e.printStackTrace();
            pdr.setCode(Constants.HTTP_500_CODE);
            log.error(this.getClass()+".selectProjectInfoList()根据参数获取记录列表失败原因为：{}", e);
        }
        return pdr;
    }
    /**
     * @description 根据编号删除
     * @author wzz
     * @date 2019/3/20
     * @param[id]
     * @return com.wupao.log.utils.ResultUtil
     */
    @PostMapping("/delProjectInfo/{id}")
    @ResponseBody
    public ResultUtil delProjectInfo(@PathVariable("id") Long id ){
       return  projectService.delProjectInfo(id);
    }

    @PostMapping("/addProject")
    @ResponseBody
    public ResultUtil addProject(Project project){
        return  projectService.addProject(project);
    }
    @PostMapping("/updateProject")
    @ResponseBody
    public ResultUtil updateProject(Project project){
        return  projectService.updateProject(project);
    }

    @PostMapping("/getProject/{id}")
    @ResponseBody
    public ResultUtil getProject(@PathVariable Long id) {
        return projectService.getProject(id);
    }
}
