package com.lobster.common.entity;

import javax.persistence.Column;
import java.util.Date;

/**
 * 基础信息 TabelBaseInfo
 *
 * @author Administrator
 * @date 2020-10-23 15:16:24
 **/
public class TabelBaseInfo {

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

    public TabelBaseInfo() {
    }

    public TabelBaseInfo(String createBy, Date createTime, String updateBy, Date updateTime, String delFlag) {
        this.createBy = createBy;
        this.createTime = createTime;
        this.updateBy = updateBy;
        this.updateTime = updateTime;
        this.delFlag = delFlag;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

}
