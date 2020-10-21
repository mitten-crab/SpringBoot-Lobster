package com.lobster.controller;

import com.lobster.common.entity.ResponseResult;
import com.lobster.entity.SysUser;
import com.lobster.service.SysUserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @ApiOperation(value = "获取用户列表")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "userName", value = "用户名称", dataTypeClass = String.class, required = false, paramType = "query", example = "管理员")
                    , @ApiImplicitParam(name = "page", value = "页码", dataTypeClass = int.class, required = true, paramType = "query", example = "1")
                    , @ApiImplicitParam(name = "size", value = "页大小", dataTypeClass = int.class, required = true, paramType = "query", example = "10")
            }
    )
    @ApiModelProperty(name = "id", notes = "主键哦")
    @PostMapping(value = "/list")
    public ResponseEntity list(
            @RequestParam(name = "userName", required = false) String userName,
            @RequestParam(name = "pageNumber", required = false) int pageNumber,
            @RequestParam(name = "pageSize", required = false) int pageSize
    ) {

        final PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "updateTime"));

        SysUser sysUser = new SysUser();
        Page<SysUser> sysUserPage = sysUserService.findAllByPage(sysUser, pageRequest);

        return new ResponseEntity<>(ResponseResult.success(sysUserPage), HttpStatus.OK);

    }

}
