package com.example.springbootplus.service;

import com.example.springbootplus.entity.DatabaseBak;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author code946
 * @since 2022-04-15
 */
public interface DatabaseBakService extends IService<DatabaseBak> {
    public void deleteById(Long id);

    Long maxId();

    void dump(DatabaseBak databaseBak);

    DatabaseBak getByFileName(String filename);
}
