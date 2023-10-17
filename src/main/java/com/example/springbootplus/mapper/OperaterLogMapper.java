package com.example.springbootplus.mapper;

import com.example.springbootplus.entity.OperaterLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author code946
 * @since 2022-04-15
 */
@Mapper
public interface OperaterLogMapper extends BaseMapper<OperaterLog> {
    //根据id删除日志
    public void deleteByIds(@Param(value = "ids") List<Long> list);

}
