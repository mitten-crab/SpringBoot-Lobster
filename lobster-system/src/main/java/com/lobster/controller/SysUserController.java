package com.lobster.controller;

import com.lobster.common.entity.PageDataInfo;
import com.lobster.common.entity.ResponseResult;
import com.lobster.entity.SysUser;
import com.lobster.service.SysUserService;
import com.lobster.vo.SysUserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 用户接口
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

        final PageRequest pageRequest = PageDataInfo.toPageParam(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "updateTime"));

        SysUser sysUser = new SysUser();
        sysUser.setUserName(userName);
        Page<SysUser> sysUserPage = sysUserService.findAllByPage(sysUser, pageRequest);

        final PageDataInfo pageDataInfo = PageDataInfo.toPageData(sysUserPage);

        List<SysUserVO> sysUserVOList = new ArrayList<SysUserVO>();
        pageDataInfo.getContent().stream().filter(Objects::nonNull).forEach(content -> {
            SysUserVO sysUserVO = new SysUserVO();
            BeanUtils.copyProperties(content, sysUserVO);
            sysUserVOList.add(sysUserVO);
        });
        pageDataInfo.setContent(sysUserVOList);

        return ResponseEntity.ok(ResponseResult.success(pageDataInfo));
    }

    /**
     * 新增-用户
     *
     * @param sysUserVO 用户信息
     * @return 无
     */
    @PostMapping(value = "/create")
    public ResponseEntity create(@Validated(SysUserVO.Create.class) @RequestBody SysUserVO sysUserVO) {
        SysUser sysUser = new SysUser();
        sysUser.setDeptId(sysUserVO.getDeptId());
        sysUser.setUserName(sysUserVO.getUserName());
        // TODO 校验登录名是否重用
        sysUser.setLoginName(sysUserVO.getLoginName());
        // TODO 需要加密
        sysUser.setPassword("123456");
        sysUserService.save(sysUser);
        return ResponseEntity.ok(ResponseResult.success());
    }

    /**
     * 更新-用户
     *
     * @param sysUserVO 用户信息
     * @return 无
     */
    @PutMapping(value = "/put")
    public ResponseEntity update(
            @Validated(SysUserVO.Update.class) @RequestBody SysUserVO sysUserVO
    ) {
        Optional<SysUser> sysUserOptional = sysUserService.findById(sysUserVO.getId());
        if (sysUserOptional.isPresent()) {
            final SysUser sysUser = sysUserOptional.get();
            BeanUtils.copyProperties(sysUserVO, sysUser, "id", "password", "userStatus", "remark", "createBy", "createTime");
            sysUserService.update(sysUser);
        } else {
            return ResponseEntity.ok(ResponseResult.error("用户不存在"));
        }
        return ResponseEntity.ok(ResponseResult.success());
    }

    /**
     * 删除-用户
     *
     * @param ids 用户ids
     * @return 无
     */
    @DeleteMapping("/delete/{ids}")
    public ResponseEntity delete(
            @PathVariable(name = "ids") String[] ids
    ) {
        sysUserService.delete(ids);
        return ResponseEntity.ok(ResponseResult.success());
    }

}
