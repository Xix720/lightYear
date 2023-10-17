package com.example.springbootplus.service;

import com.example.springbootplus.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author code946
 * @since 2022-04-15
 */
public interface MenuService extends IService<Menu> {
    public List<Menu> findByLevel1();
    public List<Menu> findByLevel2();
    public List<Menu> findByLevel3();

    public List<Menu> findByLevel1AndLevel2();

}
