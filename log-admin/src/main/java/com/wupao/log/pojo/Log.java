package com.wupao.log.pojo;

import lombok.Data;

/**
 * @Auther: wzz
 * @packageName com.wupao.log.pojo
 * @Date: 2019/3/20 10:11
 * @Description: 项目名称
 */
@Data
public class Log {

    private int id;
    private int projectId;
    private String logMessage;
    private String logLevel;
    private String createTime;
    private String projectType;

}
