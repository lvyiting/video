package cn.ting.video.service;

import cn.ting.video.dto.AllDto;
import cn.ting.video.pojo.Work;

import java.util.List;

public interface WorkService {
    /**
     * 添加作品信息
     * @param work
     * @return
     */
    Work addWorkInfo(Work work);

    /**
     * 根据参赛队伍（团队名称）查询信息
     */
    List<Work> selectByWorkTeam(String workTeam);

    /**
     * 显示所有团队信息
     */
    List<Work> selectAllTeam();

    /**
     * 显示详情信息
     */
    List<AllDto> selectDetails(int id);



}
