package com.wupao.log.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wupao.log.mapper.ProjectMapper;
import com.wupao.log.pojo.Project;
import com.wupao.log.service.ProjectService;
import com.wupao.log.utils.PageDataResult;
import com.wupao.log.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    ProjectMapper projectMapper;
    /**
     * @description 根据id删除
     * @author wzz
     * @date 2019/3/20
     * @param[id]
     * @return com.wupao.log.utils.ResultUtil
     */
    @Override
    @Transactional
    public ResultUtil delProjectInfo(Long id) {
        ResultUtil r=new ResultUtil();
        int count=projectMapper.delProjectInfo(id);
        if(count>0){
            r.setResult(ResultUtil.SUCCESS);
        }else{
            r.setResult(ResultUtil.ERROR);
        }
        return r;
    }

    @Override
    @Transactional
    public ResultUtil addProject(Project project) {
        ResultUtil r=new ResultUtil();
        int count=projectMapper.addProject(project);
        if(count>0){
            r.setResult(ResultUtil.SUCCESS);
        }else{
            r.setResult(ResultUtil.ERROR);
        }
        return r;
    }

    @Override
    @Transactional
    public ResultUtil updateProject(Project project) {
        ResultUtil r=new ResultUtil();
        int count=projectMapper.updateProject(project);
        if(count>0){
            r.setResult(ResultUtil.SUCCESS);
        }else{
            r.setResult(ResultUtil.ERROR);
        }
        return r;
    }

    @Override
    public ResultUtil getProject(Long id) {
        Project project= projectMapper.getProject(id);
        return new ResultUtil(ResultUtil.SUCCESS,"查询成功",project);
    }

    /**
     * @description 分页
     * @author wzz
     * @date 2019/3/20
     * @param[project, startDate, endDate, page, limit]
     * @return com.wupao.tools.entity.PageDataResult
     */
    @Override
    public PageDataResult selectProjectInfoList(Project project, String startDate, String endDate, int page, int limit) {

        PageDataResult pdr=new PageDataResult();
        PageHelper.startPage(page, limit);
        List<Project> projectList=projectMapper.selectProjectInfoList(project, startDate, endDate);
        PageInfo<Project> pageInfo = new PageInfo<Project>(projectList);
        pdr.setTotals(Long.valueOf(pageInfo.getTotal()).intValue());
        pdr.setList(projectList);
        return pdr;
    }
}
