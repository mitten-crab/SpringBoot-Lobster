package com.lobster.service;

import com.lobster.entity.SysUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

/**
 * 用户信息 Service
 *
 * @author Administrator
 * @date Wed Oct 21 14:58:10 CST 2020
 **/
public interface SysUserService {

    /**
     * 用户-查询-列表分页
     *
     * @param sysUser  用户
     * @param pageable 分页参数对象
     * @return 用户列表分页对象
     */
    Page<SysUser> findAllByPage(SysUser sysUser, Pageable pageable);

    /**
     * 用户-查询列表
     *
     * @param sysUser 用户
     * @param sort    排序
     * @return 用户列表
     */
    List<SysUser> findAll(SysUser sysUser, Sort sort);

    /**
     * 用户-查询-根据用户id
     *
     * @param id 用户id
     * @return 用户
     */
    Optional<SysUser> findById(String id);

    /**
     * 用户-保存
     *
     * @param sysUser 用户
     * @return 用户
     */
    SysUser save(SysUser sysUser);

    /**
     * 用户-修改
     *
     * @param sysUser 用户
     * @return 用户
     */
    SysUser update(SysUser sysUser);

    /**
     * 用户-删除
     *
     * @param ids 用户ID
     * @return 影响数
     */
    int delete(String[] ids);

}
