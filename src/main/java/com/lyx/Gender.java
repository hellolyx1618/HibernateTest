package com.lyx;

/**
 * Created by liyanxin on 2014/12/24.
 */
public enum Gender {

    MAIL("男性"), FMAIL("女性"), UNSPECIFIED("未知");

    private String value;

    private Gender(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
