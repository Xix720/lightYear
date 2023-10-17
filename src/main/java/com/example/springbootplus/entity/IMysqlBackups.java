package com.example.springbootplus.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class IMysqlBackups implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String mysqlIp;

    /**
     * 数据库端口号
     */
    private String mysqlPort;

    /**
     * 备份命令
     */
    private String mysqlCmd;

    /**
     * 恢复命令
     */
    private String mysqlBackCmd;

    /**
     * 数据库名称
     */
    private String databaseName;

    /**
     * 备份文件名称
     */
    private String backupsName;

    /**
     * 备份路径
     */
    private String backupsPath;

    /**
     * 操作次数
     */
    private Integer operation;

    /**
     * 备份时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date backupTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMysqlIp() {
        return mysqlIp;
    }

    public void setMysqlIp(String mysqlIp) {
        this.mysqlIp = mysqlIp;
    }

    public String getMysqlPort() {
        return mysqlPort;
    }

    public void setMysqlPort(String mysqlPort) {
        this.mysqlPort = mysqlPort;
    }

    public String getMysqlCmd() {
        return mysqlCmd;
    }

    public void setMysqlCmd(String mysqlCmd) {
        this.mysqlCmd = mysqlCmd;
    }

    public String getMysqlBackCmd() {
        return mysqlBackCmd;
    }

    public void setMysqlBackCmd(String mysqlBackCmd) {
        this.mysqlBackCmd = mysqlBackCmd;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getBackupsName() {
        return backupsName;
    }

    public void setBackupsName(String backupsName) {
        this.backupsName = backupsName;
    }

    public String getBackupsPath() {
        return backupsPath;
    }

    public void setBackupsPath(String backupsPath) {
        this.backupsPath = backupsPath;
    }

    public Integer getOperation() {
        return operation;
    }

    public void setOperation(Integer operation) {
        this.operation = operation;
    }

    public Date getBackupTime() {
        return backupTime;
    }

    public void setBackupTime(Date backupTime) {
        this.backupTime = backupTime;
    }

    public Date getRecoveryTime() {
        return recoveryTime;
    }

    public void setRecoveryTime(Date recoveryTime) {
        this.recoveryTime = recoveryTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 恢复时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date recoveryTime;

    /**
     * 备注
     */
    private String remark;
}

