package com.lobster.service.impl;

import com.lobster.common.enums.DelFlagEnums;
import com.lobster.common.utils.Snowflake;
import com.lobster.entity.SysUser;
import com.lobster.repository.SysUserRepository;
import com.lobster.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

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
     * 用户-查询-列表分页
     *
     * @param sysUser  用户
     * @param pageable 分页参数对象
     * @return 用户列表分页对象
     */
    @Override
    public Page<SysUser> findAllByPage(SysUser sysUser, Pageable pageable) {
        sysUser.setDelFlag("0");
        Specification<SysUser> specification = new Specification<SysUser>() {
            @Override
            public Predicate toPredicate(Root<SysUser> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<>();
                predicateList.add(cb.equal(root.get("delFlag").as(String.class), sysUser.getDelFlag()));
                if (StringUtils.isNotBlank(sysUser.getUserName())) {
                    predicateList.add(cb.like(root.get("userName").as(String.class), "%" + sysUser.getUserName() + "%"));
                }
                Predicate[] pre = new Predicate[predicateList.size()];
                return query.where(predicateList.toArray(pre)).getRestriction();
            }
        };
        return sysUserRepository.findAll(specification, pageable);
    }

    /**
     * 用户-查询列表
     *
     * @param sysUser 用户
     * @param sort    排序
     * @return 用户列表
     */
    @Override
    public List<SysUser> findAll(SysUser sysUser, Sort sort) {
        return sysUserRepository.findAll(Example.of(sysUser), sort);
    }

    /**
     * 用户-查询-根据用户id
     *
     * @param id 用户id
     * @return 用户
     */
    @Override
    public Optional<SysUser> findById(String id) {
        return sysUserRepository.findById(id);
    }

    /**
     * 保存-用户
     *
     * @param sysUser 用户
     * @return 用户
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public SysUser save(SysUser sysUser) {
        try {
            sysUser.setId(Snowflake.getSnowflakeId());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        sysUser.setCreateBy(null);
        sysUser.setCreateTime(new Date());
        sysUser.setUpdateBy(null);
        sysUser.setUpdateTime(new Date());
        sysUser.setDelFlag(DelFlagEnums.OK.getCode());
        return sysUserRepository.save(sysUser);
    }

    /**
     * 修改-用户
     *
     * @param sysUser 用户
     * @return 用户
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public SysUser update(SysUser sysUser) {
        sysUser.setUpdateBy(null);
        sysUser.setUpdateTime(new Date());
        return sysUserRepository.save(sysUser);
    }

    /**
     * 删除-用户
     *
     * @param ids 用户ID
     * @return 影响数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(String[] ids) {
        AtomicInteger count = new AtomicInteger();
        Arrays.stream(ids).forEach(id -> {
            Optional<SysUser> sysUser = sysUserRepository.findById(id);
            if (sysUser.isPresent()) {
                SysUser data = sysUser.get();
                data.setUpdateBy(null);
                data.setUpdateTime(new Date());
                data.setDelFlag(DelFlagEnums.DELETED.getCode());
                sysUserRepository.save(data);
                count.getAndIncrement();
                // TODO 删除用户与角色关联表
            }
        });
        return count.get();
    }

}
