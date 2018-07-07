package com.tag.midnight.club.server.model.domain;

import com.tag.midnight.club.server.util.CipherUtils;
import com.tag.midnight.club.server.model.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class UserInfo {
    private String id;

    private String password;

    private UserType userType;

    public static UserInfo create(String id, String password, UserType userType) {
        return new UserInfo(id, CipherUtils.encrypt(password), userType);
    }

    public static UserInfo createForLogIn(UserInfo userInfo) {
        return new UserInfo(userInfo.getId(), null, userInfo.getUserType());
    }
}