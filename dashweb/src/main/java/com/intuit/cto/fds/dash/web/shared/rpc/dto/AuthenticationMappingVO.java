package com.intuit.cto.fds.dash.web.shared.rpc.dto;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AuthenticationMappingVO implements Serializable, Comparable<AuthenticationMappingVO> {

	private static final long serialVersionUID = 1L;
	protected String id;
	protected String fieldType;
	protected String stripChars;
	protected Boolean hideChars;
	protected String authenticationText;
	protected String mappingSource;
	protected List<String> mappingTarget;
	protected boolean encrypted;
	protected Integer ignoreWhenIALExceeds;
	protected BigInteger displayOrder;

	/**
	 * @return the ignoreWhenIALExceeds
	 */
	public Integer getIgnoreWhenIALExceeds() {
		return ignoreWhenIALExceeds;
	}

	/**
	 * @param ignoreWhenIALExceeds
	 *            the ignoreWhenIALExceeds to set
	 */
	public void setIgnoreWhenIALExceeds(Integer ignoreWhenIALExceeds) {
		this.ignoreWhenIALExceeds = ignoreWhenIALExceeds;
	}

	/**
	 * @return the displayOrder
	 */
	public BigInteger getDisplayOrder() {
		return displayOrder;
	}

	/**
	 * @param displayOrder
	 *            the displayOrder to set
	 */
	public void setDisplayOrder(BigInteger displayOrder) {
		this.displayOrder = displayOrder;
	}

	public String getId() {
		return id;
	}

	public void setId(String value) {
		this.id = value;
	}

	public String getAuthenticationText() {
		return authenticationText;
	}

	public void setAuthenticationText(String value) {
		this.authenticationText = value;
	}

	public String getMappingSource() {
		return mappingSource;
	}

	public void setMappingSource(String value) {
		this.mappingSource = value;
	}

	public List<String> getMappingTarget() {
		if (mappingTarget == null) {
			mappingTarget = new ArrayList<String>();
		}
		return this.mappingTarget;
	}

	public void setMappingTarget(List<String> mappingTarget) {
		this.mappingTarget = mappingTarget;
	}

	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String value) {
		this.fieldType = value;
	}

	public String getStripChars() {
		return stripChars;
	}

	public void setStripChars(String value) {
		this.stripChars = value;
	}

	public Boolean getHideChars() {
		return hideChars;
	}

	public void setHideChars(Boolean hideChars) {
		this.hideChars = hideChars;
	}

	public boolean getEncrypted() {
		return encrypted;
	}

	public void setEncrypted(boolean encrypted) {
		this.encrypted = encrypted;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AuthenticationMappingVO [id=");
		builder.append(id);
		builder.append(", fieldType=");
		builder.append(fieldType);
		builder.append(", stripChars=");
		builder.append(stripChars);
		builder.append(", hideChars=");
		builder.append(hideChars);
		builder.append(", authenticationText=");
		builder.append(authenticationText);
		builder.append(", mappingSource=");
		builder.append(mappingSource);
		builder.append(", mappingTarget=");
		builder.append(mappingTarget);
		builder.append(", encrypted=");
		builder.append(encrypted);
		builder.append(", ignoreWhenIALExceeds=");
		builder.append(ignoreWhenIALExceeds);
		builder.append(", displayOrder=");
		builder.append(displayOrder);

		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mappingSource == null) ? 0 : mappingSource.hashCode());
		result = prime * result + ((mappingTarget == null) ? 0 : mappingTarget.hashCode());
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
		AuthenticationMappingVO other = (AuthenticationMappingVO) obj;

		if (mappingSource != null && other.mappingSource != null) {
			return mappingSource.equals(other.mappingSource);
		}

		if (mappingTarget != null && other.mappingTarget != null) {
			Set<String> mappingTargetSet = new HashSet<String>(mappingTarget);
			Set<String> otherMappingTargetSet = new HashSet<String>(other.mappingTarget);
			return mappingTargetSet.equals(otherMappingTargetSet);
		}

		return false;
	}

	@Override
	public int compareTo(AuthenticationMappingVO authenticationMappingVO) {
		int comapreDiff = 0;
		try {
			int compareDisplayOrder = authenticationMappingVO.getDisplayOrder().intValue();
			comapreDiff = this.displayOrder.intValue() - compareDisplayOrder;
		} catch (Exception e) {
		}
		return comapreDiff;
	}

}
