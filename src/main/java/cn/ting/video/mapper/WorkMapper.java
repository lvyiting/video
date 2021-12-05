package cn.ting.video.mapper;

import cn.ting.video.dto.AllDto;
import cn.ting.video.pojo.Work;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.bytebuddy.asm.Advice;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkMapper extends BaseMapper<Work> {

    /**
     * 查询作品的详情信息
     */

//    @Select("select w.*,t.* from `work` as w " +
//            "INNER JOIN team as t " +
//            "on w.id=t.work_id " +
//            "where w.id=#{id}")
    @Select("select w.*,t.* from `work` as w,team as t " +
            "where " +
            "t.work_id=w.id " +
            "and " +
            "w.id=#{id}")
    public List<AllDto> selectWorkDetails(int id) ;
}
