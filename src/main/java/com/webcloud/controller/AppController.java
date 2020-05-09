package com.webcloud.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.webcloud.constant.CommonConstants;
import com.webcloud.dto.PageDto;
import com.webcloud.pojo.App;
import com.webcloud.service.AppService;
import com.webcloud.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app")
@RequiredArgsConstructor
public class AppController extends ResponseVo {

    private final AppService service;


    /**
     * 查询分页
     *
     * @param page
     * @param key
     * @return
     */
    @GetMapping("/getInfoListPage")
    public ResponseVo getInfoListPage(PageDto page, String key) {
        App pojo = App.builder().build();
        if (StringUtils.isNotBlank(key)) {
        }
        final Page result = service.page(new Page(page.getPage(), page.getLimit()), Wrappers.query(pojo));
        return restResult(result.getRecords(), CommonConstants.SUCCESS, "", result.getTotal());
    }


    @GetMapping("/getPhPage")
    public ResponseVo getPhPage() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.orderByDesc("total");
        final Page result = service.page(new Page(1, 1000000), queryWrapper);
        return restResult(result.getRecords(), CommonConstants.SUCCESS, "", result.getTotal());
    }

    /**
     * 新增修改
     *
     * @return
     */
    @PostMapping("/saveOrUpdateInfo")
    public ResponseVo saveOrUpdate(App pojo) {
        return ok(service.saveOrUpdate(pojo));
    }

    @GetMapping("/setCount")
    public ResponseVo setCount(@RequestParam Integer id) {
        final App app = service.getById(id);
        Integer total = app.getTotal();
        total++;
        app.setTotal(total);
        service.saveOrUpdate(app);
        return ok();
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