package com.gdiot.ssm.action;

import com.gdiot.ssm.dao.UserMapper;
import com.gdiot.ssm.entity.User;
import com.gdiot.ssm.session.SessionUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ZhouHR
 */
@RestController
public class UserAction {

    @Autowired
    UserMapper userMapper;

    @Autowired
    SessionUtil sessionUtil;

    @RequestMapping("/gettoken")
    public Map<String, String> gettoken(String appKey, String appSecret) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("code", "-1");
        map.put("msg", "参数错误");

        if (StringUtils.isEmpty(appKey) || StringUtils.isEmpty(appSecret)) {
            return map;
        }

        List<User> list = userMapper.getUser(appKey, appSecret);

        if (list.size() != 1) {
            return map;
        }

        User user = list.get(0);
//      String enc=System.currentTimeMillis()+user.getRealname()+user.getName()+user.getDept();
//      String base64=new sun.misc.BASE64Encoder().encode(enc.getBytes());

        //放入redis session,把原来的失效
        String token = sessionUtil.setSession(user);

        map.put("token", token);
        map.put("code", "0");
        map.put("msg", "success");

//		sessionUtil.setSession(base64, user);
        return map;
    }

}
