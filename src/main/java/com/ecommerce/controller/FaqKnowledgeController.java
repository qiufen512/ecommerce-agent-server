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
 * FAQ Knowledge Base Controller
 */
@Api(tags = "FAQ Knowledge Base")
@RestController
@RequestMapping("/api/faq")
@RequiredArgsConstructor
@Validated
public class FaqKnowledgeController {

    private final FaqKnowledgeService faqService;

    /** Create FAQ Entry */
    @ApiOperation("Create FAQ Entry")
    @PostMapping
    public Response create(@Valid @RequestBody FaqKnowledgeCreateVO vo) {
        FaqKnowledge faq = new FaqKnowledge();
        BeanUtils.copyProperties(vo, faq);
        boolean success = faqService.save(faq);
        return success ? Response.success() : Response.fail("Failed to create FAQ");
    }

    /** Batch Create FAQ Entries */
    @ApiOperation("Batch Create FAQ Entries")
    @PostMapping("/batch")
    public Response createBatch(@RequestBody List<FaqKnowledgeCreateVO> voList) {
        List<FaqKnowledge> faqList = new ArrayList<>();
        for (FaqKnowledgeCreateVO vo : voList) {
            FaqKnowledge faq = new FaqKnowledge();
            BeanUtils.copyProperties(vo, faq);
            faqList.add(faq);
        }
        boolean success = faqService.saveBatch(faqList);
        return success ? Response.success() : Response.fail("Failed to batch create FAQ");
    }

    /** Query FAQ by Primary Key ID */
    @ApiOperation("Query FAQ by Primary Key ID")
    @GetMapping("/{id}")
    public Response getById(@ApiParam("Primary Key ID") @PathVariable Long id) {
        FaqKnowledge faq = faqService.getById(id);
        if (faq != null) {
            FaqKnowledgeResponseVO vo = new FaqKnowledgeResponseVO();
            BeanUtils.copyProperties(faq, vo);
            return Response.success(vo);
        }
        return Response.notFound("FAQ not found: " + id);
    }

    /** Query FAQ List by Category (Descending by Priority) */
    @ApiOperation("Query FAQ List by Category")
    @GetMapping("/category/{category}")
    public Response listByCategory(@ApiParam("Category") @PathVariable String category) {
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

    /** Paged Query of FAQ List */
    @ApiOperation("Paged Query of FAQ List")
    @GetMapping("/page")
    public Response page(@ApiParam("Current Page") @RequestParam(defaultValue = "1") Integer current,
                                           @ApiParam("Page Size") @RequestParam(defaultValue = "10") Integer size,
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

    /** Update FAQ Entry */
    @ApiOperation("Update FAQ Entry")
    @PutMapping
    public Response update(@Valid @RequestBody FaqKnowledgeUpdateVO vo) {
        FaqKnowledge faq = new FaqKnowledge();
        BeanUtils.copyProperties(vo, faq);
        boolean success = faqService.updateById(faq);
        return success ? Response.success() : Response.fail("Failed to update FAQ");
    }

    /** Delete FAQ Entry */
    @ApiOperation("Delete FAQ Entry")
    @DeleteMapping("/{id}")
    public Response delete(@ApiParam("Primary Key ID") @PathVariable Long id) {
        boolean success = faqService.removeById(id);
        return success ? Response.success() : Response.fail("Failed to delete FAQ");
    }
}