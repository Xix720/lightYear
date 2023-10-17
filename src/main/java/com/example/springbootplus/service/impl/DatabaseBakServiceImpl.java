package com.example.springbootplus.service.impl;

import com.example.springbootplus.entity.DatabaseBak;
import com.example.springbootplus.mapper.DatabaseBakMapper;
import com.example.springbootplus.service.DatabaseBakService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author code946
 * @since 2022-04-15
 */
@Service
public class DatabaseBakServiceImpl extends ServiceImpl<DatabaseBakMapper, DatabaseBak> implements DatabaseBakService {
    @Autowired
    private DatabaseBakMapper databaseBakMapper;
    @Override
    public void deleteById(Long id) {
        databaseBakMapper.deleteById(id);
    }

    @Override
    public Long maxId() {
        return databaseBakMapper.maxId();
    }

    @Override
    public void dump(DatabaseBak databaseBak) {
        //执行sql语句
        databaseBakMapper.dump(databaseBak);
    }

    @Override
    public DatabaseBak getByFileName(String filename) {
        return databaseBakMapper.getByFileName(filename);
    }

}

