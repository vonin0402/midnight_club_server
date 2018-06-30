package com.club.midnight.model.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserType {
    ADMINISTRATOR(99), KNIGHT(10), COMMON(0);

    public final int privilege;
}
