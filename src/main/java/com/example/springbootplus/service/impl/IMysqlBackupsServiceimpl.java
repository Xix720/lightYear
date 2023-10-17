package com.example.springbootplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springbootplus.common.CodeMsg;
import com.example.springbootplus.common.Result;
import com.example.springbootplus.entity.IMysqlBackups;
import com.example.springbootplus.mapper.IMysqlBackupsMapper;
import com.example.springbootplus.service.IMysqlBackupsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
@Service
public class IMysqlBackupsServiceimpl extends ServiceImpl<IMysqlBackupsMapper, IMysqlBackups> implements IMysqlBackupsService {
    /**
     * 数据库备份
     * @param filePath 备份地址
     * @param userName 数据库名称
     * @param password 数据库密码
     * @param ip
     * @param port
     * @param database_name
     * @param remark
     * @return
     */
    @Transactional
    @Override
    public Result mysqlBackups(String filePath, String userName, String password, String ip, String port, String database_name, String remark) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        // 数据库文件名称
        StringBuilder mysqlFileName = new StringBuilder()
                .append(sdf.format(new Date()))
                .append("_")
                .append(database_name)
                .append(".sql");
        // 备份命令
        StringBuilder cmd = new StringBuilder()
                .append("mysqldump ")
                .append("--no-tablespaces ")
                .append("-h")
                .append(ip)
                .append(" -u")
                .append(userName)
                .append(" -p")
                .append(password)
                // 排除MySQL备份表
                .append(" --ignore-table ")
                .append(database_name)
                .append(".mysql_backups ")
                .append(database_name)
                .append(" > ")
                .append(filePath)
                .append(mysqlFileName);
        // 判断文件是否保存成功
        if (!new File(filePath).exists()) {
            new File(filePath).mkdirs();
        }
        // 获取操作系统名称
        String osName = System.getProperty("os.name").toLowerCase();
        String[] command = new String[0];
        if (true) {
            // Windows
            command = new String[]{"cmd", "/c", String.valueOf(cmd)};
        } else {
            // Linux
            command = new String[]{"/bin/sh", "-c", String.valueOf(cmd)};
        }
        IMysqlBackups mysqlBackups = new IMysqlBackups();
//        // 备份信息存放到数据库
//        mysqlBackups.setMysqlIp(ip);
//        mysqlBackups.setMysqlPort(port);
//        mysqlBackups.setBackupsName(String.valueOf(mysqlFileName));
//        mysqlBackups.setDatabaseName(database_name);
//        mysqlBackups.setMysqlCmd(String.valueOf(cmd));
//        mysqlBackups.setBackupsPath(filePath);
//        mysqlBackups.setBackupTime(new Date());
//        mysqlBackups.setOperation(0);
//        mysqlBackups.setRemark(remark);
//        this.save(mysqlBackups);
        try {
            // 获取Runtime实例
            Runtime.getRuntime().exec(command);
            return Result.success("Mysql 数据库备份成功,备份文件名：{}"+ mysqlFileName);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(CodeMsg.DATA_ERROR);
        }
    }
}
