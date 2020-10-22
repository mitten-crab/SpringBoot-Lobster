package com.lobster.service;

import com.lobster.entity.SysDept;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * 部门 Service
 *
 * @author Administrator
 * @date Mon Oct 19 13:02:05 CST 2020
 **/
public interface SysDeptService {

    /**
     * 查询-部门列表-分页
     *
     * @param sysDept  部门
     * @param pageable 分页参数对象
     * @return 部门列表分页对象
     */
    Page<SysDept> findAllByPage(SysDept sysDept, Pageable pageable);

    /**
     * 查询-部门列表
     *
     * @param sysDept 部门
     * @param sort    排序
     * @return 部门列表
     */
    List<SysDept> findAll(SysDept sysDept, Sort sort);

    /**
     * 保存-部门
     *
     * @param sysDept 部门
     * @return 部门
     */
    SysDept save(SysDept sysDept);

}
