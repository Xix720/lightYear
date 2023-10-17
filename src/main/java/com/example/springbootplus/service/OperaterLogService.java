package com.example.springbootplus.service;

import com.example.springbootplus.entity.OperaterLog;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author code946
 * @since 2022-04-15
 */
public interface OperaterLogService extends IService<OperaterLog> {
    //根据id删除日志
    public void deleteByIds(List<Long> list);

}
