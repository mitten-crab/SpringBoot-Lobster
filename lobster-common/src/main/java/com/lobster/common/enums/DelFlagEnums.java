package com.lobster.common.enums;

/**
 * 删除标志（0：删除、1：正常）
 */
public enum DelFlagEnums {

    /**
     * 删除
     */
    DELETED("0", "删除"),

    /**
     * 正常
     */
    OK("1", "正常");

    private final String code;
    private final String info;

    DelFlagEnums(String code, String info) {
        this.code = code;
        this.info = info;
    }

    public String getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }

}