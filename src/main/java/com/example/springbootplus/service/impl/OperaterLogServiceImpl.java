package com.example.springbootplus.service.impl;

import com.example.springbootplus.entity.OperaterLog;
import com.example.springbootplus.mapper.OperaterLogMapper;
import com.example.springbootplus.mapper.UserMapper;
import com.example.springbootplus.service.OperaterLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author code946
 * @since 2022-04-15
 */
@Service
public class OperaterLogServiceImpl extends ServiceImpl<OperaterLogMapper, OperaterLog> implements OperaterLogService {
    @Autowired
    private OperaterLogMapper operaterLogMapper;
    @Override
    public void deleteByIds(List<Long> list) {
        operaterLogMapper.deleteByIds(list);
    }
}
