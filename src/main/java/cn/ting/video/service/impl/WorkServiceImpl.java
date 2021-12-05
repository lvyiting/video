package cn.ting.video.service.impl;

import cn.ting.video.dto.AllDto;
import cn.ting.video.mapper.WorkMapper;
import cn.ting.video.pojo.Work;
import cn.ting.video.service.WorkService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkServiceImpl implements WorkService {


    @Autowired
    WorkMapper workMapper;

    /**
     * 添加作品信息
     * @param work
     * @return
     */
    @Override
    public Work addWorkInfo(Work work) {
        workMapper.insert(work);
        return  work;
    }

    /**
     * 根据参赛队伍（团队名称）查询信息
     * @return
     */
    @Override
    public List<Work> selectByWorkTeam(String workTeam) {
        LambdaQueryWrapper<Work> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Work::getWorkTeam,workTeam);
        List<Work> works = workMapper.selectList(lambdaQueryWrapper);
        return works;
    }

    @Override
    public List<Work> selectAllTeam() {
        List<Work> works = workMapper.selectList(null);
        return works;
    }
    /**
     * 查询作品的详情信息
     */
    @Override
    public List<AllDto> selectDetails(int id) {
        List<AllDto> works= workMapper.selectWorkDetails(id);
        return works;
    }
}
