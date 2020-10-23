package com.lobster.controller;

import com.lobster.VO.SysDeptVO;
import com.lobster.common.entity.PageDataInfo;
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
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 部门接口
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

    /**
     * @api {POST} /v1.0.0/sysDept/list 获取部门列表
     * @apiGroup 部门
     * @apiVersion 1.0.0
     * @apiHeader {String} Authorization 用户授权token
     * @apiHeaderExample {json} Header-Example:
     * {"Authorization":"Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c"}
     * @apiParam {String} [deptName] 部门名称
     * @apiParam {Number} pageNumber 页码
     * @apiParam {Number} pageSize 页大小
     * @apiParamExample {json} Param-Example:
     * {
     * "deptName":"部",
     * "pageNumber":0,
     * "pageSize":10
     * }
     * @apiSuccess {String} id 主键
     * @apiSuccess {String} parentId 部门父级id
     * @apiSuccess {String} hierarchy 层级关系
     * @apiSuccess {String} deptName 部门名称
     * @apiSuccess {Number} deptSort 排序
     * @apiSuccessExample {json} Success-Response:
     * {
     * "msg":"操作成功",
     * "code":200,
     * "data":{
     * "content":[
     * {
     * "id":"100",
     * "parentId":"0",
     * "hierarchy":"0",
     * "deptName":"广东新华发行集团股份有限公司",
     * "deptSort":0,
     * "phone":"13333333333",
     * "email":"gdxh@xh.com",
     * "remark":null,
     * "createBy":"admin",
     * "createTime":"2020-04-20 17:03:40",
     * "updateBy":"admin",
     * "updateTime":"2020-04-22 12:39:33",
     * "delFlag":"0"
     * }
     * ],
     * "totalCount":5,
     * "totalPages":1,
     * "pageSize":10,
     * "pageNumber":0
     * }
     * }
     */
    @ApiOperation(value = "获取部门列表")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "deptName", value = "部门名称", dataType = "String", dataTypeClass = String.class, paramType = "query", example = "业务部"),
                    @ApiImplicitParam(name = "pageNum", value = "页码", dataType = "String", dataTypeClass = String.class, paramType = "query", example = "0"),
                    @ApiImplicitParam(name = "pageSize", value = "页大小", dataType = "String", dataTypeClass = String.class, paramType = "query", example = "10")
            }
    )
    @ResponseBody
    @PostMapping(value = "/list")
    public ResponseEntity list(
            @RequestBody Map<Object, Object> map
    ) {

        // 部门名称
        String deptName = (String) map.get("deptName");
        // 页码
        int pageNumber = (int) map.get("pageNumber");
        // 页大小
        int pageSize = (int) map.get("pageSize");

        final PageRequest pageRequest = PageDataInfo.toPageParam(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "updateTime"));

        SysDept sysDept = new SysDept();
        sysDept.setDeptName(deptName);
        Page<SysDept> sysDeptPage = sysDeptService.findAllByPage(sysDept, pageRequest);

        List<SysDeptVO> sysDeptVOList = new ArrayList<SysDeptVO>();
        sysDeptPage.forEach(sysDeptData -> {
            SysDeptVO sysDeptVO = new SysDeptVO();
            BeanUtils.copyProperties(sysDeptData, sysDeptVO);
            sysDeptVOList.add(sysDeptVO);
        });

        final PageDataInfo pageDataInfo = PageDataInfo.toPageData(sysDeptVOList, sysDeptPage);

        return ResponseEntity.ok(ResponseResult.success(pageDataInfo));
    }


    /**
     * @param deptName
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @PostMapping(value = "/create")
    public ResponseEntity create(
            @RequestParam(name = "deptName", defaultValue = "") String deptName,
            @RequestParam(name = "pageNum", defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize
    ) {


        return new ResponseEntity<ResponseResult>(ResponseResult.success(), HttpStatus.OK);
    }

    @ApiOperation("删除部门")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@RequestBody Set<String> ids) {

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
