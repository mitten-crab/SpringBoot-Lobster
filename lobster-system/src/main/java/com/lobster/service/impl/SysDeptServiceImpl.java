package com.lobster.service.impl;

import com.lobster.entity.SysDept;
import com.lobster.repository.SysDeptRepository;
import com.lobster.service.SysDeptService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * 部门 Service
 *
 * @author Administrator
 * @date Mon Oct 19 13:02:05 CST 2020
 **/
@Service
public class SysDeptServiceImpl implements SysDeptService {

    @Resource
    private SysDeptRepository sysDeptRepository;

    /**
     * 查询-部门列表-分页
     *
     * @param sysDept  部门
     * @param pageable 分页参数对象
     * @return 部门列表分页对象
     */
    @Override
    public Page<SysDept> findAllByPage(SysDept sysDept, Pageable pageable) {
        sysDept.setDelFlag("0");
        //直接使用匿名内部类实现接口
        Specification<SysDept> specification = new Specification<SysDept>() {
            @Override
            public Predicate toPredicate(Root<SysDept> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<>();
                predicateList.add(cb.equal(root.get("delFlag").as(String.class), sysDept.getDelFlag()));
                //条件1：查询 tvName 为 海信 的数据，root.get 中的值与 TV 实体中的属性名称对应
                if (StringUtils.isNotBlank(sysDept.getDeptName())) {
                    predicateList.add(cb.like(root.get("deptName").as(String.class), "%" + sysDept.getDeptName() + "%"));
                }
                //条件2：TV 生产日期（dateOfProduction）大于等于 start 的数据，root.get 中的 dateOfProduction 必须对应 TV 中的属性
//                predicateList.add(cb.greaterThanOrEqualTo(root.get("dateOfProduction").as(Date.class), start));
                //条件3：TV 生产日期（dateOfProduction）小于等于 end
//                predicateList.add(cb.lessThanOrEqualTo(root.get("dateOfProduction").as(Date.class), end));
                Predicate[] pre = new Predicate[predicateList.size()];
                return query.where(predicateList.toArray(pre)).getRestriction();
            }
        };
        return sysDeptRepository.findAll(specification, pageable);
    }

    /**
     * 查询-部门列表
     *
     * @param sysDept 部门
     * @param sort    排序
     * @return 部门列表
     */
    @Override
    public List<SysDept> findAll(SysDept sysDept, Sort sort) {
        return sysDeptRepository.findAll(Example.of(sysDept), sort);
    }

    /**
     * 保存-部门
     *
     * @param sysDept 部门
     * @return 部门
     */
    @Override
    public SysDept save(SysDept sysDept) {
        return sysDeptRepository.save(sysDept);
    }

}
