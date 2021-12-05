package cn.ting.video.controller;

import cn.ting.video.dto.AllDto;
import cn.ting.video.exception.CommonReturnType;
import cn.ting.video.pojo.User;
import cn.ting.video.pojo.Work;
import cn.ting.video.service.TeamService;
import cn.ting.video.service.UserService;
import cn.ting.video.service.WorkService;
import cn.ting.video.util.JWTUtils;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user/")
@CrossOrigin
public class TeamWorkController {
    @Autowired
    WorkService workService;

    @Autowired
    TeamService teamService;

    @Autowired
    private UserService userService;

    @PostMapping("login")
    public Map<String, Object> login(@RequestBody User user) {
        Map<String, Object> map = new HashMap<>();
        try {
            User login = userService.login(user);
            Map<String, String> map1 = new HashMap<>();
            map1.put("id", login.getId());
            map1.put("account", login.getAccount());
            //生成token令牌
            String token = JWTUtils.getToken(map1);
            map.put("state", true);
            map.put("msg", "认证成功");
            map.put("token", token);
        } catch (Exception e) {
            map.put("state", false);
            map.put("msg", e.getMessage());
        }
        return map;
    }

    @ApiOperation("根据参赛队伍（团队名称）查询信息")
    @GetMapping("queryWorkTeam")
    public CommonReturnType getArrayTeam(String workTeam) {
        List<Work> works = workService.selectByWorkTeam(workTeam);
        return CommonReturnType.create(200, works, "查询成功");
    }

    @ApiOperation("显示所有队伍")
    @GetMapping("queryAllTeam")
    public CommonReturnType queryAllTeam() {
        List<Work> works = workService.selectAllTeam();
        return CommonReturnType.create(200, works, "查询成功");
    }

    @ApiOperation("根据作品id查询详情")
    @GetMapping("queryWorkDetails")
    public CommonReturnType queryWorkDetails(int id) {
        List<AllDto> works = workService.selectDetails(id);
        return CommonReturnType.create(200, works, "查询成功");
    }

    @PostMapping("test")
    public Map<String, Object> test(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        String token = request.getHeader("token");
        DecodedJWT verify = JWTUtils.verify(token);
        map.put("data", verify.getClaim("account").asString());
        map.put("state", true);
        map.put("msg", "成功");
        return map;
    }
}
