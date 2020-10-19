package com.lobster.service;

import com.lobster.entity.SysDept;
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

    Object findAllByPage(SysDept sysDept, Pageable pageable);

    List<SysDept> findAll(SysDept sysDept, Sort sort);

}
