package com.intuit.cto.fds.dash.web.shared.rpc.dto;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for agentType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="agentType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="NGWI"/>
 *     &lt;enumeration value="OfxAgent"/>
 *     &lt;enumeration value="WebServiceAgent"/>
 *     &lt;enumeration value="HostedDataServiceAgent"/>     
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "agentType")
@XmlEnum
public enum AgentTypeVO {

	 NGWI("NGWI"),
	 @XmlEnumValue("OfxAgent")
	 OFX_AGENT("OfxAgent"),
	 @XmlEnumValue("WebServiceAgent")
	 WEBSERVICE_AGENT("WebServiceAgent"),
	 @XmlEnumValue("HostedDataServiceAgent")
	 HOSTED_DATA_SERVICE_AGENT("HostedDataServiceAgent");
	 
	 private final String value;

    AgentTypeVO(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AgentTypeVO fromValue(String v) {
        for (AgentTypeVO c: AgentTypeVO.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
