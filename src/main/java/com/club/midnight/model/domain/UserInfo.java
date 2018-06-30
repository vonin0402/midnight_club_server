package com.club.midnight.model.domain;

import com.club.midnight.model.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static com.club.midnight.util.CipherUtils.encrypt;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class UserInfo {
    private String id;

    private String password;

    private UserType userType;

    public static UserInfo create(String id, String password, UserType userType) {
        return new UserInfo(id, encrypt(password), userType);
    }
}