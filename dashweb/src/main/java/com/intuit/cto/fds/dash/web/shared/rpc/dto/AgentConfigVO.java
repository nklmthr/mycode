package com.intuit.cto.fds.dash.web.shared.rpc.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for agentConfig complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="agentConfig">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="agentType" type="{}agentType"/>
 *         &lt;element name="parameter" type="{}parameter" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "agentConfig", propOrder = {
	"id",
    "agentType",
    "parameter"
})
@XmlRootElement(name="agentConfig")
public class AgentConfigVO implements Serializable {

	private static final long serialVersionUID = 1L;

	protected String id;
    @XmlElement(required = true)
    protected AgentTypeVO agentType;
    @XmlElement(required = false)
    protected List<ParameterVO> parameter;

    public void setParameter(List<ParameterVO> parameter) {
		this.parameter = parameter;
	}

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    
	/**
     * Gets the value of the agentType property.
     * 
     * @return
     *     possible object is
     *     {@link AgentTypeVO }
     *     
     */
    public AgentTypeVO getAgentType() {
        return agentType;
    }

    /**
     * Sets the value of the agentType property.
     * 
     * @param value
     *     allowed object is
     *     {@link AgentTypeVO }
     *     
     */
    public void setAgentType(AgentTypeVO value) {
        this.agentType = value;
    }

    /**
     * Gets the value of the parameter property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the parameter property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getParameter().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ParameterVO }
     * 
     * 
     */
    public List<ParameterVO> getParameter() {
        if (parameter == null) {
            parameter = new ArrayList<ParameterVO>();
        }
        return this.parameter;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AgentConfigVO [agentType=");
		builder.append(agentType);
		builder.append(", parameter=");
		builder.append(parameter);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		return this.agentType.value().hashCode();
	}

	@Override
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 * Equals based on the agent type since currently we can not have to agent configurations of the same type in a channel
	 */
	public boolean equals(Object object) {

		if (object == null || object.getClass() != getClass()) {
			return false;
		} else {
			AgentConfigVO agentConfig = (AgentConfigVO) object;
			return this.agentType.equals(agentConfig.agentType);
		}
	}

}
