package com.wupao.log.mapper;

import com.wupao.log.pojo.Logs;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface LogsMapper {

    List<Logs> selectLogsInfoList(@Param("logs") Logs logs, @Param("startDate") String startDate, @Param("endDate") String endDate);
    @Select("select * from t_log t where t.id = #{id}")
    Logs getLogs(Long id);
    @Delete("delete from t_log where id = #{id}")
    int deleteLogs(Long id);
    int updateLogs(Logs logs);
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into t_log(project_id, log_message, log_level, project_type, create_time, update_time, is_del) values(#{projectId}, #{logMessage}, #{logLevel}, #{projectType}, now(),now(),1)")
    int addLogs(Logs logs);
    int count(@Param("params") Map<String, Object> params);
    int delLogsInfo(@Param("id") Long id);

}
