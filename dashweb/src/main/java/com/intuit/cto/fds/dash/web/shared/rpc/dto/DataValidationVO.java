package com.intuit.cto.fds.dash.web.shared.rpc.dto;

public enum DataValidationVO {


    PASSIVE("Passive"),
    
    ACTIVE("Active"),
    
    NONE("None");
    private final String value;

    DataValidationVO(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DataValidationVO fromValue(String v) {
        for (DataValidationVO c: DataValidationVO.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
