package com.example.springbootplus.service.impl;

import com.example.springbootplus.entity.Menu;
import com.example.springbootplus.mapper.MenuMapper;
import com.example.springbootplus.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author code946
 * @since 2022-04-15
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> findByLevel1() {
        return menuMapper.selectByLevel1();
    }

    @Override
    public List<Menu> findByLevel2() {
        return menuMapper.selectByLevel2();
    }

    @Override
    public List<Menu> findByLevel3() {
        return menuMapper.selectByLevel3();
    }

    @Override
    public List<Menu> findByLevel1AndLevel2() {
        List<Menu> menuLevel1 = menuMapper.selectByLevel1();
        List<Menu> menuLevel2 = menuMapper.selectByLevel2();
        menuLevel1.addAll(menuLevel2);
        return menuLevel1;
    }

}
