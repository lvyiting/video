package cn.ting.video.service.impl;

import cn.ting.video.mapper.TeamMapper;
import cn.ting.video.pojo.Team;
import cn.ting.video.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
    TeamMapper teamMapper;

    @Override
    public Integer addTeam(Team team,int workId) {
        team.setWorkId(workId);
        int insert = teamMapper.insert(team);
        return insert;
    }
}
