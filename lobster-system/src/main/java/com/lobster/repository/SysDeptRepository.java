package com.lobster.repository;

import com.lobster.entity.SysDept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * 部门 Repository
 *
 * @author Administrator
 * @date Mon Oct 19 13:02:05 CST 2020
 **/
@Repository
public interface SysDeptRepository extends JpaRepository<SysDept, String>, JpaSpecificationExecutor<SysDept> {
}
