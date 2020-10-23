package com.lobster.service.impl;

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

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
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

    @Override
    public List<SysUser> findAll(SysUser sysUser, Sort sort) {
        return sysUserRepository.findAll(Example.of(sysUser), sort);
    }

}
