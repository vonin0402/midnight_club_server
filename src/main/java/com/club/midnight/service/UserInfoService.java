package com.club.midnight.service;

import com.club.midnight.model.domain.UserInfo;
import com.club.midnight.model.enums.UserType;
import com.club.midnight.util.JsonUtils;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.club.midnight.util.CipherUtils.decrypt;

@Slf4j
@Service
public class UserInfoService implements InitializingBean {


    private Map<String, UserInfo> userMap;

    @Override
    public void afterPropertiesSet() throws Exception {
        userMap = Maps.newConcurrentMap();
    }

    public void signUp(String id, String password) {
        userMap.put(id, UserInfo.create(id, password, UserType.COMMON));
    }

    public String logIn(String id, String password) {
        UserInfo userInfo = userMap.get(id);
        if (isValidUser(userInfo, password)) {
            userInfo.setPassword(null);
            return JsonUtils.toJson(userInfo);
        } else {
            return null;
        }
    }

    private boolean isValidUser(UserInfo userInfo, String password) {
        return userInfo != null && StringUtils.isNotEmpty(userInfo.getPassword()) && (decrypt(userInfo.getPassword())).equals(password);
    }

    public String levelUp(String id, String target, String userType) {
        UserInfo userInfo = userMap.get(id);
        if (userInfo.getUserType() == UserType.ADMINISTRATOR) {
            UserInfo targetInfo = userMap.get(target);
            if (targetInfo == null) {
                return "invalid user";
            }
            targetInfo.setUserType(UserType.valueOf(userType));
            userMap.put(target, targetInfo);
            return "level upgraded";
        }
        return "you are not an admin";
    }

    public void leave(String id) {
        userMap.remove(id);
    }


}
