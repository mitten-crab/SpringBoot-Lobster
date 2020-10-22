package com.lobster.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 用户信息 Entity
 *
 * @author Administrator
 * @date Wed Oct 21 14:58:10 CST 2020
 **/
@Entity
@Table(name = "sys_user")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class SysUser implements Serializable {

    /**
     * 主键
     */
    @Id
    @Column(name = "id")
    private String id;

    /**
     * 部门ID
     */
    @Column(name = "dept_id")
    private String deptId;

    /**
     * 登录账号
     */
    @Column(name = "login_name")
    private String loginName;

    /**
     * 用户名称
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 邮箱
     */
    @Column(name = "email")
    private String email;

    /**
     * 手机号码
     */
    @Column(name = "phone")
    private String phone;

    /**
     * 用户性别（0：未知、1：男、2：女）
     */
    @Column(name = "sex")
    private String sex;

    /**
     * 盐加密
     */
    @Column(name = "salt")
    private String salt;

    /**
     * 密码
     */
    @Column(name = "password")
    private String password;

    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;

    /**
     * 帐号状态（0：停用、1：正常）
     */
    @Column(name = "status")
    private String status;

    /**
     * 创建者
     */
    @Column(name = "create_by")
    private String createBy;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private java.util.Date createTime;

    /**
     * 更新者
     */
    @Column(name = "update_by")
    private String updateBy;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private java.util.Date updateTime;

    /**
     * 删除标志（0：删除、1：正常）
     */
    @Column(name = "del_flag")
    private String delFlag;


    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeptId() {
        return this.deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getLoginName() {
        return this.loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSalt() {
        return this.salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateBy() {
        return this.createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return this.updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public java.util.Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(java.util.Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getDelFlag() {
        return this.delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

}
