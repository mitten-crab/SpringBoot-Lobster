package com.lobster.service;

import com.lobster.entity.SysUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * 用户信息 Service
 *
 * @author Administrator
 * @date Wed Oct 21 14:58:10 CST 2020
 **/
public interface SysUserService {

    /**
     * 查询用户列表-分页
     *
     * @param sysUser  用户
     * @param pageable 分页参数对象
     * @return 用户列表分页对象
     */
    Page<SysUser> findAllByPage(SysUser sysUser, Pageable pageable);

    List<SysUser> findAll(SysUser sysUser, Sort sort);

}
