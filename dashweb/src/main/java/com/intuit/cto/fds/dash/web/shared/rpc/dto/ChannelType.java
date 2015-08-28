package com.intuit.cto.fds.dash.web.shared.rpc.dto;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public enum ChannelType implements IsSerializable, Serializable{

   
    OFX_2("ofx2"),
  
    WEB_SERVICE("webService"),
   
    WEB_INTEGRATION("webIntegration"),
    
    INTUIT_HOSTED ("intuitHosted");
    
    private final String value;

    ChannelType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ChannelType fromValue(String v) {
        for (ChannelType c: ChannelType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
