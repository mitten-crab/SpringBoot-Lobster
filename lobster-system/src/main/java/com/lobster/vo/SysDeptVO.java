package com.lobster.vo;

import com.lobster.common.vo.TabelBaseInfoVO;

import java.io.Serializable;

/**
 * 部门信息 SysDeptVO
 *
 * @author Administrator
 * @date 2020-10-23 15:14:48
 **/
public class SysDeptVO extends TabelBaseInfoVO implements Serializable {

    /**
     * 主键
     */
    private String id;

    /**
     * 部门父级id
     */
    private String parentId;

    /**
     * 层级关系
     */
    private String hierarchy;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 排序
     */
    private Integer deptSort;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getHierarchy() {
        return hierarchy;
    }

    public void setHierarchy(String hierarchy) {
        this.hierarchy = hierarchy;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Integer getDeptSort() {
        return deptSort;
    }

    public void setDeptSort(Integer deptSort) {
        this.deptSort = deptSort;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
