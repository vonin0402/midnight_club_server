package com.club.midnight.service;

import com.club.midnight.model.domain.UserInfo;
import com.club.midnight.model.enums.UserType;
import com.club.midnight.util.ExceptionLogUtils;
import com.club.midnight.util.JsonUtils;
import com.club.midnight.util.exception.JsonParsingException;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

import static com.club.midnight.util.CipherUtils.decrypt;

@Slf4j
@Service
public class UserInfoService implements InitializingBean {


    private Map<String, UserInfo> userMap;

    @Override
    public void afterPropertiesSet() throws Exception {
        userMap = Maps.newConcurrentMap();
        userMap.put("uadmin", new UserInfo("uadmin", "admin", UserType.ADMINISTRATOR));
    }

    public String signUp(HttpServletRequest request) {
        UserRequest userRequest = getUserRequest(request);
        if (userRequest != null) {
            if (userMap.get(userRequest.id) != null) {
                return "already exists";
            }

            userMap.put(userRequest.id, UserInfo.create(userRequest.id, userRequest.password, UserType.COMMON));
            return "success";
        }
        return null;
    }

    public String logIn(HttpServletRequest request) {
        UserRequest userRequest = getUserRequest(request);
        if (userRequest != null) {
            UserInfo userInfo = userMap.get(userRequest.id);
            if (isValidUser(userInfo, userRequest.password)) {
                return JsonUtils.toJson(UserInfo.createForLogIn(userInfo));
            } else {
                return null;
            }
        }
        return null;
    }

    public String levelUp(HttpServletRequest request) {
        UserRequest userRequest = getUserRequest(request);
        if (userRequest != null) {
            UserInfo userInfo = userMap.get(userRequest.id);
            if (userInfo.getUserType() == UserType.ADMINISTRATOR) {
                UserInfo targetInfo = userMap.get(userRequest.target);

                if (targetInfo == null) return "invalid user";

                targetInfo.setUserType(UserType.valueOf(userRequest.userType));
                userMap.put(userRequest.target, targetInfo);
                return "level upgraded";
            }
            return "you are not an admin";
        }
        return null;
    }

    public void leave(String id) {
        userMap.remove(id);
    }

    private boolean isValidUser(UserInfo userInfo, String password) {
        return userInfo != null && StringUtils.isNotEmpty(userInfo.getPassword()) && (decrypt(userInfo.getPassword())).equals(password);
    }

    private UserRequest getUserRequest(HttpServletRequest request) {
        try {
            return JsonUtils.fromJson(IOUtils.toString(request.getReader()), UserRequest.class);
        } catch (IOException | JsonParsingException e) {
            ExceptionLogUtils.error(log, e);
        }
        return null;
    }

    private static class UserRequest {
        public String id;
        public String target;
        public String password;
        public String userType;
    }

}
