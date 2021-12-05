package cn.ting.video.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("work")
public class Work implements Serializable {

    @TableId(type = IdType.AUTO )
    private Integer id;
    @TableField("work_name")
    private String workName;
    @TableField("work_team")
    private String workTeam;
    @TableField("work_url")
    private String workUrl;

}
