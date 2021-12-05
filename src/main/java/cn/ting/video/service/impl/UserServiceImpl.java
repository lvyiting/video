package cn.ting.video.service.impl;

import cn.ting.video.mapper.UserMapper;
import cn.ting.video.pojo.User;
import cn.ting.video.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper mapper;

    @Override
    public User login(User user) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(User::getAccount,user.getAccount())
                .eq(User::getPassword,user.getPassword());
        User user1 = mapper.selectOne(lambdaQueryWrapper);
        return user1;
    }
}
