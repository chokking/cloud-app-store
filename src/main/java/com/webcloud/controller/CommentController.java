package com.webcloud.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.webcloud.constant.CommonConstants;
import com.webcloud.dto.PageDto;
import com.webcloud.pojo.Comment;
import com.webcloud.service.CommentService;
import com.webcloud.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController extends ResponseVo {

    private final CommentService service;


    /**
     * 查询分页
     *
     * @param page
     * @param key
     * @return
     */
    @GetMapping("/getInfoListPage")
    public ResponseVo getInfoListPage(PageDto page, String key) {
        Comment pojo = Comment.builder().build();
        if (StringUtils.isNotBlank(key)) {
        }
        final Page result = service.page(new Page(page.getPage(), page.getLimit()), Wrappers.query(pojo));
        return restResult(result.getRecords(), CommonConstants.SUCCESS, "", result.getTotal());
    }


    @GetMapping("/getByAppId")
    public ResponseVo getById(@RequestParam Integer appId) {
        return ok(service.list(Wrappers.query(Comment.builder().appId(appId).build())));
    }


    /**
     * 新增修改
     *
     * @return
     */
    @PostMapping("/saveOrUpdateInfo")
    public ResponseVo saveOrUpdate(Comment pojo) {
        pojo.setTime(new Date().toLocaleString());
        return ok(service.saveOrUpdate(pojo));
    }

    /**
     * 删除
     *
     * @return
     */
    @GetMapping("/deleteInfoList/{id}")
    public ResponseVo deleteInfoList(@PathVariable(name = "id") Integer id) {
        return ok(service.removeById(id));
    }


}