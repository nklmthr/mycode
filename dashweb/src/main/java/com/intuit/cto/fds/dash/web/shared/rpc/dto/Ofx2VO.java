package com.intuit.cto.fds.dash.web.shared.rpc.dto;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Ofx2VO extends ChannelVO implements IsSerializable, Serializable{

	private static final long serialVersionUID = 1L;
	protected String version;
	protected DataValidationVO dataValidation = DataValidationVO.PASSIVE;

	public Ofx2VO() {
		preference = 1;
	}

	/**
	 * Gets the value of the version property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * Sets the value of the version property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setVersion(String value) {
		this.version = value;
	}

	/**
	 * Gets the value of the dataValidation property.
	 * 
	 * @return possible object is {@link DataValidationVO }
	 * 
	 */
	public DataValidationVO getDataValidation() {
		return dataValidation;
	}

	/**
	 * Sets the value of the dataValidation property.
	 * 
	 * @param value
	 *            allowed object is {@link DataValidationVO }
	 * 
	 */
	public void setDataValidation(DataValidationVO value) {
		this.dataValidation = value;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Ofx2VO [version=");
		builder.append(version);
		builder.append(", dataValidation=");
		builder.append(dataValidation);
		builder.append(", super.toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

	public ChannelType typeOfChannel() {
		return ChannelType.OFX_2;
	}

}
