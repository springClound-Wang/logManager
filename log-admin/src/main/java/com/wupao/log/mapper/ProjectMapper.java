package com.wupao.log.mapper;

import com.wupao.log.pojo.Project;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProjectMapper {

    List<Project> selectProjectInfoList(@Param("project")Project project, @Param("startDate") String startDate, @Param("endDate") String endDate);
    int delProjectInfo(@Param("id")Long id);
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into t_project(project_name,create_time,is_del,update_time) values(#{projectName},now(),1,now())")
    int addProject(Project project);
    @Select("select * from t_project t where t.id = #{id}")
    Project getProject(Long id);
    int updateProject(Project project);
}