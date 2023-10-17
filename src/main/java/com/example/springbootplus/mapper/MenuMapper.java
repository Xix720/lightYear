package com.example.springbootplus.mapper;

import com.example.springbootplus.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
public interface MenuMapper extends BaseMapper<Menu> {
    public List<Menu> selectByLevel1();
    public List<Menu> selectByLevel2();
    public List<Menu> selectByLevel3();
}
