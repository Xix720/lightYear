package com.example.springbootplus.mapper;

import com.example.springbootplus.entity.DatabaseBak;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author code946
 * @since 2022-04-15
 */
@Mapper
public interface DatabaseBakMapper extends BaseMapper<DatabaseBak> {
    //查询所有
    public List<DatabaseBak> selectAll();
    //根据id删除
    public void deleteById(Long id);

    Long maxId();

    void dump(DatabaseBak databaseBak);

    DatabaseBak getByFileName(String filename);
}
