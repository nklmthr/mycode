package com.intuit.cto.fds.dash.web.shared.rpc.dto;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class ProviderDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	protected String id;
	protected String legacyId;
	protected String name;
	protected boolean active;
	protected String type;
	//protected LogoVO logo;
	protected Boolean canPersistResponse;
	private List<ChannelVO> channels;
	protected HelpInfoVO helpInfo;
	private Date modifiedTime;

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
	 * Gets the value of the legacyId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getLegacyId() {
		return legacyId;
	}

	/**
	 * Sets the value of the legacyId property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setLegacyId(String value) {
		this.legacyId = value;
	}

	/**
	 * Gets the value of the name property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the value of the name property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setName(String value) {
		this.name = value;
	}

	/**
	 * Gets the value of the active property.
	 * 
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * Sets the value of the active property.
	 * 
	 */
	public void setActive(boolean value) {
		this.active = value;
	}

	/**
	 * Gets the value of the type property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the value of the type property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setType(String value) {
		this.type = value;
	}

	/**
	 * Gets the value of the logo property.
	 * 
	 * @return possible object is {@link Logo }
	 * 
	 *
	public LogoVO getLogo() {
		return logo;
	}

	**
	 * Sets the value of the logo property.
	 * 
	 * @param value
	 *            allowed object is {@link Logo }
	 * 
	 *
	public void setLogo(LogoVO value) {
		this.logo = value;
	}

	**
	 * Gets the value of the canPersistResponse property.
	 * 
	 */
	public Boolean isCanPersistResponse() {
		return canPersistResponse;
	}

	/**
	 * Sets the value of the canPersistResponse property.
	 * 
	 */
	public void setCanPersistResponse(boolean value) {
		this.canPersistResponse = value;
	}

	public List<ChannelVO> getChannels() {
		return channels;
	}

	public void setChannels(List<ChannelVO> channels) {
		this.channels = channels;
	}

	public HelpInfoVO getHelpInfo() {
		return helpInfo;
	}

	public void setHelpInfo(HelpInfoVO value) {
		this.helpInfo = value;
	}

	public Date getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ProviderDTO [id=");
		builder.append(id);
		builder.append(", legacyId=");
		builder.append(legacyId);
		builder.append(", name=");
		builder.append(name);
		builder.append(", active=");
		builder.append(active);
		builder.append(", type=");
		builder.append(type);
		builder.append(", logo=");
		//builder.append(logo);
		builder.append(", canPersistResponse=");
		builder.append(canPersistResponse);
		builder.append(", channels=");
		builder.append(channels);
		builder.append(", helpInfo=");
		builder.append(helpInfo);
		builder.append("]");
		return builder.toString();
	}

	public static final Comparator<ProviderDTO> PROVIDER_MODIFIED_TIME_COMPARATOR = new Comparator<ProviderDTO>() {

		@Override
		public int compare(ProviderDTO provider1, ProviderDTO provider2) {
			Date provider1LastModifiedTime = null;
			Date provider2LastModifiedTime = null;
			if (null != provider1 && null != provider2) {
				provider1LastModifiedTime = provider1.getModifiedTime();
				provider2LastModifiedTime = provider2.getModifiedTime();
			}

			// descending sort on modifiedTime
			if (null != provider1LastModifiedTime && null != provider1LastModifiedTime)
				return provider2LastModifiedTime.compareTo(provider1LastModifiedTime);
			else
				return 0;
		}

	};
}
