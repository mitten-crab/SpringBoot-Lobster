package com.lobster.repository;

import com.lobster.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


/**
 * 用户信息 Repository
 *
 * @author Administrator
 * @date Wed Oct 21 14:58:10 CST 2020
 **/
@Repository
public interface SysUserRepository extends JpaRepository<SysUser, String>, JpaSpecificationExecutor<SysUser> {
}
