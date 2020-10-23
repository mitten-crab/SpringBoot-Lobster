package com.lobster.vo;


import com.lobster.common.vo.TabelBaseInfoVO;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 用户信息 SysUserVO
 *
 * @author Administrator
 * @date 2020-10-23 15:14:48
 **/
public class SysUserVO extends TabelBaseInfoVO implements Serializable {

    /**
     * 主键
     */
    @NotBlank(message = "用户ID不能为空", groups = Update.class)
    private String id;

    /**
     * 部门ID
     */
    @NotBlank(message = "部门ID不能为空", groups = {Create.class, Update.class})
    @Length(max = 50)
    private String deptId;

    /**
     * 登录账号
     */
    @NotBlank(message = "登录账号不能为空", groups = {Create.class, Update.class})
    @Length(min = 3, max = 50)
    private String loginName;

    /**
     * 用户名称
     */
    @NotBlank(message = "用户名称不能为空", groups = {Create.class, Update.class})
    @Length(min = 2, max = 50)
    private String userName;

    /**
     * 邮箱
     */
    @Email
    private String email;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 用户性别
     */
    private String sex;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空", groups = {Create.class})
    @Length(min = 8, max = 20)
    private String password;

    /**
     * 帐号状态（0：停用、1：正常）
     */
    private String userStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }
}
