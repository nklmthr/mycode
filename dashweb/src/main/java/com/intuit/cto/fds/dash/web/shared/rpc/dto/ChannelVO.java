package com.intuit.cto.fds.dash.web.shared.rpc.dto;

import java.io.Serializable;
import java.util.List;
public abstract class ChannelVO implements Serializable, Comparable<ChannelVO> {

	private static final long serialVersionUID = 1L;

	protected String id;
	protected String channelType;
	protected String url;
	protected Integer preference;	
	protected String location;
	protected Boolean usesMFA;	
	protected AuthenticationInfoVO authenticationInfo;	
	protected GlobalConfigVO globalConfig;
	protected List<AgentConfigVO> agentConfigs;
	private AgentConfigVO agentConfig;
	private List<ServiceVO> services;

	/**
	 * Gets the value of the id property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the value of the id property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setId(String value) {
		this.id = value;
	}

	/**
	 * Gets the value of the channelType property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getChannelType() {
		return channelType;
	}

	/**
	 * Sets the value of the channelType property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setChannelType(String value) {
		this.channelType = value;
	}

	/**
	 * Gets the value of the url property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Sets the value of the url property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setUrl(String value) {
		this.url = value;
	}

	/**
	 * Gets the value of the preference property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getPreference() {
		return preference;
	}

	/**
	 * Sets the value of the preference property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setPreference(Integer value) {
		this.preference = value;
	}

	/**
	 * Gets the value of the location property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * Sets the value of the location property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setLocation(String value) {
		this.location = value;
	}

	/**
	 * Gets the value of the usesMFA property.
	 * 
	 * @return possible object is {@link Boolean }
	 * 
	 */
	public Boolean getUsesMFA() {
		return usesMFA;
	}

	/**
	 * Sets the value of the usesMFA property.
	 * 
	 * @param value
	 *            allowed object is {@link Boolean }
	 * 
	 */
	public void setUsesMFA(Boolean value) {
		this.usesMFA = value;
	}

	/**
	 * Gets the value of the authenticationInfo property.
	 * 
	 * @return possible object is {@link AuthenticationInfo }
	 * 
	 */
	public AuthenticationInfoVO getAuthenticationInfo() {
		return authenticationInfo;
	}

	/**
	 * Sets the value of the authenticationInfo property.
	 * 
	 * @param value
	 *            allowed object is {@link AuthenticationInfo }
	 * 
	 */
	public void setAuthenticationInfo(AuthenticationInfoVO value) {
		this.authenticationInfo = value;
	}

	/**
	 * Gets the value of the globalConfig property.
	 * 
	 * @return possible object is {@link GlobalConfig }
	 * 
	 */
	public GlobalConfigVO getGlobalConfig() {
		return globalConfig;
	}

	/**
	 * Sets the value of the globalConfig property.
	 * 
	 * @param value
	 *            allowed object is {@link GlobalConfig }
	 * 
	 */
	public void setGlobalConfig(GlobalConfigVO value) {
		this.globalConfig = value;
	}

	/**
	 * Gets the value of the agentConfig property.
	 * 
	 * @return possible object is {@link AgentConfig }
	 * 
	 */
	public List<AgentConfigVO> getAgentConfigs() {
		return agentConfigs;
	}

	/**
	 * Sets the value of the agentConfig property.
	 * 
	 * @param value
	 *            allowed object is {@link AgentConfig }
	 * 
	 */
	public void setAgentConfigs(List<AgentConfigVO> value) {
		this.agentConfigs = value;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ChannelVO [id=");
		builder.append(id);
		builder.append(", channelType=");
		builder.append(channelType);
	//	builder.append(", url=");
	//	builder.append(url);
		builder.append(", preference=");
		builder.append(preference);
		builder.append(", location=");
		builder.append(location);
		builder.append(", authenticationInfo=");
		builder.append(authenticationInfo);
		builder.append(", globalConfig=");
		builder.append(globalConfig);
		builder.append(", agentConfig=");
		builder.append(agentConfig);
		builder.append(", services=");
		builder.append(services);
		builder.append("]");
		return builder.toString();
	}

	public List<ServiceVO> getServices() {
		return services;
	}

	public void setServices(List<ServiceVO> services) {
		this.services = services;
	}

	public AgentConfigVO getAgentConfig() {
		return agentConfig;
	}

	public void setAgentConfig(AgentConfigVO agentConfig) {
		this.agentConfig = agentConfig;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.typeOfChannel() == null) ? 0 : this.typeOfChannel().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChannelVO other = (ChannelVO) obj;
		if (this.typeOfChannel() == null) {
			if (other.typeOfChannel() != null)
				return false;
		} else if (!this.typeOfChannel().equals(other.typeOfChannel()))
			return false;
		return true;
	}

	@Override
	public int compareTo(ChannelVO channelVO) {
		Integer comparePreference = channelVO.getPreference();
		return this.getPreference() - comparePreference;
	}
	/*
	 * This method should be overridden by every channel to return the ChannelType corresponding to it.
	 * This will be used when validating the channel in the request.
	 */
	public abstract ChannelType typeOfChannel();
}
