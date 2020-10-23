package com.lobster.controller;

import com.lobster.common.entity.PageDataInfo;
import com.lobster.common.entity.ResponseResult;
import com.lobster.entity.SysUser;
import com.lobster.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 用户 Entity
 *
 * @author Administrator
 * @date Wed Oct 21 14:58:10 CST 2020
 */
@Controller
@ResponseBody
@RequestMapping("/sysUser")
@Api(value = "用户信息", tags = "用户信息接口")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;


    /**
     * @api {POST} /v1.0.0/sysUser/list 获取用户列表
     * @apiGroup 用户
     * @apiVersion 1.0.0
     */
    @ApiOperation(value = "获取用户列表")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "userName", value = "用户名称", dataTypeClass = String.class, required = false, paramType = "query", example = "管理员")
                    , @ApiImplicitParam(name = "page", value = "页码", dataTypeClass = Integer.class, required = true, paramType = "query", example = "1")
                    , @ApiImplicitParam(name = "size", value = "页大小", dataTypeClass = Integer.class, required = true, paramType = "query", example = "10")
            }
    )
    @PostMapping(value = "/list")
    public ResponseEntity list(
            @RequestBody Map<Object, Object> map
    ) {

        // 用户名称
        String userName = (String) map.get("userName");
        // 页码
        int pageNumber = (int) map.get("pageNumber");
        // 页大小
        int pageSize = (int) map.get("pageSize");

        final PageRequest pageRequest =  PageDataInfo.toPageParam(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "updateTime"));

        SysUser sysUser = new SysUser();
        sysUser.setUserName(userName);
        Page<SysUser> sysUserPage = sysUserService.findAllByPage(sysUser, pageRequest);

        final PageDataInfo pageDataInfo = PageDataInfo.toPageData(sysUserPage);

        return ResponseEntity.ok(ResponseResult.success(pageDataInfo));
    }

}
