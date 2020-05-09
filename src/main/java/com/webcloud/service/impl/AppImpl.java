package com.webcloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.webcloud.mapper.AppMapper;
import com.webcloud.pojo.App;
import com.webcloud.service.AppService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * App业务实现
 *
 * @author xt
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
@RequiredArgsConstructor
public class AppImpl extends ServiceImpl<AppMapper, App> implements AppService {
}