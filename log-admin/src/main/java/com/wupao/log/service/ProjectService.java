package com.wupao.log.service;


import com.wupao.log.pojo.Project;
import com.wupao.log.utils.PageDataResult;
import com.wupao.log.utils.ResultUtil;


public interface ProjectService {

	PageDataResult selectProjectInfoList(Project project, String startDate, String endDate, int page, int limit);
	ResultUtil delProjectInfo(Long id);
    ResultUtil addProject(Project project);
    ResultUtil updateProject(Project project);
    ResultUtil getProject(Long id);
}
