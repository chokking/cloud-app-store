package com.webcloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.webcloud.mapper.UserMapper;
import com.webcloud.pojo.User;
import com.webcloud.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * User业务实现
 *
 * @author xt
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
@RequiredArgsConstructor
public class UserImpl extends ServiceImpl<UserMapper, User> implements UserService {
}