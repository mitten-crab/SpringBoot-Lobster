package com.lobster.service.impl;

import com.lobster.entity.SysUser;
import com.lobster.repository.SysUserRepository;
import com.lobster.service.SysUserService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户信息 Service
 *
 * @author Administrator
 * @date Wed Oct 21 14:58:10 CST 2020
 **/
@Service
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private SysUserRepository sysUserRepository;

    /**
     * 查询用户列表-分页
     *
     * @param sysUser  用户
     * @param pageable 分页参数对象
     * @return 用户列表分页对象
     */
    @Override
    public Page<SysUser> findAllByPage(SysUser sysUser, Pageable pageable) {
        Example<SysUser> example = Example.of(sysUser);
        return sysUserRepository.findAll(example, pageable);
    }

    @Override
    public List<SysUser> findAll(SysUser sysUser, Sort sort) {
        return sysUserRepository.findAll(Example.of(sysUser), sort);
    }

}
