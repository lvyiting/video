package cn.ting.video.dao;

import cn.ting.video.pojo.Team;

import java.util.List;

public class TeamDAO {
    public void save(List<Team> list) {
        // 如果是mybatis,尽量别直接调用多次insert,自己写一个mapper里面新增一个方法batchInsert,所有数据一次性插入
    }
}
