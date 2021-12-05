package cn.ting.video.controller;

import cn.ting.video.exception.CommonReturnType;
import cn.ting.video.pojo.Team;
import cn.ting.video.pojo.Work;
import cn.ting.video.service.TeamService;
import cn.ting.video.service.VodService;
import cn.ting.video.service.WorkService;
import cn.ting.video.util.ConstantVodUtils;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.fastjson.JSON;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import com.google.gson.Gson;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/videoservice/")
@CrossOrigin
public class VideoController {
    @Autowired
    VodService vodService;

    @Autowired
    WorkService workService;

    @Autowired
    TeamService teamService;

    @ApiOperation("上传阿里云视频")
    @PostMapping("uploadAlyVideo")
    public CommonReturnType uploadAlyVideo(@RequestParam MultipartFile file) {
        String videoId = vodService.uploadVideoAly(file);
        String s = GetPlayInfo(videoId);
        return CommonReturnType.create(200, s, "视频上传成功");
    }


    @ApiOperation("读取Excel")
    @PostMapping("readExcel")
    public CommonReturnType simpleRead(@RequestParam MultipartFile file) throws IOException {
        List list = new ArrayList();
        InputStream inputStream = file.getInputStream();
        EasyExcel.read(inputStream, Team.class, new PageReadListener<Team>(dataList -> {
            for (Team team : dataList) {
                System.out.println(JSON.toJSONString(team));
                list.add(team);
            }
        })).sheet().doRead();
        return CommonReturnType.create(200, list, "导入成功");
    }

    @ApiOperation("添加队员所有信息")
    @PostMapping("addArrayTeam/{workId}")
    public CommonReturnType getArrayTeam(@RequestBody List<Team> teams,@PathVariable Integer workId) {

        for (int i = 0; i < teams.size(); i++) {
            Team team = teams.get(i);
            teamService.addTeam(team,workId);
        }
        return CommonReturnType.create(200, null, "添加成功");
    }

    @ApiOperation("添加作品信息")
    @PostMapping("addWork")
    public CommonReturnType getArrayTeam(@RequestBody Work work) {
        if (work == null) {
//            throw new BusinessException(CommonErrorCode.E_101);
        }
        Work work1 = workService.addWorkInfo(work);
        Integer id = work1.getId();
        return CommonReturnType.create(200, id, "添加成功");
    }

    /**
     * 获取上传视频地址
     *
     * @param vid 视频id
     */
    public static String GetPlayInfo(String vid) {
        // 创建SubmitMediaInfoJob实例并初始化
        DefaultProfile profile = DefaultProfile.getProfile(
                "cn-Shanghai",                // // 点播服务所在的地域ID，中国大陆地域请填cn-shanghai
                ConstantVodUtils.ACCESS_KEY_ID,        // 您的AccessKey ID
                ConstantVodUtils.ACCESS_KEY_SECRET);    // 您的AccessKey Secret
        IAcsClient client = new DefaultAcsClient(profile);
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        // 视频ID。
        request.setVideoId(vid);
        String url = null;
        String newurl = null;
        try {
            GetPlayInfoResponse response = client.getAcsResponse(request);
            System.out.println(new Gson().toJson(response));
            for (GetPlayInfoResponse.PlayInfo playInfo : response.getPlayInfoList()) {
                // 播放地址
                System.out.println("PlayInfo.PlayURL = " + playInfo.getPlayURL());
                String str = playInfo.getPlayURL();
                //这里会返回m3u8和mp4格式，m3u8需要转码，看自己情况
                //如果播放地址后缀为mp4返回
                if (str != null || str != "") {
//                    if(str.substring(str.length()-3,str.length()).equals("mp4")) {
                    url = playInfo.getPlayURL();
                    int index = url.indexOf("?");
                    newurl = url.substring(0, index);
//                    }
                }
            }
            return url;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
