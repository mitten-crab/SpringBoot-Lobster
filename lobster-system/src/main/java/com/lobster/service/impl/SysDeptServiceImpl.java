package com.lobster.service.impl;

import com.lobster.entity.SysDept;
import com.lobster.repository.SysDeptRepository;
import com.lobster.service.SysDeptService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 部门 Service
 *
 * @author Administrator
 * @date Mon Oct 19 13:02:05 CST 2020
 **/
@Service
public class SysDeptServiceImpl implements SysDeptService {

    @Resource
    private SysDeptRepository sysDeptRepository;

    /**
     * 查询部门列表-分页
     *
     * @param sysDept  部门
     * @param pageable 分页参数对象
     * @return 部门列表分页对象
     */
    @Override
    public Page<SysDept> findAllByPage(SysDept sysDept, Pageable pageable) {
        Example<SysDept> example = Example.of(sysDept);
        return sysDeptRepository.findAll(example, pageable);
    }

    @Override
    public List<SysDept> findAll(SysDept sysDept, Sort sort) {
        return sysDeptRepository.findAll(Example.of(sysDept), sort);
    }

}
