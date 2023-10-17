package com.example.springbootplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springbootplus.common.Result;
import com.example.springbootplus.entity.IMysqlBackups;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IMysqlBackupsMapper extends BaseMapper<IMysqlBackups> {
    /**
     * 数据库备份
     * @param filePath 备份地址
     * @param userName 数据库名称
     * @param password 数据库密码
     * @param ip 数据库ip
     * @param port 数据库端口号
     * @param database_name  数据库名称
     * @param remark
     * @return
     */
    Result mysqlBackups(String filePath, String ip, String port, String database_name, String userName, String password, String remark);
}

