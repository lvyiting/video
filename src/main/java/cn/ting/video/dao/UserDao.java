package cn.ting.video.dao;

import cn.ting.video.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    User login(User user);
}
