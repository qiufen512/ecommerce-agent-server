package com.ecommerce.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ecommerce.common.response.Response;
import com.ecommerce.entity.FaqKnowledge;
import com.ecommerce.model.vo.knowledge.FaqKnowledgeCreateVO;
import com.ecommerce.model.vo.knowledge.FaqKnowledgeQueryVO;
import com.ecommerce.model.vo.knowledge.FaqKnowledgeResponseVO;
import com.ecommerce.model.vo.knowledge.FaqKnowledgeUpdateVO;
import com.ecommerce.service.FaqKnowledgeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.*;

import javax.validation.Valid;

/**
 * FAQ知识库控制器
 */
@Api(tags = "FAQ知识库")
@RestController
@RequestMapping("/api/faq")
@RequiredArgsConstructor
@Validated
public class FaqKnowledgeController {

    private final FaqKnowledgeService faqService;

    /** 创建FAQ条目 */
    @ApiOperation("创建FAQ条目")
    @PostMapping
    public Response create(@Valid @RequestBody FaqKnowledgeCreateVO vo) {
        FaqKnowledge faq = new FaqKnowledge();
        BeanUtils.copyProperties(vo, faq);
        boolean success = faqService.save(faq);
        return success ? Response.success() : Response.fail("创建FAQ失败");
    }

    /** 批量创建FAQ条目 */
    @ApiOperation("批量创建FAQ条目")
    @PostMapping("/batch")
    public Response createBatch(@RequestBody List<FaqKnowledgeCreateVO> voList) {
        List<FaqKnowledge> faqList = new ArrayList<>();
        for (FaqKnowledgeCreateVO vo : voList) {
            FaqKnowledge faq = new FaqKnowledge();
            BeanUtils.copyProperties(vo, faq);
            faqList.add(faq);
        }
        boolean success = faqService.saveBatch(faqList);
        return success ? Response.success() : Response.fail("批量创建FAQ失败");
    }

    /** 按主键ID查询FAQ */
    @ApiOperation("按主键ID查询FAQ")
    @GetMapping("/{id}")
    public Response getById(@ApiParam("主键ID") @PathVariable Long id) {
        FaqKnowledge faq = faqService.getById(id);
        if (faq != null) {
            FaqKnowledgeResponseVO vo = new FaqKnowledgeResponseVO();
            BeanUtils.copyProperties(faq, vo);
            return Response.success(vo);
        }
        return Response.notFound("FAQ不存在: " + id);
    }

    /** 按分类查询FAQ列表（按优先级降倒） */
    @ApiOperation("按分类查询FAQ列表")
    @GetMapping("/category/{category}")
    public Response listByCategory(@ApiParam("分类") @PathVariable String category) {
        List<FaqKnowledge> list = faqService.list(
                new LambdaQueryWrapper<FaqKnowledge>()
                        .eq(FaqKnowledge::getCategory, category)
                        .orderByDesc(FaqKnowledge::getPriority));
        List<FaqKnowledgeResponseVO> voList = list.stream().map(faq -> {
            FaqKnowledgeResponseVO vo = new FaqKnowledgeResponseVO();
            BeanUtils.copyProperties(faq, vo);
            return vo;
        }).collect(java.util.stream.Collectors.toList());
        return Response.success(voList);
    }

    /** 分页查询FAQ列表 */
    @ApiOperation("分页查询FAQ列表")
    @GetMapping("/page")
    public Response page(@ApiParam("当前页") @RequestParam(defaultValue = "1") Integer current,
                                           @ApiParam("每页大小") @RequestParam(defaultValue = "10") Integer size,
                                           FaqKnowledgeQueryVO queryVO) {
        LambdaQueryWrapper<FaqKnowledge> wrapper = new LambdaQueryWrapper<>();
        if (queryVO.getQuestion() != null) {
            wrapper.like(FaqKnowledge::getQuestion, queryVO.getQuestion());
        }
        if (queryVO.getCategory() != null) {
            wrapper.eq(FaqKnowledge::getCategory, queryVO.getCategory());
        }
        if (queryVO.getMinPriority() != null) {
            wrapper.ge(FaqKnowledge::getPriority, queryVO.getMinPriority());
        }
        wrapper.orderByDesc(FaqKnowledge::getPriority);
        Page<FaqKnowledge> page = faqService.page(new Page<>(current, size), wrapper);
        Page<FaqKnowledgeResponseVO> voPage = new Page<>(page.getCurrent(), page.getSize(), page.getTotal());
        List<FaqKnowledgeResponseVO> voList = page.getRecords().stream().map(faq -> {
            FaqKnowledgeResponseVO vo = new FaqKnowledgeResponseVO();
            BeanUtils.copyProperties(faq, vo);
            return vo;
        }).collect(java.util.stream.Collectors.toList());
        voPage.setRecords(voList);
        return Response.success(voPage);
    }

    /** 更新FAQ条目 */
    @ApiOperation("更新FAQ条目")
    @PutMapping
    public Response update(@Valid @RequestBody FaqKnowledgeUpdateVO vo) {
        FaqKnowledge faq = new FaqKnowledge();
        BeanUtils.copyProperties(vo, faq);
        boolean success = faqService.updateById(faq);
        return success ? Response.success() : Response.fail("更新FAQ失败");
    }

    /** 删除FAQ条目 */
    @ApiOperation("删除FAQ条目")
    @DeleteMapping("/{id}")
    public Response delete(@ApiParam("主键ID") @PathVariable Long id) {
        boolean success = faqService.removeById(id);
        return success ? Response.success() : Response.fail("删除FAQ失败");
    }
}