package com.ecommerce.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ecommerce.common.Result;
import com.ecommerce.entity.FaqKnowledge;
import com.ecommerce.service.FaqKnowledgeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * FAQ知识库控制器
 */
@RestController
@RequestMapping("/api/faq")
@RequiredArgsConstructor
public class FaqKnowledgeController {

    private final FaqKnowledgeService faqService;

    /** 创建FAQ条目 */
    @PostMapping
    public Result<?> create(@RequestBody FaqKnowledge faq) {
        boolean success = faqService.save(faq);
        return success ? Result.success() : Result.fail("创建FAQ失败");
    }

    /** 批量创建FAQ条目 */
    @PostMapping("/batch")
    public Result<?> createBatch(@RequestBody List<FaqKnowledge> faqList) {
        boolean success = faqService.saveBatch(faqList);
        return success ? Result.success() : Result.fail("批量创建FAQ失败");
    }

    /** 按主键ID查询FAQ */
    @GetMapping("/{id}")
    public Result<?> getById(@PathVariable Long id) {
        FaqKnowledge faq = faqService.getById(id);
        if (faq != null) {
            return Result.success(faq);
        }
        return Result.notFound("FAQ不存在: " + id);
    }

    /** 按分类查询FAQ列表（按优先级降倒） */
    @GetMapping("/category/{category}")
    public Result<List<FaqKnowledge>> listByCategory(@PathVariable String category) {
        List<FaqKnowledge> list = faqService.list(
                new LambdaQueryWrapper<FaqKnowledge>()
                        .eq(FaqKnowledge::getCategory, category)
                        .orderByDesc(FaqKnowledge::getPriority));
        return Result.success(list);
    }

    /** 分页查询FAQ列表 */
    @GetMapping("/page")
    public Result<Page<FaqKnowledge>> page(@RequestParam(defaultValue = "1") Integer current,
                                           @RequestParam(defaultValue = "10") Integer size) {
        Page<FaqKnowledge> page = faqService.page(new Page<>(current, size));
        return Result.success(page);
    }

    /** 更新FAQ条目 */
    @PutMapping
    public Result<?> update(@RequestBody FaqKnowledge faq) {
        boolean success = faqService.updateById(faq);
        return success ? Result.success() : Result.fail("更新FAQ失败");
    }

    /** 删除FAQ条目 */
    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id) {
        boolean success = faqService.removeById(id);
        return success ? Result.success() : Result.fail("删除FAQ失败");
    }
}