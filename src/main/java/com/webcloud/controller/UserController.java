package com.webcloud.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.webcloud.constant.CommonConstants;
import com.webcloud.dto.PageDto;
import com.webcloud.pojo.User;
import com.webcloud.service.UserService;
import com.webcloud.vo.ResponseVo;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController extends ResponseVo {

    private final UserService service;

    @PostMapping("/adminLogin")
    public ResponseVo adminLogin(@RequestParam String username, @RequestParam String password) {
        if (CommonConstants.USERNAME.equals(username) && CommonConstants.PASSWORD.equals(password)) {
            return ok();
        }
        return failed("用户名或密码错误");
    }

    @PostMapping("/login")
    public ResponseVo login(@RequestParam String username, @RequestParam String password) {
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        User build = User.builder().username(username).password(password).build();
        User one = service.getOne(Wrappers.query(build));
        if (Objects.nonNull(one)) {
            return ok(one);
        }
        return failed("用户名或密码错误");
    }

    /**
     * 找回秘密
     *
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/findPwd")
    public ResponseVo findPwd(@RequestParam String username, @RequestParam String password) {
        User build = User.builder().username(username).build();
        User one = service.getOne(Wrappers.query(build));
        if (Objects.isNull(one)) {
            return failed("此账号不存在,请核对后再次输入");
        }
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        one.setPassword(password);
        service.saveOrUpdate(one);
        return ok();
    }

    @PostMapping("/register")
    public ResponseVo register(@RequestParam String username, @RequestParam String password) {
        User build = User.builder().username(username).build();
        User one = service.getOne(Wrappers.query(build));
        if (Objects.nonNull(one)) {
            return failed("此账户已被占用,请更换!");
        }
        build.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        service.saveOrUpdate(build);
        return ok();
    }


    /**
     * 查询分页
     *
     * @param page
     * @param key
     * @return
     */
    @GetMapping("/getInfoListPage")
    public ResponseVo getInfoListPage(PageDto page, String key) {
        User pojo = User.builder().build();
        if (StringUtils.isNotBlank(key)) {
        }
        final Page result = service.page(new Page(page.getPage(), page.getLimit()), Wrappers.query(pojo));
        return restResult(result.getRecords(), CommonConstants.SUCCESS, "", result.getTotal());
    }

    /**
     * 新增修改
     *
     * @return
     */
    @PostMapping("/saveOrUpdateInfo")
    public ResponseVo saveOrUpdate(User pojo) {
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