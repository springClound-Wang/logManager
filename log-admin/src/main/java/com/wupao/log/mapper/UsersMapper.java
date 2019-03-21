package com.wupao.log.mapper;

import com.wupao.log.pojo.Users;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface UsersMapper {

    List<Users> selectUsersInfoList(@Param("users") Users users, @Param("startDate") String startDate, @Param("endDate") String endDate);
    @Select("select * from users t where t.id = #{id}")
    Users getUsers(Long id);
    @Delete("delete from users where id = #{id}")
    int deleteUsers(Long id);
    int updateUsers(Users users);
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into users(name, phone, create_time, update_time, is_del) values(#{name}, #{phone}, now(),now(),1)")
    int addUsers(Users users);
    int count(@Param("params") Map<String, Object> params);
    int delUsersInfo(@Param("id") Long id);

}
