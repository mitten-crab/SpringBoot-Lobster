package com.lobster.controller;

import com.lobster.VO.SysDeptVO;
import com.lobster.common.entity.ResponseResult;
import com.lobster.entity.SysDept;
import com.lobster.service.SysDeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
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

import java.util.ArrayList;
import java.util.List;

/**
 * 部门 Entity
 *
 * @author Administrator
 * @date 2020-10-19 16:53:42
 */
@Controller
@ResponseBody
@RequestMapping("/sysDept")
@Api(value = "部门信息", tags = "部门信息接口")
public class SysDeptController {

    @Autowired
    private SysDeptService sysDeptService;

    @ApiOperation(value = "获取部门列表")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "deptName", value = "部门名称", dataType = "String", dataTypeClass = String.class, paramType = "query", example = "业务部"),
                    @ApiImplicitParam(name = "pageNum", value = "页码", dataType = "String", dataTypeClass = String.class, paramType = "query", example = "1"),
                    @ApiImplicitParam(name = "pageSize", value = "页大小", dataType = "String", dataTypeClass = String.class, paramType = "query", example = "10")
            }
    )
    @PostMapping(value = "/list")
    public ResponseEntity list(
            String deptName,
            @RequestParam(name = "pageNum", defaultValue = "1") int pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize
    ) {

        final PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "updateTime"));

        SysDept sysDept = new SysDept();
        Page<SysDept> sysDeptPage = sysDeptService.findAllByPage(sysDept, pageRequest);

        List<SysDeptVO> sysDeptVOList = new ArrayList<SysDeptVO>();
        sysDeptPage.forEach(sysDeptData -> {
            SysDeptVO sysDeptVO = new SysDeptVO();
            BeanUtils.copyProperties(sysDeptData, sysDeptVO);
            sysDeptVOList.add(sysDeptVO);
        });

        return new ResponseEntity<>(ResponseResult.success(sysDeptVOList), HttpStatus.OK);

    }

}
