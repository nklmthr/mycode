package com.intuit.cto.fds.dash.web.shared.rpc.dto;

import javax.xml.datatype.XMLGregorianCalendar;

public class EinProviderRelationshipVO {

	protected String id;

	protected String providerId;

	protected int taxYear;

	protected XMLGregorianCalendar actualAvailabilityDate;

	protected XMLGregorianCalendar expectedAvailabilityDate;
	protected int preference = 1;

	protected boolean synced = false;

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
	 * Gets the value of the providerId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getProviderId() {
		return providerId;
	}

	/**
	 * Sets the value of the providerId property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setProviderId(String value) {
		this.providerId = value;
	}

	/**
	 * Gets the value of the taxYear property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public int getTaxYear() {
		return taxYear;
	}

	/**
	 * Sets the value of the taxYear property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTaxYear(int value) {
		this.taxYear = value;
	}

	/**
	 * Gets the value of the actualAvailabilityDate property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getActualAvailabilityDate() {
		return actualAvailabilityDate;
	}

	/**
	 * Sets the value of the actualAvailabilityDate property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setActualAvailabilityDate(XMLGregorianCalendar value) {
		this.actualAvailabilityDate = value;
	}

	/**
	 * Gets the value of the expectedAvailabilityDate property.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getExpectedAvailabilityDate() {
		return expectedAvailabilityDate;
	}

	/**
	 * Sets the value of the expectedAvailabilityDate property.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setExpectedAvailabilityDate(XMLGregorianCalendar value) {
		this.expectedAvailabilityDate = value;
	}

	/**
	 * Gets the value of the preference property.
	 * 
	 */
	public int getPreference() {
		return preference;
	}

	/**
	 * Sets the value of the preference property.
	 * 
	 */
	public void setPreference(int value) {
		this.preference = value;
	}

	public boolean isSynced() {
		return synced;
	}

	public void setSynced(boolean synced) {
		this.synced = synced;
	}

	@Override
	public String toString() {
		return String.format(
				"EinProviderRelationshipVO [id=%s, providerId=%s, taxYear=%s, actualAvailabilityDate=%s, expectedAvailabilityDate=%s, preference=%s, synced=%s]",
				id, providerId, taxYear, actualAvailabilityDate, expectedAvailabilityDate, preference, synced);
	}
}
